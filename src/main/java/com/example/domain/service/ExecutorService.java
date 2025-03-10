package com.example.domain.service;

import com.example.domain.mapper.ProcesscontextMapper;
import com.example.domain.model.IndicatorsValidateRequest;
import com.example.domain.model.IndicatorsValidateResponse;
import com.example.domain.model.dto.ProcessContext;
import com.example.domain.service.handler.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExecutorService {

    private final List<Handler<ProcessContext>> handlerList;
    private final ProcesscontextMapper mapper;
    private static final Logger logger = LoggerFactory.getLogger(ExecutorService.class);

    public ExecutorService(List<Handler<ProcessContext>> handlerList, ProcesscontextMapper mapper) {
        this.handlerList = handlerList;
        this.mapper = mapper;
    }

    public IndicatorsValidateResponse executeHandlers(IndicatorsValidateRequest request){
        ProcessContext processContext = mapper.createContext(request);
        handlerList.forEach(handler -> {
            logger.info("Start Executing {}", handler.getClass().getSimpleName());
            handler.execute(processContext);
            logger.info("End Executing {}", handler.getClass().getSimpleName());
        });
        processContext.setResponse(mapper.createContextResponse(processContext));
        return processContext.getResponse();
    }
}
