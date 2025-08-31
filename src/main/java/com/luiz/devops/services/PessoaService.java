package com.luiz.devops.services;

import com.luiz.devops.dtos.pessoa.*;
import com.luiz.devops.exceptions.RegistroExistenteException;
import com.luiz.devops.exceptions.RegistroNaoEncontradoException;
import com.luiz.devops.models.Pessoa;
import com.luiz.devops.repositories.PessoaRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PessoaService {
    private static final String CLASS_NAME = "Pessoa";
    private final PessoaRepository repository;
    private final PessoaMapper mapper;

    public PessoaService(PessoaRepository repository, PessoaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public CreatePessoaResponseDto criarPessoa(PessoaRequestDto dto) {
        repository.findByCpf(dto.cpf())
                .ifPresent(p -> {
                    throw new RegistroExistenteException(p.getNome(), CLASS_NAME);
                });

        Pessoa createdPessoa = repository.save(mapper.toEntity(dto));
        log.info("Pessoa {} criada com sucesso", createdPessoa.getNome());
        return mapper.toDtoCreatePessoa(createdPessoa);
    }

    public PessoaResponseDto buscarUmaPessoa(Long id) {
        Pessoa pessoa = repository.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException(CLASS_NAME));
        return mapper.toDto(pessoa);
    }

    public PessoaPageDto buscarTodasPessoas(int page) {
        Page<Pessoa> result = repository.findAll(PageRequest.of(page, 10));

        List<PessoaResponseDto> pessoasResponse = result.get().map(mapper::toDto).toList();

        return new PessoaPageDto(pessoasResponse, result.getTotalPages(), result.getTotalElements());
    }

    @Transactional
    public PessoaResponseDto atualizarPessoa(Long id, PessoaRequestDto dto) {
        Pessoa pessoa = repository.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException(CLASS_NAME));
        pessoa.setNome(dto.nome().toLowerCase());
        pessoa.setEndereco(dto.endereco().toLowerCase());
        Pessoa updatedPessoa = repository.save(pessoa);
        log.info("Pessoa {} atualizada com sucesso", updatedPessoa.getNome());
        return mapper.toDto(updatedPessoa);
    }

    public PessoaAndContaDtoResponse buscarPessoaEConta(int id, int page) {
        Page<PessoaAndContaDto> pessoaEContasQuery = repository.findPessoaAndConta(id, PageRequest.of(page, 10));
        log.info("Contas da pessoa com id {} buscadas", id);
        return new PessoaAndContaDtoResponse(
                pessoaEContasQuery.getContent(),
                pessoaEContasQuery.getNumberOfElements(),
                pessoaEContasQuery.getTotalElements()
        );
    }

    @Transactional
    public void deletarPessoa(Long id) {
        log.info("Pessoa de id {} deletada", id);
        repository.deleteById(id);
    }

    public PessoaPageDto buscarPessoasFilter(String nome, int page) {
        Page<Pessoa> pessoasFiltered = repository.findAllByNomeContains(nome, PageRequest.of(page, 10));
        List<PessoaResponseDto> pessoasResponse = pessoasFiltered.get().map(mapper::toDto).toList();
        log.info("Buscando pessoa {}, resultado {}", nome, pessoasResponse);
        return new PessoaPageDto(pessoasResponse, pessoasFiltered.getTotalPages(), pessoasFiltered.getTotalElements());
    }
}