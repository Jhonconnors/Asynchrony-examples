package com.example.domain.model.lecto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TranslationRequest {
    private List<String> texts;
    private List<String> to;
    private String from;
}