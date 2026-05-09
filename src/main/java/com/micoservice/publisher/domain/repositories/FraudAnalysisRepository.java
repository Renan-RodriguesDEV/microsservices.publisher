package com.micoservice.publisher.domain.repositories;

import com.micoservice.publisher.domain.model.FraudAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FraudAnalysisRepository extends JpaRepository<FraudAnalysis, Long> {
}

