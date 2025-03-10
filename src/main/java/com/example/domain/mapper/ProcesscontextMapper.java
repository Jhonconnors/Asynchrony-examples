package com.example.domain.mapper;

import com.example.domain.model.IndicatorsValidateRequest;
import com.example.domain.model.IndicatorsValidateResponse;
import com.example.domain.model.dto.ProcessContext;
import com.example.domain.model.lecto.TranslationRequest;
import com.example.domain.model.mongo.IndicatorsRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ProcesscontextMapper {

    public ProcessContext createContext(IndicatorsValidateRequest request){
        ProcessContext processContext = new ProcessContext();
        processContext.setRequest(request);
        return processContext;
    }

    public TranslationRequest createTranslationRequest(ProcessContext context) {
        TranslationRequest request = new TranslationRequest();
        request.setTexts(context.getRequest().getTextsToTranslate());
        request.setTo(context.getRequest().getLanguageToTranslate());
        request.setFrom(context.getRequest().getFromLanguage());
        return request;
    }

    public IndicatorsRequest createMongoRequest(ProcessContext context) {
        IndicatorsRequest mongoRequest = new IndicatorsRequest();
        mongoRequest.setRequestText(context.getRequest().getTextsToTranslate().toString());
        mongoRequest.setRequestCurrency(context.getRequest().getCurrencyCode());
        mongoRequest.setCity("Santiago of Chile");
        mongoRequest.setValueResponseCurrency(context.getCurrencyResponse().getValor());
        String responseText = context.getTranslationResponse().getTranslations().getFirst().getTranslated().toString();
        mongoRequest.setResponseText(responseText);
        mongoRequest.setDate(LocalDate.now());
        return mongoRequest;
    }

    public IndicatorsValidateResponse createContextResponse(ProcessContext context) {
        IndicatorsValidateResponse response = new IndicatorsValidateResponse();

        response.setIndicatorResponse(context.getCurrencyResponse());
        response.setTranslationResponse(context.getTranslationResponse());
        response.setOperationId(context.getMongoRequest().getId());
        return response;
    }
}
