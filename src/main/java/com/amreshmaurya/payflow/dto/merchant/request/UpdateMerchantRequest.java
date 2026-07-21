package com.amreshmaurya.payflow.dto.merchant.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMerchantRequest {

 @Column(nullable = false)
    private String businessName;

    // @Column(nullable = false, unique = true)
    // private String merchantCode;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private  String password;

    @Column(nullable = false)
    private String phone;

    private String website;

    // @Column(nullable = false)
    // private String apiKey;

    // @Column(nullable = false)
    // private String secretKey;

    @Column(nullable = false)
    private Boolean active = true;
}