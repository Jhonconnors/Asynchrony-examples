package com.example.domain.service.handler;

import com.example.domain.model.dto.ProcessContext;
import com.example.domain.model.indicators.Indicator;
import com.example.domain.model.indicators.IndicatorResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Order(1)
public class IndicatorHandler implements Handler<ProcessContext> {

    private final RestTemplate restTemplate;

    public IndicatorHandler(@Qualifier("indicatorRestTemplate")RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void execute(ProcessContext context) {
        context.setIndicatorResponse(restTemplate.getForObject("/api", IndicatorResponse.class));
        String currencyCode = context.getRequest().getCurrencyCode();
        switchCurrency(context, currencyCode);

    }

    private void switchCurrency(ProcessContext context, String currencyCode){
        switch (currencyCode){
            case "USD"
                    -> context.setCurrencyResponse(context.getIndicatorResponse().getDolar());
            case "UF"
                    -> context.setCurrencyResponse(context.getIndicatorResponse().getUf());
            case "EUR"
                    -> context.setCurrencyResponse(context.getIndicatorResponse().getEuro());
            case "UTM"
                    -> context.setCurrencyResponse(context.getIndicatorResponse().getUtm());
            case "BTC"
                    -> context.setCurrencyResponse(context.getIndicatorResponse().getBitcoin());
            default -> {
                Indicator defaultIndicator = new Indicator();
                defaultIndicator.setCodigo("Error");
                context.setCurrencyResponse(defaultIndicator);
            }
        }
    }
}
