package com.luiz.devops.dtos.conta;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

public record ContaMovimentacoesDto(
        UUID contaId,
        long movimentacaoId,
        BigDecimal valor,
        LocalDateTime dataMovimentacao
) {
}