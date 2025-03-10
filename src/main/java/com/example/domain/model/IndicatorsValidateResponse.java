package com.example.domain.model;


import com.example.domain.model.indicators.Indicator;
import com.example.domain.model.lecto.TranslationResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IndicatorsValidateResponse {

    private String operationId;
    private Indicator indicatorResponse;
    private TranslationResponse translationResponse;
}
