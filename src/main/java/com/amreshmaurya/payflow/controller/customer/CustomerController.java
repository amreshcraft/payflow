package com.amreshmaurya.payflow.controller.customer;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amreshmaurya.payflow.dto.customer.request.CreateCustomerRequest;
import com.amreshmaurya.payflow.dto.customer.request.UpdateCustomerRequest;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

import com.amreshmaurya.payflow.common.response.ApiResponse;

import com.amreshmaurya.payflow.dto.customer.response.CustomerResponse;
import com.amreshmaurya.payflow.service.customer.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

        private final CustomerService customerService;

        public CustomerController(CustomerService customerService) {
                this.customerService = customerService;
        }

        @PostMapping
        public ResponseEntity<ApiResponse<CustomerResponse>> createCustomer(
                        @Valid @RequestBody CreateCustomerRequest createCustomerRequest) {

                return ResponseEntity.status(HttpStatus.CREATED)
                                .body(ApiResponse.<CustomerResponse>builder()
                                                .success(true)
                                                .message("Customer created successfully")
                                                .data(customerService.createCustomer(createCustomerRequest))
                                                .timestamp(LocalDateTime.now())
                                                .build());
        }

        @GetMapping("/{id}")
        public ResponseEntity<ApiResponse<CustomerResponse>> getCustomerById(
                        @PathVariable UUID id) {

                return ResponseEntity.ok(
                                ApiResponse.<CustomerResponse>builder()
                                                .success(true)
                                                .message("Customer retrieved successfully")
                                                .data(customerService.getCustomerById(id))
                                                .timestamp(LocalDateTime.now())
                                                .build());
        }

        @GetMapping("/email")
        public ResponseEntity<ApiResponse<CustomerResponse>> getCustomerByEmail(
                        @RequestParam String email) {

                return ResponseEntity.ok(
                                ApiResponse.<CustomerResponse>builder()
                                                .success(true)
                                                .message("Customer retrieved successfully")
                                                .data(customerService.getCustomerByEmail(email))
                                                .timestamp(LocalDateTime.now())
                                                .build());
        }

        @GetMapping("/phone/{phone}")
        public ResponseEntity<ApiResponse<CustomerResponse>> getCustomerByPhone(
                        @PathVariable String phone) {

                return ResponseEntity.ok(
                                ApiResponse.<CustomerResponse>builder()
                                                .success(true)
                                                .message("Customer retrieved successfully")
                                                .data(customerService.getCustomerByPhone(phone))
                                                .timestamp(LocalDateTime.now())
                                                .build());
        }

        @PutMapping("/{id}")
        public ResponseEntity<ApiResponse<CustomerResponse>> updateCustomer(
                        @PathVariable UUID id,
                        @Valid @RequestBody UpdateCustomerRequest updateCustomerRequest) {

                return ResponseEntity.ok(
                                ApiResponse.<CustomerResponse>builder()
                                                .success(true)
                                                .message("Customer updated successfully")
                                                .data(customerService.updateCustomer(id, updateCustomerRequest))
                                                .timestamp(LocalDateTime.now())
                                                .build());
        }

        // @DeleteMapping("/{id}")
        // public ResponseEntity<ApiResponse<Void>> deleteCustomer(
        // @PathVariable UUID id) {

        // customerService.deleteCustomer(id);

        // return ResponseEntity.ok(
        // ApiResponse.<Void>builder()
        // .success(true)
        // .message("Customer deleted successfully")
        // .timestamp(LocalDateTime.now())
        // .build());
        // }

        // @PatchMapping("/{id}/activate")
        // public ResponseEntity<ApiResponse<CustomerResponse>> activateCustomer(
        // @PathVariable UUID id) {

        // return ResponseEntity.ok(
        // ApiResponse.<CustomerResponse>builder()
        // .success(true)
        // .message("Customer activated successfully")
        // .data(customerService.activateCustomer(id))
        // .timestamp(LocalDateTime.now())
        // .build());
        // }

        // @PatchMapping("/{id}/deactivate")
        // public ResponseEntity<ApiResponse<CustomerResponse>> deactivateCustomer(
        // @PathVariable UUID id) {

        // return ResponseEntity.ok(
        // ApiResponse.<CustomerResponse>builder()
        // .success(true)
        // .message("Customer deactivated successfully")
        // .data(customerService.deactivateCustomer(id))
        // .timestamp(LocalDateTime.now())
        // .build());
        // }

}