package com.luiz.devops.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Profile;

import com.luiz.devops.dtos.movimentacoes.MovimentacoesMapper;
import com.luiz.devops.dtos.movimentacoes.MovimentacoesRequestDto;
import com.luiz.devops.exceptions.OperacaoInvalidaException;
import com.luiz.devops.models.Conta;
import com.luiz.devops.repositories.ContaRepository;
import com.luiz.devops.repositories.MovimentacoesRepository;
import static org.assertj.core.api.Assertions.assertThat;

@Profile("test")
@ExtendWith(MockitoExtension.class)
class MovimentacoesServiceTest {
    @Mock
    MovimentacoesRepository movimentacoesRepository;

    @Mock
    ContaRepository contaRepository;

    @Mock
    MovimentacoesMapper movimentacoesMapper;

    @InjectMocks
    MovimentacoesService movimentacoesService;

    UUID idConta = UUID.randomUUID();

    @Test
    void criarMovimentacaoSaqueValido() {
        Conta conta = setupConta();
        BigDecimal valorSaque = new BigDecimal(50);
        MovimentacoesRequestDto movimentacoesRequestDto = new MovimentacoesRequestDto(idConta, valorSaque, "SAQUE");

        when(contaRepository.findById(idConta)).thenReturn(Optional.of(conta));
        when(contaRepository.save(any(Conta.class))).thenReturn(conta);
        when(movimentacoesMapper.toDto(any())).thenReturn(null);

        movimentacoesService.criarMovimentacao(movimentacoesRequestDto);

        BigDecimal saldoEsperado = new BigDecimal(5000).subtract(valorSaque);
        assertNotNull(conta);
        verify(contaRepository).findById(idConta);
        verify(contaRepository).save(conta);
        verify(movimentacoesRepository).save(any());
        assertThat(saldoEsperado).isEqualTo(conta.getSaldo());
    }

    @Test
    void criarMovimentacaoSaqueInvalido() {
        Conta conta = setupConta();
        BigDecimal valorSaque = new BigDecimal(999999999);
        MovimentacoesRequestDto movimentacoesRequestDto = new MovimentacoesRequestDto(idConta, valorSaque, "SAQUE");

        when(contaRepository.findById(idConta)).thenReturn(Optional.of(conta));

        assertThrows(
                OperacaoInvalidaException.class,
                () -> movimentacoesService.criarMovimentacao(movimentacoesRequestDto));
        verify(contaRepository).findById(idConta);
    }

    Conta setupConta() {
        Conta conta = new Conta();
        conta.setId(idConta);
        conta.setSaldo(new BigDecimal(5000));
        return conta;
    }
}