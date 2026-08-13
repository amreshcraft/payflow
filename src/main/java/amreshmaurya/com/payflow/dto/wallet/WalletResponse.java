package amreshmaurya.com.payflow.dto.wallet;

import java.math.BigDecimal;
import java.util.UUID;

import amreshmaurya.com.payflow.entity.WalletStatus;

public record WalletResponse(
        UUID id,
        BigDecimal balance,
        String currency,
        WalletStatus status,
        Long version
) 
{
        
}
