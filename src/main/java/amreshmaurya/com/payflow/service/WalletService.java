package amreshmaurya.com.payflow.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import amreshmaurya.com.payflow.dto.wallet.WalletResponse;
import amreshmaurya.com.payflow.entity.User;
import amreshmaurya.com.payflow.entity.Wallet;
import amreshmaurya.com.payflow.entity.WalletStatus;
import amreshmaurya.com.payflow.repository.UserRepository;
import amreshmaurya.com.payflow.repository.WalletRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WalletService {

        private final WalletRepository walletRepository;
        private final UserRepository userRepository;

        @Transactional
        public WalletResponse createWallet(String email) {

                User user = userRepository.findByEmail(email)
                                .orElseThrow(() -> new RuntimeException("User not found"));

                if (walletRepository.findByUserId(user.getId()).isPresent()) {
                        throw new RuntimeException("Wallet already exists");
                }

                Wallet wallet = new Wallet();

                wallet.setUser(user);
                wallet.setBalance(BigDecimal.ZERO);
                wallet.setCurrency("INR");
                wallet.setStatus(WalletStatus.ACTIVE);

                Wallet savedWallet = walletRepository.save(wallet);

                return new WalletResponse(
                                savedWallet.getId(),
                                savedWallet.getBalance(),
                                savedWallet.getCurrency(),
                                savedWallet.getStatus(),
                                savedWallet.getVersion());
        }

        @Transactional(readOnly = true)
        public WalletResponse getWallet(String email) {
                Wallet wallet = walletRepository.findByUser_Email(email)
                                .orElseThrow(() -> new RuntimeException("Wallet not found"));

                return new WalletResponse(
                                wallet.getId(),
                                wallet.getBalance(),
                                wallet.getCurrency(),
                                wallet.getStatus(),
                                wallet.getVersion());
        }

        public WalletResponse depositMoney(String email, BigDecimal money) {

                Wallet wallet = walletRepository.findByUser_Email(email)
                                .orElseThrow(() -> new RuntimeException("Wallet is not exist or active"));
                if (money == null || money.compareTo(BigDecimal.ZERO) <= 0) {
                        throw new RuntimeException("Deposit amount must be greater than zero");
                }

                wallet.setBalance(
                                wallet.getBalance().add(money));
                return new WalletResponse(
                                wallet.getId(),
                                wallet.getBalance(),
                                wallet.getCurrency(),
                                wallet.getStatus(),
                                wallet.getVersion());

        }

}
