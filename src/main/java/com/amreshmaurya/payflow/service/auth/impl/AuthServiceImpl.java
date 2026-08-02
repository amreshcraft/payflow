package com.amreshmaurya.payflow.service.auth.impl;


import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.amreshmaurya.payflow.dto.auth.request.LoginRequest;
import com.amreshmaurya.payflow.dto.auth.request.RegisterRequest;
import com.amreshmaurya.payflow.dto.auth.response.LoginResponse;
import com.amreshmaurya.payflow.dto.auth.response.RegisterResponse;
import com.amreshmaurya.payflow.dto.customer.request.CreateCustomerRequest;
import com.amreshmaurya.payflow.dto.customer.response.CustomerResponse;
import com.amreshmaurya.payflow.dto.merchant.request.CreateMerchantRequest;
import com.amreshmaurya.payflow.dto.merchant.response.MerchantResponse;
import com.amreshmaurya.payflow.entity.customer.Customer;
import com.amreshmaurya.payflow.entity.merchant.Merchant;
import com.amreshmaurya.payflow.entity.user.User;
import com.amreshmaurya.payflow.exception.EmailAlreadyExistException;
import com.amreshmaurya.payflow.mapper.CustomerMapper;
import com.amreshmaurya.payflow.mapper.MerchantMapper;
import com.amreshmaurya.payflow.service.auth.AuthService;
import com.amreshmaurya.payflow.service.customer.CustomerService;
import com.amreshmaurya.payflow.service.merchant.MerchantService;
import com.amreshmaurya.payflow.service.user.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final CustomerService customerService;
    private final MerchantService merchantService;
    private final MerchantMapper merchantMapper;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerResponse registerCustomer(CreateCustomerRequest request) {

        String email = request.getEmail().toLowerCase();

        if (userService.existsByEmail(email)) {
            throw new EmailAlreadyExistException("Email already exists");
        }

      Customer customerResponse =  customerService.createCustomer(request);

        return customerMapper.toResponse(customerResponse);
    }

    @Override
    public MerchantResponse registerMerchant(CreateMerchantRequest request) {

        String email = request.getEmail().toLowerCase();

        if (userService.existsByEmail(email)) {
            throw new EmailAlreadyExistException("Email already exists");
        }
  
      Merchant  merchantResponse =  merchantService.createMerchant(request);
        return   merchantMapper.toResponse(merchantResponse);
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }
    


//  @Override
//     public LoginResponse login(LoginRequest request) {

//         authenticationManager.authenticate(
//                 new UsernamePasswordAuthenticationToken(
//                         request.getEmail(),
//                         request.getPassword()
//                 )
//         );

//         User user = userService.findByEmail(request.getEmail())
//                 .orElseThrow(() ->
//                         new BadCredentialsException("Invalid credentials"));

//         String accessToken = jwtService.generateToken(user);

//         return LoginResponse.builder()
//                 .accessToken(accessToken)
//                 .tokenType("Bearer")
//                 .role(user.getRole().name())
//                 .expiresIn(3600L)
//                 .build();
//     }


}
