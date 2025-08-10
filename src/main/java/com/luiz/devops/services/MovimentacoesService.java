package com.luiz.devops.services;

import com.luiz.devops.dtos.movimentacoes.MovimentacoesMapper;
import com.luiz.devops.dtos.movimentacoes.MovimentacoesRequestDto;
import com.luiz.devops.dtos.movimentacoes.MovimentacoesResponseDto;
import com.luiz.devops.enums.OperacaoEnum;
import com.luiz.devops.exceptions.OperacaoInvalidaException;
import com.luiz.devops.exceptions.RegistroNaoEncontradoException;
import com.luiz.devops.models.Conta;
import com.luiz.devops.models.Movimentacoes;
import com.luiz.devops.repositories.ContaRepository;
import com.luiz.devops.repositories.MovimentacoesRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class MovimentacoesService {
    private final MovimentacoesRepository repository;
    private final ContaRepository contaRepository;
    private final MovimentacoesMapper mapper;

    public MovimentacoesService(MovimentacoesRepository repository, ContaRepository contaRepository,
                                MovimentacoesMapper mapper) {
        this.repository = repository;
        this.contaRepository = contaRepository;
        this.mapper = mapper;
    }

    @Transactional
    public MovimentacoesResponseDto criarMovimentacao(MovimentacoesRequestDto dto) {
        Conta conta = contaRepository.findById(dto.contaId())
                .orElseThrow(() -> new RegistroNaoEncontradoException("Conta"));

        OperacaoEnum tipoMovimentacao = OperacaoEnum.valueOf(dto.tipoMovimentacao());
        if (tipoMovimentacao == OperacaoEnum.SAQUE && (dto.valor().compareTo(conta.getSaldo()) > 0)) {
            throw new OperacaoInvalidaException();
        }

        BigDecimal valor = switch (tipoMovimentacao) {
            case DEPOSITO -> dto.valor();
            case SAQUE -> dto.valor().negate();
        };

        conta.setSaldo(conta.getSaldo().add(valor));
        contaRepository.save(conta);

        return mapper.toDto(repository.save(new Movimentacoes(conta, valor)));
    }
}