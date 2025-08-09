package com.luiz.devops.dtos.pessoa;

import com.luiz.devops.dtos.conta.ContaResponseDto;

import java.util.List;

public record PessoaResponseDto(
        Long id,
        String nome,
        String cpf,
        String endereco,
        List<ContaResponseDto> conta
) {
}