package za.co.currency.exception;


import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CurrencyGlobalExceptionHandler {

    @ExceptionHandler(CurrencyExchangeRateNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(CurrencyExchangeRateNotFoundException ex, WebRequest request) {
        CurrencyErrorDetails currencyErrorDetails = new CurrencyErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(currencyErrorDetails, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
        CurrencyErrorDetails currencyErrorDetails = new CurrencyErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(currencyErrorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

