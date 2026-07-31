package com.amreshmaurya.payflow.dto.auth.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterResponse {
    private boolean success;
    private String message;
}