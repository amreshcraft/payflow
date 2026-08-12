package amreshmaurya.com.payflow.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import amreshmaurya.com.payflow.entity.User;
import amreshmaurya.com.payflow.entity.Wallet;

public interface WalletRepository extends JpaRepository<Wallet,UUID> {
    Optional<Wallet> findByUser(User user);
    Optional<Wallet> findByUserId(UUID userId);
    } 