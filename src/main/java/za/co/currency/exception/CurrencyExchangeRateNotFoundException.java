package za.co.currency.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CurrencyExchangeRateNotFoundException extends Exception{
    private static final long serialVersionUID = 1L;
    public CurrencyExchangeRateNotFoundException(String message){
        super(message);
    }
}