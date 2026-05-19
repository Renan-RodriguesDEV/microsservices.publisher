package com.micoservice.publisher.domain.services;
import com.micoservice.publisher.domain.dto.request.FraudAnalysisDTO;
import com.micoservice.publisher.domain.model.FraudAnalysis;
import com.micoservice.publisher.domain.repositories.FraudAnalysisRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class FraudAnalysisService {
    private final FraudAnalysisRepository fraudAnalysisRepository;
    private final RestClient client;

    public FraudAnalysisService(FraudAnalysisRepository fraudAnalysisRepository, RestClient client) {
        this.fraudAnalysisRepository = fraudAnalysisRepository;
        this.client = client;
    }

    public FraudAnalysis findById(Long id) {
        return fraudAnalysisRepository.findById(id).orElse(null);
    }

    public List<FraudAnalysis> findAll() {
        return fraudAnalysisRepository.findAll();
    }

    public FraudAnalysis create(FraudAnalysisDTO fraudAnalysisDTO) {
        // faz post pra um serviço mock
        CompletableFuture.runAsync(()->client.post().uri("/send").retrieve().body(Object.class));
        return fraudAnalysisRepository.save(fraudAnalysisDTO.toEntity());
    }

    public FraudAnalysis update(Long id, FraudAnalysisDTO fraudAnalysisDTO) {
       return  null;
    }

    public void deleteById(Long id) {
        FraudAnalysis ledgerDB = this.findById(id);
        if (ledgerDB == null) return;
        fraudAnalysisRepository.delete(ledgerDB);
    }
}
