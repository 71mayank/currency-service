package za.co.currency.monitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import za.co.currency.exception.CurrencyExchangeRateNotFoundException;
import za.co.currency.response.CurrencyExchangeRateResponse;
import za.co.currency.service.CurrencyService;

@Component
public class CurrencyMonitor {

    @Autowired
    CurrencyService currencyService;

    @Value("${currency.service.url}")
    private String currencyServiceUrl;

    private static final String SUPPORTED_BASE_CURRENCY = "BTC";

    private static final Logger logger = LoggerFactory.getLogger(CurrencyMonitor.class);

    @Scheduled(fixedRateString = "${currency.service.check.interval}")
    public void testAsynch() throws CurrencyExchangeRateNotFoundException {
        logger.info("Getting Latest Exchange Rate - Check Interval {} milliseconds ",currencyServiceUrl);
        CurrencyExchangeRateResponse latestRates = currencyService.getLatestRatesByBaseCurrency(SUPPORTED_BASE_CURRENCY);
        logger.info(latestRates.getResponseMessage());
    }
}
