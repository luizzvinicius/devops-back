package com.luiz.devops.services;

import com.luiz.devops.dtos.pessoa.PessoaPageDto;
import com.luiz.devops.dtos.pessoa.PessoaRequestDto;
import com.luiz.devops.dtos.pessoa.PessoaResponseDto;
import com.luiz.devops.exceptions.RegistroNaoEncontradoException;
import com.luiz.devops.models.Conta;
import com.luiz.devops.models.Pessoa;
import com.luiz.devops.repositories.PessoaRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {
    private final PessoaRepository repository;

    public PessoaService(PessoaRepository repository) {
        this.repository = repository;
    }

    public PessoaResponseDto criarPessoa(PessoaRequestDto dto) {
        Pessoa createdPessoa = repository.save(new Pessoa(dto.nome(), dto.cpf(), dto.endereco()));
        List<Conta> contas = List.of();
        if (createdPessoa.getConta() != null) {
            contas = createdPessoa.getConta();
        }

        return new PessoaResponseDto(
                createdPessoa.getId(),
                createdPessoa.getNome(),
                createdPessoa.getCpf(),
                createdPessoa.getEndereco()
                , contas
        );
    }

    public PessoaPageDto buscarTodasPessoas(int p) {
        Page<Pessoa> page = repository.findAll(PageRequest.of(p, 10));

        if (page.isEmpty()) {
            throw new RegistroNaoEncontradoException("Pessoa");
        }

        List<PessoaResponseDto> pessoasResponse = page.get().map(pessoa -> new PessoaResponseDto(
                pessoa.getId(),
                pessoa.getNome(),
                pessoa.getCpf(),
                pessoa.getEndereco(),
                pessoa.getConta()
        )).toList();

        return new PessoaPageDto(pessoasResponse, page.getTotalPages(), page.getTotalElements());
    }

    public PessoaResponseDto atualizarPessoa(Long id, PessoaRequestDto dto) {
        Pessoa pessoa = repository.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException("Pessoa"));
        pessoa.setNome(dto.nome());
        pessoa.setEndereco(dto.endereco());
        Pessoa updatedPessoa = repository.save(pessoa);
        return new PessoaResponseDto(
                updatedPessoa.getId(),
                updatedPessoa.getNome(),
                updatedPessoa.getCpf(),
                updatedPessoa.getEndereco(),
                updatedPessoa.getConta()
        );
    }

    @Transactional
    public void deletarPessoa(Long id) {
        repository.deleteById(id);
    }
}