package com.luiz.devops.dtos.investimento;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateInvestimentoDto(
        @NotNull UUID idConta,
        @NotNull int tipoInvestimento,
        @NotNull @Positive BigDecimal aporte
) {
}