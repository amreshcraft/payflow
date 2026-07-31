package com.amreshmaurya.payflow.dto.merchant.response;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MerchantResponse {

    private UUID id;

    private String businessName;

    private String merchantCode;

    // Comes from User entity
    private String email;

    private String phone;

    private String website;

    private Boolean active;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}