package amreshmaurya.com.payflow.dto.payment;


import amreshmaurya.com.payflow.entity.PaymentStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;


@Builder
@Getter
@Setter
public class PaymentResponse{
        UUID paymentId;
        BigDecimal amount;
        String currency;
        PaymentStatus status;
        String reference;
}
