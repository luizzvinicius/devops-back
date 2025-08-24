package com.luiz.devops.dtos.pessoa;

import com.luiz.devops.dtos.conta.ContaMapper;
import com.luiz.devops.dtos.conta.ContaResponseDto;
import com.luiz.devops.models.Pessoa;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class PessoaMapper {
    private final ContaMapper contaMapper;

    public PessoaMapper(ContaMapper contaMapper) {
        this.contaMapper = contaMapper;
    }

    public PessoaResponseDto toDto(Pessoa pessoa) {
        List<ContaResponseDto> contas = Collections.emptyList();
        if (pessoa.getContas() != null) {
            contas = pessoa.getContas().stream().map(contaMapper::toDto).toList();
        }

        return new PessoaResponseDto(
                pessoa.getId(),
                pessoa.getNome(),
                pessoa.getCpf(),
                pessoa.getEndereco(),
                contas
        );
    }

    public CreatePessoaResponseDto toDtoCreatePessoa(Pessoa pessoa) {
        return new CreatePessoaResponseDto(
                pessoa.getId(),
                pessoa.getNome().toLowerCase(),
                pessoa.getCpf(),
                pessoa.getEndereco().toLowerCase()
        );
    }

    public Pessoa toEntity(PessoaRequestDto dto) {
        return new Pessoa(dto.nome(), dto.cpf(), dto.endereco());
    }
}