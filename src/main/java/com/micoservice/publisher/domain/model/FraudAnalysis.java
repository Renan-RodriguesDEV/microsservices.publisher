package com.micoservice.publisher.domain.model;

import com.micoservice.publisher.domain.dto.enums.FraudType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class FraudAnalysis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long tranferencyId;
    private FraudType fraudType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public FraudAnalysis() {

    }

    public FraudAnalysis(Long tranferencyId, FraudType fraudType) {
        this.tranferencyId = tranferencyId;
        this.fraudType = fraudType;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FraudType getFraudType() {
        return fraudType;
    }

    public void setFraudType(FraudType fraudType) {
        this.fraudType = fraudType;
    }

    public Long getTranferencyId() {
        return tranferencyId;
    }

    public void setTranferencyId(Long tranferencyId) {
        this.tranferencyId = tranferencyId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @PrePersist
    private void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }

    @PreUpdate
    private void preUpdate() {
        if (this.updatedAt == null) {
            this.updatedAt = LocalDateTime.now();
        }
    }
}
