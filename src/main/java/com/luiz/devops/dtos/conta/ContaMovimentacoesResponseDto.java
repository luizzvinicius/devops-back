package com.luiz.devops.dtos.conta;

import java.math.BigDecimal;
import java.util.List;

public record ContaMovimentacoesResponseDto(
        List<ContaMovimentacoesDto> contaMovimentacoes,
        BigDecimal saldo,
        long pageSize,
        long totalElements
) {
}