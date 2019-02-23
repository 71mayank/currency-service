package za.co.currency.dao;

import za.co.currency.model.Currency;

import java.time.LocalDateTime;
import java.util.List;

public interface CurrencyDAO {

    Currency saveCurrency(Currency currency);

    List<Currency> getAllCurrency();

    Currency getCurrencyById(Long currencyId);

    public void deleteCurrency(Long currencyId);

    List<Currency> getHistoricalRatesByStartAndEndDate(LocalDateTime startDate, LocalDateTime endDate);
}
