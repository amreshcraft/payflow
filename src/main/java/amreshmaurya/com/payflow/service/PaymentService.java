package amreshmaurya.com.payflow.service;

import amreshmaurya.com.payflow.dto.payment.CreatePaymentRequest;
import amreshmaurya.com.payflow.dto.payment.PaymentResponse;
import amreshmaurya.com.payflow.entity.*;
import amreshmaurya.com.payflow.repository.PaymentEventRepository;
import amreshmaurya.com.payflow.repository.PaymentRepository;
import amreshmaurya.com.payflow.repository.UserRepository;
import amreshmaurya.com.payflow.repository.WalletRepository;
import amreshmaurya.com.payflow.repository.WalletTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final UserRepository userRepository;
    private final WalletRepository walletRepository;
    private final PaymentRepository paymentRepository;
    private final WalletTransactionRepository walletTransactionRepository;
    private final PaymentEventRepository paymentEventRepository;


    @Transactional
    public PaymentResponse createPayment(
            String senderEmail,
            CreatePaymentRequest request
    ) {

        // --------------------------------------------------
        // 1. Validate request
        // --------------------------------------------------

        if (request.amount() == null ||
                request.amount().compareTo(BigDecimal.ZERO) <= 0) {

            throw new RuntimeException("Amount must be greater than zero");
        }

        if (request.receiverPhone() == null ||
                request.receiverPhone().isBlank()) {

            throw new RuntimeException("Receiver phone is required");
        }

        if (request.currency() == null ||
                request.currency().isBlank()) {

            throw new RuntimeException("Currency is required");
        }


        // --------------------------------------------------
        // 2. Find sender
        // --------------------------------------------------

        User sender = userRepository.findByEmail(senderEmail)
                .orElseThrow(() ->
                        new RuntimeException("Sender not found")
                );


        // --------------------------------------------------
        // 3. Find receiver
        // --------------------------------------------------

        User receiver = userRepository.findByPhone(
                request.receiverPhone()
        ).orElseThrow(() ->
                new RuntimeException("Receiver not found")
        );


        // --------------------------------------------------
        // 4. Prevent self payment
        // --------------------------------------------------

        if (sender.getId().equals(receiver.getId())) {

            throw new RuntimeException(
                    "You cannot transfer money to yourself"
            );
        }


        // --------------------------------------------------
        // 5. Get wallets with pessimistic lock
        // --------------------------------------------------

        Wallet senderWallet =
                walletRepository.findByUserIdForUpdate(sender.getId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Sender wallet not found"
                                )
                        );

        Wallet receiverWallet =
                walletRepository.findByUserIdForUpdate(receiver.getId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Receiver wallet not found"
                                )
                        );


        // --------------------------------------------------
        // 6. Validate wallet status
        // --------------------------------------------------

        if (senderWallet.getStatus() != WalletStatus.ACTIVE) {

            throw new RuntimeException(
                    "Sender wallet is not active"
            );
        }

        if (receiverWallet.getStatus() != WalletStatus.ACTIVE) {

            throw new RuntimeException(
                    "Receiver wallet is not active"
            );
        }


        // --------------------------------------------------
        // 7. Validate currency
        // --------------------------------------------------

        if (!senderWallet.getCurrency()
                .equalsIgnoreCase(request.currency())) {

            throw new RuntimeException(
                    "Sender wallet currency mismatch"
            );
        }

        if (!receiverWallet.getCurrency()
                .equalsIgnoreCase(request.currency())) {

            throw new RuntimeException(
                    "Receiver wallet currency mismatch"
            );
        }


        // --------------------------------------------------
        // 8. Check balance
        // --------------------------------------------------

        BigDecimal amount = request.amount();

        BigDecimal senderBalanceBefore =
                senderWallet.getBalance();

        if (senderBalanceBefore.compareTo(amount) < 0) {

            throw new RuntimeException(
                    "Insufficient balance"
            );
        }


        // --------------------------------------------------
        // 9. Create Payment
        // --------------------------------------------------

        Payment payment = new Payment();

        payment.setSender(sender);
        payment.setReceiver(receiver);
        payment.setAmount(amount);
        payment.setCurrency(request.currency().toUpperCase());
        payment.setStatus(PaymentStatus.PROCESSING);
        payment.setReference(UUID.randomUUID().toString());
        payment.setDescription(request.description());

        payment = paymentRepository.save(payment);


        // --------------------------------------------------
        // 10. Calculate new balances
        // --------------------------------------------------

        BigDecimal senderBalanceAfter =
                senderBalanceBefore.subtract(amount);

        BigDecimal receiverBalanceBefore =
                receiverWallet.getBalance();

        BigDecimal receiverBalanceAfter =
                receiverBalanceBefore.add(amount);


        // --------------------------------------------------
        // 11. Debit sender
        // --------------------------------------------------

        senderWallet.setBalance(senderBalanceAfter);


        // --------------------------------------------------
        // 12. Credit receiver
        // --------------------------------------------------

        receiverWallet.setBalance(receiverBalanceAfter);


        // --------------------------------------------------
        // 13. Save wallets
        // --------------------------------------------------

        walletRepository.save(senderWallet);
        walletRepository.save(receiverWallet);


        // --------------------------------------------------
        // 14. Sender wallet transaction - DEBIT
        // --------------------------------------------------

        WalletTransaction senderTransaction =
                new WalletTransaction();

        senderTransaction.setWallet(senderWallet);
        senderTransaction.setPayment(payment);
        senderTransaction.setType(TransactionType.DEBIT);
        senderTransaction.setAmount(amount);
        senderTransaction.setBalanceBefore(senderBalanceBefore);
        senderTransaction.setBalanceAfter(senderBalanceAfter);
        senderTransaction.setDescription(
                "Payment sent to " + receiver.getPhone()
        );

        walletTransactionRepository.save(senderTransaction);


        // --------------------------------------------------
        // 15. Receiver wallet transaction - CREDIT
        // --------------------------------------------------

        WalletTransaction receiverTransaction =
                new WalletTransaction();

        receiverTransaction.setWallet(receiverWallet);
        receiverTransaction.setPayment(payment);
        receiverTransaction.setType(TransactionType.CREDIT);
        receiverTransaction.setAmount(amount);
        receiverTransaction.setBalanceBefore(
                receiverBalanceBefore
        );
        receiverTransaction.setBalanceAfter(
                receiverBalanceAfter
        );
        receiverTransaction.setDescription(
                "Payment received from " + sender.getPhone()
        );

        walletTransactionRepository.save(receiverTransaction);


        // --------------------------------------------------
        // 16. Mark payment successful
        // --------------------------------------------------

        payment.setStatus(PaymentStatus.SUCCESS);

        paymentRepository.save(payment);


        // --------------------------------------------------
        // 17. Create payment event
        // --------------------------------------------------

        PaymentEvent paymentEvent = new PaymentEvent();

        paymentEvent.setPayment(payment);
        paymentEvent.setEventType(
                PaymentEventType.PAYMENT_SUCCESS
        );
        paymentEvent.setDescription(
                "Payment completed successfully"
        );

        paymentEventRepository.save(paymentEvent);


        // --------------------------------------------------
        // 18. Return response
        // --------------------------------------------------

        return new PaymentResponse(
                payment.getId(),
                payment.getAmount(),
                payment.getCurrency(),
                payment.getStatus(),
                payment.getReference()
        );
    }
}