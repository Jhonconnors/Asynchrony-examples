package com.example.domain.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class IndicatorsValidateRequest {

    @JsonProperty("currency_code")
    private String currencyCode;
    @JsonProperty("currency_value")
    private double currencyValue;
    @JsonProperty("texts_to_translate")
    private List<String> textsToTranslate;
    @JsonProperty("language_to_translate")
    private List<String> languageToTranslate;
    @JsonProperty("from_language")
    private String fromLanguage;

}
