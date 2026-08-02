package com.amreshmaurya.payflow.util;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HashedPasswordManager {
 private  final PasswordEncoder passwordEncoder ;
    public String hashPassword(String password){
        return passwordEncoder.encode(password);

    }

    public boolean verifyPassword(String password, String hashedPassword){
        return passwordEncoder.matches(password, hashedPassword);
    }
}
