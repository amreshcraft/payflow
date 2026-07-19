package com.amreshmaurya.payflow.dto.merchant.request;

import com.amreshmaurya.payflow.entity.BaseEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateMerchantRequest {


    @NotBlank(message = "Business name is required")
    private String businessName;

    @NotBlank(message = "Merchant code is required")
    private String merchantCode;

    @Email(message = "Invalid email")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    private  String password;

    @NotBlank(message = "Phone is required")
    private String phone;

    private String website;
}