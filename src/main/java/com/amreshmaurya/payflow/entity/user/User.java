package com.amreshmaurya.payflow.entity.user;


import com.amreshmaurya.payflow.entity.BaseEntity;
import com.amreshmaurya.payflow.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder.Default;
import lombok.*;




@Entity
@Table(name="users")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class User extends BaseEntity {


    @Column(nullable=false, unique=true)
    private String email;


    @Column(nullable=false)
    private String password;


    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    @Builder.Default
    private UserRole role = UserRole.USER;

    @Builder.Default
    private Boolean enabled = true;


}