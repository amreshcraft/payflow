package com.amreshmaurya.payflow.dto.customer.request;


import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateCustomerRequest {


    @NotBlank(message = "Full name is required")
    @Size(min = 3,max = 100)
    private String fullName;


    @NotBlank(message = "Phone is required")
    @Pattern(
        regexp = "^[6-9][0-9]{9}$",
        message = "Invalid phone number"
    )
    private String phone;

}