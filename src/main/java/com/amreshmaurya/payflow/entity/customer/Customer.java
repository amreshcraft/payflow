package com.amreshmaurya.payflow.entity.customer;


import com.amreshmaurya.payflow.entity.BaseEntity;
import com.amreshmaurya.payflow.entity.user.User;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Table(name="customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer extends BaseEntity {


    @Column(nullable=false)
    private String fullName;


    private String phone;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @Builder.Default
    private Boolean active=true;

}