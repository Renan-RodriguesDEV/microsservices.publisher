package com.micoservice.publisher.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micoservice.publisher.domain.dto.request.FraudAnalysisDTO;
import com.micoservice.publisher.domain.model.FraudAnalysis;
import com.micoservice.publisher.domain.services.FraudAnalysisService;
import com.micoservice.publisher.messaging.RabbitMQProducer;

@RestController
@RequestMapping("/fraudes")
public class FraudAnalysisController {
    private final FraudAnalysisService fraudAnalysisService;
    private final RabbitMQProducer producer;

    public FraudAnalysisController(FraudAnalysisService fraudAnalysisService, RabbitMQProducer producer) {
        this.producer = producer;
        this.fraudAnalysisService = fraudAnalysisService;
    }

    @GetMapping("/{id}")
    public FraudAnalysis get(@PathVariable Long id) {
        return fraudAnalysisService.findById(id);
    }

    @GetMapping
    public List<FraudAnalysis> get() {
        return fraudAnalysisService.findAll();
    }

    @PutMapping("/{id}")
    public FraudAnalysis put(@PathVariable Long id, @RequestBody FraudAnalysisDTO data) {
        return fraudAnalysisService.update(id, data);
    }

    @PostMapping
    public FraudAnalysis post(@RequestBody FraudAnalysisDTO data) {
        producer.send(data);
        return fraudAnalysisService.create(data);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        fraudAnalysisService.deleteById(id);
    }
}
