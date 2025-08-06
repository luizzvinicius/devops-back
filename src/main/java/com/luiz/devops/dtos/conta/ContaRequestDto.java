package teste.fiesc.conta.dtos.conta;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ContaRequestDto(
    @Positive Long pessoaId,
    @NotNull String numeroConta
) {}