package com.amreshmaurya.payflow.dto.merchant.response;


import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class MerchantResponse {

    private UUID id;

    private String businessName;

    private String merchantCode;

    private String email;

    private String phone;

    private String website;

    private String apiKey;

    private Boolean active;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}