package com.amreshmaurya.payflow.entity.customer;

import java.util.UUID;

import com.amreshmaurya.payflow.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.Builder.Default;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String phone;


    @Default
    private Boolean active = true;

  
}