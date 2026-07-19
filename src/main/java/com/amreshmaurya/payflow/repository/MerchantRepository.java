package com.amreshmaurya.payflow.repository;

import com.amreshmaurya.payflow.entity.merchant.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MerchantRepository extends JpaRepository<Merchant, UUID> {
}
