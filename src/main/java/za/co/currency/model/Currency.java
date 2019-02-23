package za.co.currency.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "currency")
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "rate", nullable = false)
    private BigDecimal rate;

    @Column(name = "name")
    private String name;

    @Column(name = "exchange_timestamp", nullable = false)
    private LocalDateTime currencyTimeStamp;


}