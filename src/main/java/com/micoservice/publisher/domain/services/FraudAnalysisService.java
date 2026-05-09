package com.micoservice.publisher.domain.services;
import com.micoservice.publisher.domain.dto.request.FraudAnalysisDTO;
import com.micoservice.publisher.domain.model.FraudAnalysis;
import com.micoservice.publisher.domain.repositories.FraudAnalysisRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FraudAnalysisService {
    private final FraudAnalysisRepository fraudAnalysisRepository;

    public FraudAnalysisService(FraudAnalysisRepository fraudAnalysisRepository) {
        this.fraudAnalysisRepository = fraudAnalysisRepository;
    }

    public FraudAnalysis findById(Long id) {
        return fraudAnalysisRepository.findById(id).orElse(null);
    }

    public List<FraudAnalysis> findAll() {
        return fraudAnalysisRepository.findAll();
    }

    public FraudAnalysis create(FraudAnalysisDTO fraudAnalysisDTO) {
        return null;
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
