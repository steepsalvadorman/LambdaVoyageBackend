package com.grupo03.Lambda_Voyage.infraestructure.helpers;

import com.grupo03.Lambda_Voyage.infraestructure.dtos.CurrencyDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Currency;

@Component
public class ApiCurrencyConnectorHelper {

    private final WebClient currencyWebClient;

    @Value(value = "${api.base-currency}")
    private String baseCurrency;

    public ApiCurrencyConnectorHelper(@Qualifier("base") WebClient currencyWebClient) {
        this.currencyWebClient = currencyWebClient;
    }


    private static final String BASE_CURRENCY_QUERY_PARAM = "base={base}";
    private static final String SYMBOL_CURRENCY_QUERY_PARAM = "&symbols={symbol}";
    private static final String CURRENCY_PATH = "/exchangerates_data/latest";



    public CurrencyDTO getCurrency(Currency currency){
        return this.currencyWebClient
                .get()
                .uri(uri->
                        uri.path(CURRENCY_PATH)
                        .query(BASE_CURRENCY_QUERY_PARAM)
                        .query(SYMBOL_CURRENCY_QUERY_PARAM)
                        .build(baseCurrency, currency.getCurrencyCode()))
                .retrieve()
                .bodyToMono(CurrencyDTO.class)
                .block();
    }
}
