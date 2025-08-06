package com.luiz.devops.dtos.conta;

import java.util.List;

import com.luiz.devops.models.Movimentacoes;

public record ContaResponseDto(
        long id,
        List<Movimentacoes> movimentacoes,
        String numero,
        double saldo) {
}