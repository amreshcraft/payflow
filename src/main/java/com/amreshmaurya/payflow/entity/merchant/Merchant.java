package com.amreshmaurya.payflow.entity.merchant;

import com.amreshmaurya.payflow.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "merchants")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Merchant extends BaseEntity {

    @Column(nullable = false)
    private String businessName;

    @Column(nullable = false, unique = true)
    private String merchantCode;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private  String password;

    @Column(nullable = false)
    private String phone;

    private String website;

    @Column(nullable = false)
    private String apiKey;

    @Column(nullable = false)
    private String secretKey;

    @Column(nullable = false)
    private Boolean active = true;
}