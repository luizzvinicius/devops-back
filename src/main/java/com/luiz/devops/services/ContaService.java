package com.luiz.devops.services;

import com.luiz.devops.dtos.conta.ContaRequestDto;
import com.luiz.devops.dtos.conta.ContaResponseDto;
import com.luiz.devops.exceptions.RegistroExistenteException;
import com.luiz.devops.exceptions.RegistroNaoEncontradoException;
import com.luiz.devops.models.Conta;
import com.luiz.devops.models.Pessoa;
import com.luiz.devops.repositories.ContaRepository;
import com.luiz.devops.repositories.PessoaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ContaService {
    private final PessoaRepository pessoaRepository;
    private final ContaRepository repository;

    public ContaService(PessoaRepository pessoaRepository, ContaRepository contaRepository) {
        this.pessoaRepository = pessoaRepository;
        this.repository = contaRepository;
    }

    @Transactional
    public ContaResponseDto criarConta(ContaRequestDto dto) {
        Pessoa pessoa = pessoaRepository.findById(dto.pessoaId())
                .orElseThrow(() -> new RegistroNaoEncontradoException("Pessoa"));

        var numeroConta = repository.findByNumero(dto.numeroConta()).isPresent();
        if (numeroConta) {
            throw new RegistroExistenteException("Número da conta", "Conta");
        }

        var createdConta = repository.save(new Conta(pessoa, dto.numeroConta()));

        return new ContaResponseDto(
                createdConta.getId(),
                createdConta.getMovimentacoes(),
                createdConta.getNumero(),
                createdConta.getSaldo()
        );
    }

    public ContaResponseDto buscarContaPorId(Long id) {
        var conta = repository.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException("Conta"));
        return new ContaResponseDto(conta.getId(),
                conta.getMovimentacoes(),
                conta.getNumero(),
                conta.getSaldo()
        );
    }

    public ContaResponseDto atualizarContaPorId(Long id, ContaRequestDto contaRequestDto) {
        var conta = repository.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException("Conta"));
        conta.setNumero(contaRequestDto.numeroConta());
        var updatedConta = repository.save(conta);

        return new ContaResponseDto(updatedConta.getId(),
                updatedConta.getMovimentacoes(),
                updatedConta.getNumero(),
                updatedConta.getSaldo()
        );
    }
}