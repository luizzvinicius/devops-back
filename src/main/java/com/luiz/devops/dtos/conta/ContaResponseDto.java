package com.luiz.devops.dtos.conta;

import com.luiz.devops.models.Movimentacoes;

import java.util.List;
import java.util.UUID;

public record ContaResponseDto(
        UUID id,
        List<Movimentacoes> movimentacoes,
        double saldo) {
}