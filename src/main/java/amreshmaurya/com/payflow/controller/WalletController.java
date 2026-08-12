package amreshmaurya.com.payflow.controller;

import amreshmaurya.com.payflow.entity.Wallet;
import amreshmaurya.com.payflow.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/wallets")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @PostMapping
public ResponseEntity<Wallet> createWallet(Authentication authentication) {

    String email = authentication.getName();

    Wallet wallet = walletService.createWallet(email);

    return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(wallet);
}
    @GetMapping("/me")
public ResponseEntity<Wallet> getMyWallet(Authentication authentication) {

    String email = authentication.getName();

    Wallet wallet = walletService.getWallet(email);

    return ResponseEntity.ok(wallet);
}
}