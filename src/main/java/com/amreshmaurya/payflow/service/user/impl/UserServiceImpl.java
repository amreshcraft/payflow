package com.amreshmaurya.payflow.service.user.impl;



import com.amreshmaurya.payflow.entity.user.User;
import com.amreshmaurya.payflow.enums.UserRole;
import com.amreshmaurya.payflow.repository.user.UserRepository;
import com.amreshmaurya.payflow.service.user.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User createUser(
            String email,
            String password,
            UserRole role
    ) {

        if (repository.existsByEmail(email.toLowerCase())) {
            throw new RuntimeException("Email already exists");
        }

        User user = User.builder()
                .email(email.toLowerCase())
                .password(passwordEncoder.encode(password))
                .role(role)
                .enabled(true)
                .build();

        return repository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email.toLowerCase());
    }

    @Override
    public User getByEmail(String email) {

        return repository.findByEmail(email.toLowerCase())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email.toLowerCase());
    }

    @Override
    public void enableUser(String email) {

        User user = getByEmail(email);

        user.setEnabled(true);

        repository.save(user);
    }

    @Override
    public void disableUser(String email) {

        User user = getByEmail(email);

        user.setEnabled(false);

        repository.save(user);
    }

}
