package com.example.domain.model.mongo;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Getter
@Setter
@Document(collection = "indicators_request") // Nombre de la colección en MongoDB
public class IndicatorsRequest {

    @Id
    private String id;
    private String city;
    private String requestCurrency;
    private String requestText;
    private String responseText;
    private double valueResponseCurrency;
    private LocalDate date;
}