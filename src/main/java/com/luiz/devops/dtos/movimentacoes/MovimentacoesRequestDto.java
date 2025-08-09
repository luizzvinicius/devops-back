package com.luiz.devops.dtos.movimentacoes;

import com.luiz.devops.enums.OperacaoEnum;
import com.luiz.devops.enums.ValueOfEnum;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record MovimentacoesRequestDto(
        @NotNull UUID contaId,
        @NotNull double valor,
        @ValueOfEnum(enumClass = OperacaoEnum.class) String tipoMovimentacao
) {
}