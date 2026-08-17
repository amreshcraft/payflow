package amreshmaurya.com.payflow.dto.wallet;

import java.math.BigDecimal;
import java.util.UUID;

import amreshmaurya.com.payflow.entity.WalletStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class WalletResponse{
        UUID id;
        BigDecimal balance;
        String currency;
        WalletStatus status;
        Long version;
}

