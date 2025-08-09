package com.luiz.devops.dtos.conta;

import com.luiz.devops.models.Conta;
import org.springframework.stereotype.Component;

@Component
public class ContaMapper {
    public ContaResponseDto toDto(Conta conta) {
        return new ContaResponseDto(
                conta.getId(),
                conta.getMovimentacoes(),
                conta.getSaldo()
        );
    }
}