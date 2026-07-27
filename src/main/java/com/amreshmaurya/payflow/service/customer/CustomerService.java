package com.amreshmaurya.payflow.service.customer;

import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.amreshmaurya.payflow.dto.customer.request.CreateCustomerRequest;
import com.amreshmaurya.payflow.dto.customer.response.CustomerResponse;
import com.amreshmaurya.payflow.entity.customer.Customer;
import com.amreshmaurya.payflow.mapper.CustomerMapper;
import com.amreshmaurya.payflow.repository.CustomerRepository;



@Service
public class CustomerService {
    

     private final CustomerRepository customerRepository;
     private final CustomerMapper customerMapper;
     private final PasswordEncoder passwordEncoder;


     public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper, PasswordEncoder passwordEncoder) {
         this.customerRepository = customerRepository;
         this.customerMapper = customerMapper;
         this.passwordEncoder = passwordEncoder;
     }

    
    public CustomerResponse createCustomer(CreateCustomerRequest request) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        Customer customer = customerMapper.toEntity(request);
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.toResponse(savedCustomer);
    }

  public CustomerResponse getCustomerById(UUID customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        return customerMapper.toResponse(customer);
    }



  public CustomerResponse getCustomerByEmail(String email) {
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        return customerMapper.toResponse(customer);
    }

    // public CustomerResponse updateCustomer(UUID  customerId, CreateCustomerRequest request) {
    //     // Implementation for updating a customer
    //     Customer existingCustomer = customerRepository.findById(customerId)
    //             .orElseThrow(() -> new RuntimeException("Customer not found"));
    //     customerMapper.updateEntity(request, existingCustomer);
    //     Customer updatedCustomer = customerRepository.save(existingCustomer);
    //     return customerMapper.toResponse(updatedCustomer);
    // }

    public void deleteCustomer(UUID customerId) {
        // Implementation for deleting a customer
        Customer existingCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        customerRepository.delete(existingCustomer);
    }


}