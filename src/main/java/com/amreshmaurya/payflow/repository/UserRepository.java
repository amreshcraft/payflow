package com.amreshmaurya.payflow.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amreshmaurya.payflow.entity.user.User;

public interface UserRepository extends JpaRepository<User, UUID> {

public Optional<User> findByEmail(String email);
public Optional<User> findByUsername(String username);

}