package za.co.currency.repository;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import za.co.currency.model.Currency;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class CurrencyRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Test
    public void whenSaveCurrency_thenReturnCurrency() {
        // given
        Currency currency = buildCurrency();
        entityManager.persist(currency);
        entityManager.flush();
        // when
        Optional<Currency> found = currencyRepository.findById(currency.getId());
        // then
        String code = found.get().getCode();
        assertThat(code).isEqualTo(currency.getCode());
    }

    private Currency buildCurrency() {
        Currency currency = new Currency();
        currency.setName("US Dollar");
        currency.setCode("USD");
        currency.setRate(BigDecimal.valueOf(3601.9));
        currency.setCurrencyTimeStamp(LocalDateTime.now());
        return currency;
    }


}


