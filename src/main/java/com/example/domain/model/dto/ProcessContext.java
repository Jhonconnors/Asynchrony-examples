package com.example.domain.model.dto;


import com.example.domain.model.IndicatorsValidateRequest;
import com.example.domain.model.IndicatorsValidateResponse;
import com.example.domain.model.indicators.Indicator;
import com.example.domain.model.indicators.IndicatorResponse;
import com.example.domain.model.lecto.TranslationRequest;
import com.example.domain.model.lecto.TranslationResponse;
import com.example.domain.model.mongo.IndicatorsRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProcessContext {
    private TranslationRequest translationRequest;
    private TranslationResponse translationResponse;
    private IndicatorsValidateRequest request;
    private IndicatorsValidateResponse response;
    private IndicatorResponse indicatorResponse;
    private Indicator currencyResponse;
    private IndicatorsRequest mongoRequest;
    private String ipClient;

}
