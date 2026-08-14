package amreshmaurya.com.payflow.dto.payment;


import amreshmaurya.com.payflow.entity.PaymentStatus;

import java.math.BigDecimal;
import java.util.UUID;

public record PaymentResponse(
        UUID paymentId,
        BigDecimal amount,
        String currency,
        PaymentStatus status,
        String reference
) {
}
