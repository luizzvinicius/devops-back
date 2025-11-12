package com.luiz.devops.dtos.investimento;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

public record ContaInvestimentosDto(
        UUID idConta,
        int idInvestimento,
        String tipoInvestimento,
        BigDecimal totalInvestido,
        BigDecimal taxa,
        Timestamp dataInicio,
        Timestamp resgate
) {
}
