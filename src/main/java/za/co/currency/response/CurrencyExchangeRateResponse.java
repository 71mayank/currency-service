package za.co.currency.response;

import lombok.Getter;
import lombok.Setter;
import za.co.currency.dto.CurrencyDTO;

@Getter
@Setter
public class CurrencyExchangeRateResponse {
    private String responseMessage;
    private CurrencyDTO currencyDTO;
}
