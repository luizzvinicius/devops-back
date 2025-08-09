package com.luiz.devops.dtos.conta;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record ContaRequestDto(
        @Positive Long pessoaId,
        @NotNull UUID numeroConta
) {
}