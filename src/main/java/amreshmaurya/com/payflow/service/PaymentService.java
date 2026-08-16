package amreshmaurya.com.payflow.service;

import amreshmaurya.com.payflow.repository.PaymentRepository;
import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.stereotype.Service;

import amreshmaurya.com.payflow.dto.payment.CreatePaymentRequest;
import amreshmaurya.com.payflow.dto.payment.PaymentResponse;
import amreshmaurya.com.payflow.entity.Payment;
import amreshmaurya.com.payflow.entity.PaymentStatus;
import amreshmaurya.com.payflow.entity.User;
import amreshmaurya.com.payflow.entity.Wallet;
import amreshmaurya.com.payflow.repository.UserRepository;
import amreshmaurya.com.payflow.repository.WalletRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final WalletRepository walletRepository;

    @Transactional
    public PaymentResponse createPayment(
            String email,
            CreatePaymentRequest request) {

        User sender = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException(
                                "User doesn't have PayFlow wallet account"));

        User receiver = userRepository.findByPhone(
                request.getReceiverPhone()
        ).orElseThrow(() ->
                new RuntimeException(
                        "Receiver doesn't have PayFlow wallet account"));

        if (sender.getId().equals(receiver.getId())) {
            throw new RuntimeException(
                    "You cannot send payment to yourself");
        }

        Wallet senderWallet = walletRepository.findByUserId(sender.getId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Sender wallet not found"));

        Wallet receiverWallet = walletRepository.findByUserId(receiver.getId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Receiver wallet not found"));

        BigDecimal amount = request.getAmount();

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException(
                    "Amount must be greater than zero");
        }

        if (senderWallet.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException(
                    "Insufficient wallet balance");
        }

        senderWallet.setBalance(
                senderWallet.getBalance().subtract(amount)
        );

        receiverWallet.setBalance(
                receiverWallet.getBalance().add(amount)
        );

        Payment payment = Payment.builder()
                .sender(sender)
                .receiver(receiver)
                .amount(amount)
                .status(PaymentStatus.SUCCESS)
                .reference(UUID.randomUUID().toString())
                .build();

        paymentRepository.save(payment);

        return PaymentResponse.builder()
                .amount(amount)
                .currency("INR")
                .status(PaymentStatus.SUCCESS)
                .reference(payment.getReference())
                .build();
    }
}