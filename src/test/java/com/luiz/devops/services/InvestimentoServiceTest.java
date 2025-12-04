package com.luiz.devops.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Profile;

import com.luiz.devops.exceptions.RegistroNaoEncontradoException;
import com.luiz.devops.models.Investimentos;
import com.luiz.devops.repositories.ContaRepository;
import com.luiz.devops.repositories.InvestimentoRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Profile("test")
@ExtendWith(MockitoExtension.class)
class InvestimentoServiceTest {
    @Mock
    InvestimentoRepository investimentoRepository;

    @Mock
    ContaRepository contaRepository;

    @InjectMocks
    InvestimentoService investimentoService;

    int idInvestimento = 1;

    @Test
    void SaqueInvestimentoSucesso() {
        Investimentos investimento = setupInvestimento();
        BigDecimal valorSaque = new BigDecimal("200.00");
        investimento.setDataInicio(LocalDateTime.now().minusYears(2));

        when(investimentoRepository.findById(idInvestimento)).thenReturn(Optional.of(investimento));
        when(investimentoRepository.save(any(Investimentos.class))).thenReturn(investimento);

        investimentoService.saqueInvestimento(idInvestimento, valorSaque);

        verify(investimentoRepository).save(investimento);
        assertEquals(new BigDecimal("800.00"), investimento.getValor());
    }

    @Test
    void testSaqueInvestimento_investimentoNaoEncontrado() {
        BigDecimal valorSaque = new BigDecimal("100.00");
        when(investimentoRepository.findById(idInvestimento)).thenReturn(Optional.empty());
        assertThrows(RegistroNaoEncontradoException.class, () -> {
            investimentoService.saqueInvestimento(idInvestimento, valorSaque);
        });
    }

    @Test
    void testSaqueInvestimento_valorInsuficiente() {
        BigDecimal valorSaque = new BigDecimal("2000");
        Investimentos investimento = setupInvestimento();
        investimento.setDataInicio(LocalDateTime.now().minusYears(2));
        when(investimentoRepository.findById(idInvestimento)).thenReturn(Optional.of(investimento));
        assertThrows(RuntimeException.class, () -> {
            investimentoService.saqueInvestimento(idInvestimento, valorSaque);
        });
    }

    Investimentos setupInvestimento() {
        Investimentos investimento = new Investimentos();
        BigDecimal valorInicial = new BigDecimal("1000.00");
        investimento.setId(idInvestimento);
        investimento.setTipo("Renda fixa 10%");
        investimento.setValor(valorInicial);
        return investimento;
    }
}