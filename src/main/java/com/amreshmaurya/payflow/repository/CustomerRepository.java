package com.amreshmaurya.payflow.repository;

import com.amreshmaurya.payflow.entity.customer.Customer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public  interface CustomerRepository extends JpaRepository<Customer, UUID> {

    public Optional<Customer> findByUserEmail(String email);
    public Optional<Customer> findByPhone(String phone);

}

