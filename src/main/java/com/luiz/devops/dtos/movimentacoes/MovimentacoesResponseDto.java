package teste.fiesc.conta.dtos.movimentacoes;

import java.time.LocalDateTime;

public record MovimentacoesResponseDto(
    long id,
    double valor,
    LocalDateTime data
) {
    
}
