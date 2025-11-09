package com.luiz.devops.controllers;

import com.luiz.devops.dtos.investimento.ContaInvestimentosDto;
import com.luiz.devops.dtos.investimento.CreateInvestimentoDto;
import com.luiz.devops.dtos.investimento.InvestimentoResponseDto;
import com.luiz.devops.dtos.investimento.SaqueInvestimentoDto;
import com.luiz.devops.services.InvestimentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Validated
@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RequestMapping("/api/v1/investimento")
public class InvestimentoController {
    private final InvestimentoService service;

    public InvestimentoController(InvestimentoService investimentoService) {
        this.service = investimentoService;
    }

    @PostMapping
    public ResponseEntity<InvestimentoResponseDto> criarInvestimento(@RequestBody @Valid CreateInvestimentoDto dto) {
        InvestimentoResponseDto response = service.criarInvestimento(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{idConta}")
    public ResponseEntity<List<ContaInvestimentosDto>> getContaInvestimentos(@PathVariable UUID idConta) {
        var contas = service.getContaInvestimentos(idConta);
        return ResponseEntity.status(HttpStatus.OK).body(contas);
    }

    @PutMapping("/{idInvestimento}")
    public ResponseEntity<Void> saqueInvestimento(@PathVariable int idInvestimento,
                                                  @RequestBody SaqueInvestimentoDto saqueDto) {
        service.saqueInvestimento(idInvestimento, saqueDto.saque());
        return ResponseEntity.ok().build();
    }
}