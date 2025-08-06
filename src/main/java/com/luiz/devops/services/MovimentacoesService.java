package com.luiz.devops.services;

import org.springframework.stereotype.Service;

import teste.fiesc.conta.dtos.movimentacoes.MovimentacoesRequestDto;
import com.luiz.devops.enums.OperacaoEnum;
import com.luiz.devops.exceptions.OperacaoInvalidaException;
import com.luiz.devops.exceptions.RegistroNaoEncontradoException;
import com.luiz.devops.models.Conta;
import com.luiz.devops.models.Movimentacoes;
import com.luiz.devops.repositories.ContaRepository;
import com.luiz.devops.repositories.MovimentacoesRepository;

@Service
public class MovimentacoesService {
    private final MovimentacoesRepository repository;
    private final ContaRepository contaRepository;

    public MovimentacoesService(MovimentacoesRepository repository, ContaRepository contaRepository) {
        this.repository = repository;
        this.contaRepository = contaRepository;
    }

    public Movimentacoes criarMovimentacao(MovimentacoesRequestDto dto) {
        Conta conta = contaRepository.findById(dto.contaId()).orElseThrow(() -> new RegistroNaoEncontradoException("Conta"));
        OperacaoEnum tipoMovimentacao = OperacaoEnum.valueOf(dto.tipoMovimentacao());
        
        if (tipoMovimentacao == OperacaoEnum.SAQUE && dto.valor() > conta.getSaldo()) {
            throw new OperacaoInvalidaException();
        }

        double valor = switch(tipoMovimentacao) {
            case DEPOSITO -> dto.valor();
            case SAQUE -> -dto.valor();
        };

        conta.setSaldo(conta.getSaldo() + valor);
        contaRepository.save(conta);

        return repository.save(new Movimentacoes(conta, valor));
    }
}