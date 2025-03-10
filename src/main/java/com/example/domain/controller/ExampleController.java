package com.example.domain.controller;


import com.example.domain.model.IndicatorsValidateRequest;
import com.example.domain.model.IndicatorsValidateResponse;
import com.example.domain.service.HandlerExecutorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2")
public class ExampleController {

    private final HandlerExecutorService executor;

    public ExampleController(HandlerExecutorService executor) {
        this.executor = executor;
    }

    @PostMapping("/validate-indicators")
    public ResponseEntity<IndicatorsValidateResponse> validateIndicators
            (@RequestBody IndicatorsValidateRequest request){
        IndicatorsValidateResponse response = executor.executeHandlers(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
