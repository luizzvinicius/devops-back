package com.luiz.devops.dtos.pessoa;

import java.math.BigDecimal;
import java.util.UUID;

public record PessoaAndContaDto(
        long id,
        String nome,
        String cpf,
        String endereco,
        UUID conta_id,
        BigDecimal conta_saldo
) {
}