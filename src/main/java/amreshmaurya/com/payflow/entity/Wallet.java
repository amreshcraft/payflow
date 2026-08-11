package amreshmaurya.com.payflow.entity;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.text.DecimalFormat;


@Entity
@Table(name = "wallets")
public class Wallet extends BaseEntity {

    @Column(unique = true)
    @OneToOne
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User user;
    BigDecimal amount;
    String currency;
    String status;
    @Version
    private Long version;
}
