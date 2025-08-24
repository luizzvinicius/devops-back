package com.luiz.devops.dtos.pessoa;

import java.util.List;

public record PessoaAndContaDtoResponse(
        List<PessoaAndContaDto> pessoaAndContaDtoList,
        long pageSize,
        long totalElements
) {}