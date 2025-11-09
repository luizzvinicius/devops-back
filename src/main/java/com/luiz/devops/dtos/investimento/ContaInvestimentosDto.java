package com.luiz.devops.dtos.investimento;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

public record ContaInvestimentosDto(
        UUID idConta,
        String tipoInvestimento,
        BigDecimal totalInvestido,
        Timestamp resgate
) {
}
