package za.co.currency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import za.co.currency.model.Currency;

import java.time.LocalDateTime;
import java.util.List;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    @Query("SELECT c FROM Currency c WHERE c.currencyTimeStamp BETWEEN :startDate AND :endDate")
    List<Currency> getHistoricalRatesByStartAndEndDate(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

}
