package com.example.domain.model.indicators;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IndicatorResponse {

    private Indicator uf;
    private Indicator utm;
    private Indicator dolar;
    private Indicator euro;
    private Indicator bitcoin;
}
