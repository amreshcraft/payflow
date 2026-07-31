package com.amreshmaurya.payflow.dto.merchant.request;

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
public class UpdateMerchantRequest {


    @NotBlank(message = "Business name is required")
    @Size(min = 3, max = 100)
    private String businessName;


    @NotBlank(message = "Phone is required")
    @Pattern(
        regexp = "^[6-9][0-9]{9}$",
        message = "Invalid phone number"
    )
    private String phone;


    @Size(max = 255)
    private String website;


}