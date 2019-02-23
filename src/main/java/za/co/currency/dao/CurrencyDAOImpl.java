package za.co.currency.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import za.co.currency.model.Currency;
import za.co.currency.repository.CurrencyRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Repository
public class CurrencyDAOImpl implements CurrencyDAO {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    public List<Currency> getAllCurrency() {
        return currencyRepository.findAll();
    }

    @Override
    public Currency getCurrencyById(Long currencyId) {
        return currencyRepository.findById(currencyId).get();
    }

    @Override
    public Currency saveCurrency(Currency currency) {
        return currencyRepository.save(currency);
    }

    @Override
    public void deleteCurrency(Long currencyId) {
        currencyRepository.deleteById(currencyId);
    }

    @Override
    public List<Currency> getHistoricalRatesByStartAndEndDate(LocalDateTime startDate, LocalDateTime endDate) {
        return currencyRepository.getHistoricalRatesByStartAndEndDate(startDate, endDate);
    }
}
