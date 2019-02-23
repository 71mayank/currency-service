package za.co.currency.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import za.co.currency.response.CurrencyExchangeRateResponse;
import za.co.currency.service.CurrencyServiceImpl;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CurrencyController.class)

public class CurrencyControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CurrencyServiceImpl currencyServiceImpl;

    @Test
    public void givenBaseCurrency_whenGetLatestExchangeRate_thenReturnNotFound() throws Exception {
        CurrencyExchangeRateResponse currencyExchangeRateResponse = new CurrencyExchangeRateResponse();
        given(currencyServiceImpl.getLatestRatesByBaseCurrency("BTC")).willReturn(currencyExchangeRateResponse);
        mvc.perform(get("/currency/getLatestRatesByBaseCurrency?baseCurrency=BTC")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


}
