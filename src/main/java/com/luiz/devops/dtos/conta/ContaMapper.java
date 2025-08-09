package com.luiz.devops.dtos.conta;

import com.luiz.devops.dtos.movimentacoes.MovimentacoesMapper;
import com.luiz.devops.dtos.movimentacoes.MovimentacoesResponseDto;
import com.luiz.devops.models.Conta;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class ContaMapper {
    private final MovimentacoesMapper movimentacoesMapper;

    public ContaMapper(MovimentacoesMapper movimentacoesMapper) {
        this.movimentacoesMapper = movimentacoesMapper;
    }

    public ContaResponseDto toDto(Conta conta) {
        List<MovimentacoesResponseDto> movimentacaoes = Collections.emptyList();
        if (conta.getMovimentacoes() != null) {
            movimentacaoes = conta.getMovimentacoes().stream().map(movimentacoesMapper::toDto).toList();
        }
        return new ContaResponseDto(
                conta.getId(),
                movimentacaoes,
                conta.getSaldo()
        );
    }
}