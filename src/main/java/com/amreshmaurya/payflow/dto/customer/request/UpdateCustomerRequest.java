package com.amreshmaurya.payflow.dto.customer.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateCustomerRequest {

    @Size(max = 100, message = "Full name cannot exceed 100 characters")
    private String fullName;

    @Email(message = "Invalid email address")
    @Size(max = 255, message = "Email cannot exceed 255 characters")
    private String email;

    @Pattern(
            regexp = "^[0-9]{10,15}$",
            message = "Phone number must be between 10 and 15 digits"
    )
    private String phone;

    private Boolean active;
}