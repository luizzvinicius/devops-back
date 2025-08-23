package com.luiz.devops.dtos.pessoa;

import java.util.List;

public record PessoaAndContaDtoResponse(
        List<PessoaAndContaDto> pessoaAndContaDtoList,
        int page,
        long pageSize
) {}