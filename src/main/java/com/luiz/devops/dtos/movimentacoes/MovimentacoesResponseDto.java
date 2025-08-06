package com.luiz.devops.dtos.movimentacoes;

import java.time.LocalDateTime;

public record MovimentacoesResponseDto(
        long id,
        double valor,
        LocalDateTime data
) {}