


package com.amreshmaurya.payflow.dto.customer.response;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponse {

    private UUID id;

    private String fullName;

    private String email;

    private String phone;

    private Boolean active;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}