package teste.fiesc.conta.dtos.pessoa;

import jakarta.validation.constraints.NotBlank;

public record PessoaRequestDto(
        @NotBlank String nome,
        @NotBlank String cpf,
        @NotBlank String endereco) {
}