package za.co.currency.controller;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import za.co.currency.exception.CurrencyExchangeRateNotFoundException;
import za.co.currency.response.CurrencyExchangeRateResponse;
import za.co.currency.response.CurrencyHistoricalExchangeRateResponse;
import za.co.currency.service.CurrencyServiceImpl;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("currency")
@Api(value = "Currency Service ", description = "Operations related to currency exchange rates")
public class CurrencyController {

    @Autowired
    CurrencyServiceImpl currencyServiceImpl;

    @ApiOperation(value = "View latest currency rates", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved latest exchange rate for BTC"),
            @ApiResponse(code = 401, message = "You are not authorized to view the currency exchange rates"),
            @ApiResponse(code = 403, message = "Access to the currency exchange you were trying is forbidden"),
            @ApiResponse(code = 404, message = "The currency exchange rate you were trying to reach is not found")
    })
    @GetMapping(value = "/getLatestRates", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CurrencyExchangeRateResponse> getLatestRates(@ApiParam(value = "Default BTC", required = true) @RequestParam(value = "baseCurrency", defaultValue = "BTC") String baseCurrency) throws CurrencyExchangeRateNotFoundException {
        return new ResponseEntity<>(currencyServiceImpl.getLatestRatesByBaseCurrency(baseCurrency), HttpStatus.OK);
    }

    @ApiOperation(value = "View historical currency exchange rates for specified start and end Date", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved historical exchange rate for BTC"),
            @ApiResponse(code = 401, message = "You are not authorized to view the currency exchange rates"),
            @ApiResponse(code = 403, message = "Access to the currency exchange you were trying is forbidden"),
            @ApiResponse(code = 404, message = "The currency exchange rate you were trying to reach is not found")
    })
    @GetMapping("/getHistoricalRatesByDate")
    public ResponseEntity<CurrencyHistoricalExchangeRateResponse> getHistoricalRatesByDate(@ApiParam(value = "LocalDateTime Format YYYY-MM-DDTHH:MM:SS", required = true) @RequestParam(value = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                                                                           @ApiParam(value = "LocalDateTime Format YYYY-MM-DDTHH:MM:SS", required = true) @RequestParam(value = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return new ResponseEntity<>(currencyServiceImpl.getHistoricalRatesByStartAndEndDate(startDate, endDate), HttpStatus.OK);
    }
}
