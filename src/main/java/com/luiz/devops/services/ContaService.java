package com.luiz.devops.services;

import com.luiz.devops.dtos.conta.ContaMapper;
import com.luiz.devops.dtos.conta.ContaRequestDto;
import com.luiz.devops.dtos.conta.ContaResponseDto;
import com.luiz.devops.exceptions.RegistroNaoEncontradoException;
import com.luiz.devops.models.Conta;
import com.luiz.devops.models.Pessoa;
import com.luiz.devops.repositories.ContaRepository;
import com.luiz.devops.repositories.PessoaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

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

        return mapper.toDto(createdConta);
    }

    public ContaResponseDto buscarContaPorId(UUID id) {
        var conta = repository.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException("Conta"));
        return mapper.toDto(conta);
    }

    @Transactional
    public void deleteConta(UUID id) {
        repository.deleteById(id);
    }
}