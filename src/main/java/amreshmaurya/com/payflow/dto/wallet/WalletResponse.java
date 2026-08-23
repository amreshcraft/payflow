package amreshmaurya.com.payflow.dto.wallet;

import java.math.BigDecimal;
import java.util.UUID;

import amreshmaurya.com.payflow.entity.WalletStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WalletResponse{
        UUID id;
        BigDecimal balance;
        String currency;
        WalletStatus status;
//        Long version;
}

