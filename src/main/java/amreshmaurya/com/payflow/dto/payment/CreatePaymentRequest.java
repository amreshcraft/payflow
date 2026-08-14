package amreshmaurya.com.payflow.dto.payment;


import java.math.BigDecimal;

public record CreatePaymentRequest(
        String receiverPhone,
        BigDecimal amount,
        String currency,
        String description
) {
}
