package com.example.domain.service;

import com.example.domain.mapper.ProcesscontextMapper;
import com.example.domain.model.IndicatorsValidateRequest;
import com.example.domain.model.IndicatorsValidateResponse;
import com.example.domain.model.dto.ProcessContext;
import com.example.domain.service.handler.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class HandlerExecutorService {

    private final List<Handler<ProcessContext>> handlerList;
    private final ProcesscontextMapper mapper;
    private final ListableBeanFactory beanFactory;
    private static final Logger logger = LoggerFactory.getLogger(HandlerExecutorService.class);
    private final ExecutorService executorService;

    public HandlerExecutorService(List<Handler<ProcessContext>> handlerList, ProcesscontextMapper mapper,
                                  ListableBeanFactory beanFactory) {
        this.handlerList = handlerList;
        this.mapper = mapper;
        this.beanFactory = beanFactory;
        int poolSize = Math.min(handlerList.size(), Runtime.getRuntime().availableProcessors());
        this.executorService = Executors.newFixedThreadPool(Math.max(poolSize, 1));
    }

    public IndicatorsValidateResponse executeHandlers(IndicatorsValidateRequest request){
        ProcessContext processContext = mapper.createContext(request);
        List<CompletableFuture<Void>> futures = new ArrayList<>();
        handlerList.forEach(handler -> {
            logger.info("Start Executing {}", handler.getClass().getSimpleName());
            String beanName = getBeanName(handler);
            if (beanName.equals("mongoHandler")){
                logger.info("Waiting for all async handlers to complete before executing MongoHandler...");
                CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
                logger.info("Executing MongoHandler synchronously...");
                handler.execute(processContext);

            }else {
                CompletableFuture<Void> future = CompletableFuture.runAsync(() -> handler.execute(processContext), executorService)
                        .exceptionally(ex -> {
                            logger.error("Error executing handler: {}", beanName, ex);
                            return null;
                        });
                futures.add(future);
            }

            logger.info("End Executing {}", handler.getClass().getSimpleName());
        });
        processContext.setResponse(mapper.createContextResponse(processContext));
        return processContext.getResponse();
    }

    private String getBeanName(Handler<ProcessContext> handler) {
        Map<String, ? extends Handler> beansOfType = beanFactory.getBeansOfType(Handler.class);
        return beansOfType.entrySet().stream()
                .filter(entry -> entry.getValue().equals(handler))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(handler.getClass().getSimpleName());
    }
}
