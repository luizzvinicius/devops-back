package com.luiz.devops.services;

import com.luiz.devops.dtos.conta.*;
import com.luiz.devops.exceptions.RegistroNaoEncontradoException;
import com.luiz.devops.models.Conta;
import com.luiz.devops.models.Pessoa;
import com.luiz.devops.repositories.ContaRepository;
import com.luiz.devops.repositories.PessoaRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class ContaService {
    private final PessoaRepository pessoaRepository;
    private final ContaRepository repository;
    private final ContaMapper mapper;

    public ContaService(PessoaRepository pessoaRepository, ContaRepository contaRepository, ContaMapper mapper) {
        this.pessoaRepository = pessoaRepository;
        this.repository = contaRepository;
        this.mapper = mapper;
    }

    @Transactional
    public ContaResponseDto criarConta(ContaRequestDto dto) {
        Pessoa pessoa = pessoaRepository.findById(dto.pessoaId())
                .orElseThrow(() -> new RegistroNaoEncontradoException("Pessoa"));

        var createdConta = repository.save(new Conta(pessoa));
        log.info("Conta de {} criada com sucesso", createdConta.getPessoa().getNome());
        return mapper.toDto(createdConta);
    }

    public ContaResponseDto buscarContaPorId(UUID id) {
        var conta = repository.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException("Conta"));
        return mapper.toDto(conta);
    }

    @Transactional
    public ContaMovimentacoesResponseDto getContaMovimentacoes(UUID contaId, int page) {
        Conta conta = repository.findById(contaId).orElseThrow(() -> new RegistroNaoEncontradoException("Conta"));
        Page<ContaMovimentacoesDto> result = repository.getContaMovimentacoes(
                contaId, PageRequest.of(page, 10)
        );

        List<ContaMovimentacoesDto> contaMovimentacoes = result.getContent();
        BigDecimal saldo = conta.getSaldo();
        log.info("Movimentação da conta {} buscadas", contaId);
        return new ContaMovimentacoesResponseDto(contaMovimentacoes, saldo, result.getSize(), result.getTotalElements());
    }

    @Transactional
    public void deleteConta(UUID id) {
        log.info("Conta {} deletada", id);
        repository.deleteById(id);
    }
}