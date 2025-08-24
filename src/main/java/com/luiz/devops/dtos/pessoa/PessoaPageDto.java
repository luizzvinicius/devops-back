package com.luiz.devops.dtos.pessoa;

import java.util.List;

public record PessoaPageDto(
    List<PessoaResponseDto> pessoas,
    long pageSize,
    long totalElements
) {}