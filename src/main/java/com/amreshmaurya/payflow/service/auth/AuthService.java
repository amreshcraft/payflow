package com.amreshmaurya.payflow.service.auth;

import org.springframework.stereotype.Service;

import com.amreshmaurya.payflow.dto.auth.request.LoginRequest;
import com.amreshmaurya.payflow.dto.auth.response.LoginResponse;
import com.amreshmaurya.payflow.dto.customer.request.CreateCustomerRequest;
import com.amreshmaurya.payflow.dto.customer.response.CustomerResponse;
import com.amreshmaurya.payflow.dto.merchant.request.CreateMerchantRequest;
import com.amreshmaurya.payflow.dto.merchant.response.MerchantResponse;



@Service
public interface AuthService {

    CustomerResponse registerCustomer(CreateCustomerRequest request);
    MerchantResponse registerMerchant(CreateMerchantRequest request);
    LoginResponse login(LoginRequest request);

}