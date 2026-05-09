package com.micoservice.publisher.domain.dto.request;

import com.micoservice.publisher.domain.dto.enums.FraudType;
import jakarta.validation.constraints.NotNull;

public record FraudAnalysisDTO(@NotNull Long transferencyId, @NotNull FraudType tipo) {
}