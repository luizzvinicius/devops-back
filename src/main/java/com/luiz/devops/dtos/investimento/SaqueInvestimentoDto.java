package com.luiz.devops.dtos.investimento;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record SaqueInvestimentoDto(
        @NotNull BigDecimal saque
) {
}
