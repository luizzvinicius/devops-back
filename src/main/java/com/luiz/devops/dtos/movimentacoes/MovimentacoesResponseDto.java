package com.luiz.devops.dtos.movimentacoes;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record MovimentacoesResponseDto(
        long id,
        BigDecimal valor,
        LocalDateTime data
) {}