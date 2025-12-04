package com.luiz.devops.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Profile;

import com.luiz.devops.dtos.conta.ContaMapper;
import com.luiz.devops.dtos.conta.ContaRequestDto;
import com.luiz.devops.dtos.conta.ContaResponseDto;
import com.luiz.devops.exceptions.RegistroNaoEncontradoException;
import com.luiz.devops.models.Conta;
import com.luiz.devops.models.Pessoa;
import com.luiz.devops.repositories.ContaRepository;
import com.luiz.devops.repositories.PessoaRepository;

@Profile("test")
@ExtendWith(MockitoExtension.class)
class ContaServiceTest {
    @Mock
    PessoaRepository pessoaRepository;

    @Mock
    ContaRepository contaRepository;

    @Mock
    ContaMapper contaMapper;

    @InjectMocks
    ContaService contaService;

    @Test
    void deveCriarContaQuandoPessoaExiste() {
        Pessoa pessoa = setupPessoa();
        Conta conta = new Conta();
        conta.setPessoa(pessoa);

        ContaRequestDto contaRequestDto = new ContaRequestDto(1L);
        ContaResponseDto contaResponseDto = new ContaResponseDto(null, null, null);

        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));
        when(contaRepository.save(any(Conta.class))).thenReturn(conta);
        when(contaMapper.toDto(conta)).thenReturn(contaResponseDto);

        ContaResponseDto result = contaService.criarConta(contaRequestDto);
        assertNotNull(result);
        verify(pessoaRepository).findById(1L);
        verify(contaRepository).save(any(Conta.class));
        verify(contaMapper).toDto(conta);
    }

    @Test
    void deveLancarExcecaoQuandoPessoaNaoExiste() {
        ContaRequestDto requestDto = new ContaRequestDto(99L);
        when(pessoaRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RegistroNaoEncontradoException.class, () -> contaService.criarConta(requestDto));
    }

    Pessoa setupPessoa() {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("Luiz");
        pessoa.setCpf("78563122549");
        pessoa.setEndereco("rua tal");
        return pessoa;
    }
}