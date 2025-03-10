package com.example.domain.service.handler;

import com.example.domain.mapper.ProcesscontextMapper;
import com.example.domain.model.dto.ProcessContext;
import com.example.domain.model.mongo.IndicatorsRequest;
import com.example.domain.repository.IndicatorsRequestRepository;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
@Order(3)
public class MongoHandler implements Handler<ProcessContext>{

    private final IndicatorsRequestRepository repository;
    private final ProcesscontextMapper mapper;

    public MongoHandler(IndicatorsRequestRepository repository, ProcesscontextMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public void execute(ProcessContext context) {
        IndicatorsRequest mongoRequest = mapper.createMongoRequest(context);
        context.setMongoRequest(mongoRequest);
        repository.save(mongoRequest);
    }
}
