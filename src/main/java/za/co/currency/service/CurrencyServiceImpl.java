package za.co.currency.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import za.co.currency.dao.CurrencyDAOImpl;
import za.co.currency.dto.CurrencyDTO;
import za.co.currency.exception.CurrencyExchangeRateNotFoundException;
import za.co.currency.model.Currency;
import za.co.currency.response.CurrencyExchangeRateResponse;
import za.co.currency.response.CurrencyHistoricalExchangeRateResponse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private static final Logger logger = LoggerFactory.getLogger(CurrencyServiceImpl.class);
    private static final String REQUIRED_EXCHANGE_RATE_FOR_CURRENCY = "USD";
    private static final String SUPPORTED_BASE_CURRENCY = "BTC";

    @Value("${currency.service.url}")
    private String currencyServiceUrl;
    private final RestTemplate restTemplate;

    @Autowired
    private CurrencyDAOImpl currencyDAOImpl;

    public CurrencyServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public CurrencyHistoricalExchangeRateResponse getHistoricalRatesByStartAndEndDate(LocalDateTime startDate, LocalDateTime endDate) {
        CurrencyHistoricalExchangeRateResponse currencyHistoricalExchangeRateResponse = new CurrencyHistoricalExchangeRateResponse();
        List<Currency> historicalRatesByStartAndEndDate = this.currencyDAOImpl.getHistoricalRatesByStartAndEndDate(startDate, endDate);
        if (!historicalRatesByStartAndEndDate.isEmpty()) {
            ArrayList<CurrencyDTO> currencyDTOS = new ArrayList<>();
            CurrencyDTO.builder().code("").build();
            historicalRatesByStartAndEndDate.forEach(currency -> currencyDTOS.add(CurrencyDTO.builder().code(currency.getCode()).name(currency.getName()).rate(currency.getRate()).time(currency.getCurrencyTimeStamp()).build()));
            currencyHistoricalExchangeRateResponse.setCurrencyDTOs(currencyDTOS);
            currencyHistoricalExchangeRateResponse.setResponseMessage("Successfully retrieved historical currency exchange rate between LocalDateTime  " + startDate + "And " + endDate);
            logger.info("{} HistoricalRatesByStartAndEndDate found between {}, and {}", historicalRatesByStartAndEndDate.size(), startDate, endDate);
        } else {
            logger.info("HistoricalRatesByStartAndEndDate Not Found in between {} and {} ", startDate, endDate);
            currencyHistoricalExchangeRateResponse.setResponseMessage("No historical currency exchange rate found between LocalDateTime  " + startDate + "And " + endDate);
        }
        return currencyHistoricalExchangeRateResponse;
    }

    @Override
    public CurrencyExchangeRateResponse getLatestRatesByBaseCurrency(String baseCurrency) throws CurrencyExchangeRateNotFoundException {
        CurrencyExchangeRateResponse currencyExchangeRateResponse = new CurrencyExchangeRateResponse();
        if(!baseCurrency.equals(SUPPORTED_BASE_CURRENCY)){
            throw new CurrencyExchangeRateNotFoundException("Exchange rate for "+baseCurrency +" is not supported at the moment");
        }
        CurrencyDTO[] currencyDTOs = restTemplate.getForObject(currencyServiceUrl, CurrencyDTO[].class);
        Optional<CurrencyDTO> usdExchangeRate = Arrays.stream(currencyDTOs).filter(currencyDTO -> currencyDTO.getCode().equals(REQUIRED_EXCHANGE_RATE_FOR_CURRENCY)).findFirst();
        if (usdExchangeRate.isPresent()) {
            currencyExchangeRateResponse.setCurrencyDTO(usdExchangeRate.get());
            currencyExchangeRateResponse.setResponseMessage("Successfully retrieved Current USD For 1 " + baseCurrency);
            this.saveCurrency(buildCurrencyWithExchangeRate(usdExchangeRate.get()));
        } else {
            currencyExchangeRateResponse.setResponseMessage("Current USD rate For 1 " + baseCurrency + " not found !");
        }
        return currencyExchangeRateResponse;
    }

    private Currency buildCurrencyWithExchangeRate(CurrencyDTO currencyDTO) {
        Currency c = new Currency();
        c.setRate(currencyDTO.getRate());
        c.setCode(currencyDTO.getCode());
        c.setName(currencyDTO.getName());
        c.setCurrencyTimeStamp(LocalDateTime.now());
        return c;
    }

    @Override
    public Currency saveCurrency(Currency currency) {
        return currencyDAOImpl.saveCurrency(currency);
    }


}
