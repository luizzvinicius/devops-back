package teste.fiesc.conta.dtos.movimentacoes;

import jakarta.validation.constraints.NotNull;
import com.luiz.devops.enums.OperacaoEnum;
import com.luiz.devops.enums.ValueOfEnum;

public record MovimentacoesRequestDto(
    @NotNull long contaId,
    @NotNull double valor,
    @ValueOfEnum(enumClass = OperacaoEnum.class) String tipoMovimentacao
) {}