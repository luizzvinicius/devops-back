package com.luiz.devops.dtos.pessoa;

public record CreatePessoaResponseDto(
        Long id,
        String nome,
        String cpf,
        String endereco
) {
}