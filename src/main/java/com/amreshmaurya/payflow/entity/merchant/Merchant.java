package com.amreshmaurya.payflow.entity.merchant;

import com.amreshmaurya.payflow.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private String apiKey;

    private String secretKey;
    
    @Default
    private Boolean active = true;



 
}