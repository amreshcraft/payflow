package com.amreshmaurya.payflow.dto.merchant.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateMerchantRequest {


    @NotBlank(message = "Business name is required")
    @Size(min = 3, max = 100, message = "Business name must be between 3 and 100 characters")
    private String businessName;


    @NotBlank(message = "Merchant code is required")
    @Size(min = 5, max = 20, message = "Merchant code must be between 5 and 20 characters")
    private String merchantCode;


    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;


    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must contain minimum 8 characters")
    private String password;


    @NotBlank(message = "Phone number is required")
    @Pattern(
        regexp = "^[6-9][0-9]{9}$",
        message = "Invalid Indian phone number"
    )
    private String phone;


    @Size(max = 255, message = "Website URL too long")
    private String website;

}