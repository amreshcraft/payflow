package amreshmaurya.com.payflow.service;

import amreshmaurya.com.payflow.entity.User;
import amreshmaurya.com.payflow.entity.Wallet;
import amreshmaurya.com.payflow.entity.WalletStatus;
import amreshmaurya.com.payflow.repository.UserRepository;
import amreshmaurya.com.payflow.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;
    private final UserRepository userRepository;

    @Transactional
    public Wallet createWallet(String email) {

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

        return walletRepository.save(wallet);
    }

    @Transactional(readOnly = true)
    public Wallet getWallet(UUID userId) {

        return walletRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));
    }

    @Transactional(readOnly = true)
    public BigDecimal getBalance(UUID userId) {

        return getWallet(userId).getBalance();
    }
}
