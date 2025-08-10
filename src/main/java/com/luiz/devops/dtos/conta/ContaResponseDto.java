package com.luiz.devops.dtos.conta;

import com.luiz.devops.dtos.movimentacoes.MovimentacoesResponseDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record ContaResponseDto(
        UUID id,
        List<MovimentacoesResponseDto> movimentacoes,
        BigDecimal saldo) {
}