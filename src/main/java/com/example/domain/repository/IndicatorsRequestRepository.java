package com.example.domain.repository;

import com.example.domain.model.mongo.IndicatorsRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IndicatorsRequestRepository extends MongoRepository<IndicatorsRequest, String> {
}