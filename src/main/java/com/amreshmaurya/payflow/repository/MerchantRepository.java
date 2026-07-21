package com.amreshmaurya.payflow.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amreshmaurya.payflow.entity.merchant.Merchant;

public interface MerchantRepository extends JpaRepository<Merchant, UUID> {
    public Optional<Merchant> findByMerchantCode(String merchantCode);
    public Optional<Merchant> findByEmail(String email);
    public Optional<Merchant> findByPhone(String phone);
}
