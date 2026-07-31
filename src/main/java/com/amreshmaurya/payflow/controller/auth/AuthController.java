package com.amreshmaurya.payflow.controller.auth;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
                                                                                                                                                                
import com.amreshmaurya.payflow.common.response.ApiResponse;
import com.amreshmaurya.payflow.dto.auth.request.LoginRequest;
import com.amreshmaurya.payflow.dto.auth.response.LoginResponse;
import com.amreshmaurya.payflow.dto.customer.request.CreateCustomerRequest;
import com.amreshmaurya.payflow.dto.customer.response.CustomerResponse;
import com.amreshmaurya.payflow.dto.merchant.request.CreateMerchantRequest;
import com.amreshmaurya.payflow.dto.merchant.response.MerchantResponse;
import com.amreshmaurya.payflow.service.auth.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;



    @PostMapping("/register/customer")
    public ResponseEntity<ApiResponse<CustomerResponse>> registerCustomer(
            @Valid @RequestBody CreateCustomerRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<CustomerResponse>builder()
                        .success(true)
                        .message("Registration successful")
                        .data(authService.registerCustomer(request))
                        .timestamp(LocalDateTime.now())
                        .build());
    }

        @PostMapping("/register/merchant")
    public ResponseEntity<ApiResponse<MerchantResponse>> registerMerchant(
            @Valid @RequestBody CreateMerchantRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<MerchantResponse>builder()
                        .success(true)
                        .message("Registration successful")
                        .data(authService.registerMerchant(request))
                        .timestamp(LocalDateTime.now())
                        .build());
    }



    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(
            @Valid @RequestBody LoginRequest request) {

        return ResponseEntity.ok(
                ApiResponse.<LoginResponse>builder()
                        .success(true)
                        .message("Login successful")
                        .data(authService.login(request))
                        .timestamp(LocalDateTime.now())
                        .build());
    }

}