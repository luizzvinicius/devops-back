package com.luiz.devops.dtos.pessoa;

import java.math.BigDecimal;
import java.util.UUID;

public record PessoaAndContaDto(
        long pessoa_id,
        String pessoa_nome,
        String pessoa_cpf,
        String pessoa_endereco,
        UUID conta_id,
        BigDecimal conta_saldo
) {
}