package amreshmaurya.com.payflow.dto.payment;


import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class CreatePaymentRequest {

    @NotBlank
    private String receiverPhone;

    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal amount;
}
