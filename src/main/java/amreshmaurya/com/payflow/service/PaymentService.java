package amreshmaurya.com.payflow.service;

import amreshmaurya.com.payflow.repository.PaymentRepository;
import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.stereotype.Service;

import amreshmaurya.com.payflow.dto.payment.CreatePaymentRequest;
import amreshmaurya.com.payflow.dto.payment.PaymentResponse;
import amreshmaurya.com.payflow.entity.Payment;
import amreshmaurya.com.payflow.entity.PaymentStatus;
import amreshmaurya.com.payflow.entity.PaymentType;
import amreshmaurya.com.payflow.entity.User;
import amreshmaurya.com.payflow.entity.Wallet;
import amreshmaurya.com.payflow.repository.UserRepository;
import amreshmaurya.com.payflow.repository.WalletRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {
        private final PaymentRepository paymentRepository;
        private final UserRepository userRepository;
        private final WalletRepository walletRepository;
   

        public PaymentResponse createPayment(String email, CreatePaymentRequest request) {

                User sender = userRepository.findByEmail(email)
                                .orElseThrow(() -> new RuntimeException("User don;t have payflow wallet account"));
                String senderPhone = sender.getPhone();
                String receiverPhone = request.getReceiverPhone();
                User receiver = userRepository.findByPhone(receiverPhone)
                                .orElseThrow(() -> new RuntimeException("Receiver don't have payflow wallet account"));

                if (sender.getId().equals(receiver.getId())) {
                        throw new RuntimeException(
                                        "You cannot send payment to yourself");
                }

                Wallet senderWallet = walletRepository.findByUserId(sender.getId())
                                .orElseThrow(() -> new RuntimeException(
                                                "Sender wallet not found"));

                // Get receiver wallet
                Wallet receiverWallet = walletRepository.findByUserId(receiver.getId())
                                .orElseThrow(() -> new RuntimeException(
                                                "Receiver wallet not found"));

                BigDecimal amount = request.getAmount();

                // Balance check
                if (senderWallet.getBalance().compareTo(amount) < 0) {
                        throw new RuntimeException("Insufficient wallet balance");
                }
                // debit sender
                senderWallet.setBalance(
                                senderWallet.getBalance().subtract(amount));

                // Credit receiver
                receiverWallet.setBalance(
                                receiverWallet.getBalance().add(amount));

                Payment payment = Payment.builder()
                                .sender(sender)
                                .receiver(receiver)
                                .amount(amount)
                                .status(PaymentStatus.SUCCESS)
                                .transactionId(UUID.randomUUID().toString())
                                .build();

                paymentRepository.save(payment);

                // 13. Response
                return paymentMapper.toResponse(payment);
        }

}