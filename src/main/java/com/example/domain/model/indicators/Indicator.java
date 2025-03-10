package com.example.domain.model.indicators;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Indicator {

    private String codigo;
    private String nombre;
    @JsonProperty("unidad_medida")
    private String unidadMedida;
    private String fecha;
    private double valor;
}
