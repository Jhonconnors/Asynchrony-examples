package com.example.domain.service.handler;

import com.example.domain.mapper.ProcesscontextMapper;
import com.example.domain.model.dto.ProcessContext;
import com.example.domain.model.lecto.TranslationRequest;
import com.example.domain.model.lecto.TranslationResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Order(2)
public class TranslationHandler implements Handler<ProcessContext> {

    private final RestTemplate restTemplate;
    private final ProcesscontextMapper mapper;

    public TranslationHandler(@Qualifier("translateRestTemplate")RestTemplate restTemplate,
                              ProcesscontextMapper mapper) {
        this.restTemplate = restTemplate;
        this.mapper = mapper;
    }

    @Override
    public void execute(ProcessContext context) {
        TranslationRequest translationRequest = mapper.createTranslationRequest(context);
        context.setTranslationRequest(translationRequest);
        context.setTranslationResponse(restTemplate.postForObject("/v1/translate/text", translationRequest, TranslationResponse.class));
    }
}
