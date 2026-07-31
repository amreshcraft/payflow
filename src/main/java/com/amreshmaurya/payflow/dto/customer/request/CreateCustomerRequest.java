package com.amreshmaurya.payflow.dto.customer.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCustomerRequest {


    @NotBlank(message = "Full name is required")
    @Size(
        min = 3,
        max = 100,
        message = "Name must be between 3 and 100 characters"
    )
    private String fullName;


    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;


    @NotBlank(message = "Password is required")
    @Size(
        min = 8,
        message = "Password must contain minimum 8 characters"
    )
    private String password;


    @NotBlank(message = "Phone number is required")
    @Pattern(
        regexp = "^[6-9][0-9]{9}$",
        message = "Invalid Indian phone number"
    )
    private String phone;

}