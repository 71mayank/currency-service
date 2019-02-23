package za.co.currency.service;

import za.co.currency.exception.CurrencyExchangeRateNotFoundException;
import za.co.currency.model.Currency;
import za.co.currency.response.CurrencyExchangeRateResponse;
import za.co.currency.response.CurrencyHistoricalExchangeRateResponse;

import java.time.LocalDateTime;

public interface CurrencyService {

    Currency saveCurrency(Currency currency);

    CurrencyExchangeRateResponse getLatestRatesByBaseCurrency(String baseCurrency) throws CurrencyExchangeRateNotFoundException;

    CurrencyHistoricalExchangeRateResponse getHistoricalRatesByStartAndEndDate(LocalDateTime startDate, LocalDateTime endDate);
}
