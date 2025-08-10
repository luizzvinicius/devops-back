package com.luiz.devops.dtos.movimentacoes;

import com.luiz.devops.enums.OperacaoEnum;
import com.luiz.devops.enums.ValueOfEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;

public record MovimentacoesRequestDto(
        @NotNull UUID contaId,
        @NotNull @Positive BigDecimal valor,
        @ValueOfEnum(enumClass = OperacaoEnum.class) String tipoMovimentacao
) {
}