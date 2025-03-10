package com.example.domain.model.lecto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Translation {
    private String to;
    private List<String> translated;
}