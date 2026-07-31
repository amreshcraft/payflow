package com.amreshmaurya.payflow.entity.merchant;

import com.amreshmaurya.payflow.entity.BaseEntity;
import com.amreshmaurya.payflow.entity.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@Entity
@Table(name="merchants")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Merchant extends BaseEntity {


    @Column(nullable=false)
    private String businessName;


    @Column(nullable=false, unique=true)
    private String merchantCode;


    private String phone;


    private String website;


    private String apiKey;


    private String secretKey;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @Builder.Default
    private Boolean active=true;

}