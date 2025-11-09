package com.luiz.devops.dtos.investimento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record InvestimentoResponseDto(
        int idInvestimento,
        String tipoInvestimento,
        BigDecimal totalInvestido,
        LocalDateTime resgate
) {
}
