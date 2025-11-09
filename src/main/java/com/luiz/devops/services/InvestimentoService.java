package com.luiz.devops.services;

import com.luiz.devops.dtos.investimento.ContaInvestimentosDto;
import com.luiz.devops.dtos.investimento.CreateInvestimentoDto;
import com.luiz.devops.dtos.investimento.InvestimentoResponseDto;
import com.luiz.devops.exceptions.RegistroNaoEncontradoException;
import com.luiz.devops.models.Conta;
import com.luiz.devops.models.Investimentos;
import com.luiz.devops.repositories.ContaRepository;
import com.luiz.devops.repositories.InvestimentoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class InvestimentoService {
    private final InvestimentoRepository repository;
    private final ContaRepository contaRepository;

    public InvestimentoService(InvestimentoRepository repository, ContaRepository contaRepository) {
        this.repository = repository;
        this.contaRepository = contaRepository;
    }

    public InvestimentoResponseDto criarInvestimento(CreateInvestimentoDto dto) {
        Conta conta = contaRepository.findById(dto.idConta()).orElseThrow(() -> new RegistroNaoEncontradoException("Conta"));

        if (dto.tipoInvestimento() < 1 || dto.tipoInvestimento() > 3) {
            throw new RuntimeException("Tipo Investimento inválido");
        }
        String tipoInvestimento;
        BigDecimal taxa;
        if (dto.tipoInvestimento() == 1) {
            tipoInvestimento = "Renda fixa 10%";
            taxa = BigDecimal.valueOf(0.1);
        } else if (dto.tipoInvestimento() == 2) {
            tipoInvestimento = "Renda fixa 15%";
            taxa = BigDecimal.valueOf(0.15);
        } else {
            tipoInvestimento = "Fundo de investimentos";
            taxa = BigDecimal.valueOf(0.12);
        }

        var investimento = new Investimentos();
        investimento.setConta(conta);
        investimento.setTipo(tipoInvestimento);
        investimento.setTaxa(taxa);
        investimento.setValor(dto.aporte());
        investimento.setResgate(LocalDateTime.now().plusYears(1));
        Investimentos savedInvestimento = repository.save(investimento);
        return new InvestimentoResponseDto(
                savedInvestimento.getId(),
                savedInvestimento.getTipo(),
                savedInvestimento.getValor(),
                savedInvestimento.getResgate()
        );
    }

    public List<ContaInvestimentosDto> getContaInvestimentos(UUID idConta) {
        return repository.getContaInvestimentos(idConta);
    }

    public void saqueInvestimento(int idInvestimento, BigDecimal saque) {
        var investimento = repository.findById(idInvestimento)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Investimento"));

        if (!verificarSaquePorTipoInvestimento(investimento)) {
            throw new RuntimeException("Saque não permitido");
        }

        investimento.setValor(investimento.getValor().subtract(saque));

        repository.save(investimento);
    }

    private boolean verificarSaquePorTipoInvestimento(Investimentos investimento) {
        String tipo = investimento.getTipo();
        boolean umAnoInvestido = ChronoUnit.YEARS.between(LocalDateTime.now(), investimento.getDataInicio()) == 1;
        if (tipo.contains("Renda fixa 10%")) {
            return true;
        }
        if (umAnoInvestido && tipo.contains("Renda fixa 15%")) {
            return true;
        }
        if (umAnoInvestido && tipo.contains("Fundo de investimentos")) {
            return true;
        }
        return false;
    }
}