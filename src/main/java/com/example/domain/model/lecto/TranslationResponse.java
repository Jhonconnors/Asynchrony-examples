package com.example.domain.model.lecto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TranslationResponse {
    private List<Translation> translations;
    private String from;
    private int translatedCharacters;
}