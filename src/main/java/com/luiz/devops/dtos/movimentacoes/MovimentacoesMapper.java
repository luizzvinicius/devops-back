package com.luiz.devops.dtos.movimentacoes;

import com.luiz.devops.models.Movimentacoes;
import org.springframework.stereotype.Component;

@Component
public class MovimentacoesMapper {
    public MovimentacoesResponseDto toDto(Movimentacoes movimentacoes) {
        return new MovimentacoesResponseDto(movimentacoes.getId(), movimentacoes.getValor(), movimentacoes.getData());
    }
}