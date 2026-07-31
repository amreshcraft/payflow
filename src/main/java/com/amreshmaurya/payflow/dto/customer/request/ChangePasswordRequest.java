package com.amreshmaurya.payflow.dto.customer.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public class ChangePasswordRequest {

@NotBlank
private String oldPassword;

@NotBlank
@Size(min=8)
private String newPassword;

}