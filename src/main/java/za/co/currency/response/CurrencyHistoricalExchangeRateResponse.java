package za.co.currency.response;

import lombok.Getter;
import lombok.Setter;
import za.co.currency.dto.CurrencyDTO;

import java.util.List;

@Getter
@Setter
public class CurrencyHistoricalExchangeRateResponse {
    private String responseMessage;
    private List<CurrencyDTO> currencyDTOs;
}
