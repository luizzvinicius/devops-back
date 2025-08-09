package com.luiz.devops.dtos.conta;

import jakarta.validation.constraints.Positive;

public record ContaRequestDto(
        @Positive Long pessoaId
) {
}