package com.luiz.devops.controllers;

import com.luiz.devops.dtos.conta.ContaMovimentacoesResponseDto;
import com.luiz.devops.dtos.conta.ContaRequestDto;
import com.luiz.devops.dtos.conta.ContaResponseDto;
import com.luiz.devops.services.ContaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Validated
@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RequestMapping("/api/v1/conta")
public class ContaController {
    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @PostMapping
    public ResponseEntity<ContaResponseDto> criarConta(@RequestBody @Valid ContaRequestDto dto) {
        ContaResponseDto contaResponse = contaService.criarConta(dto);
        return ResponseEntity.status(CREATED).body(contaResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContaResponseDto> buscarUmaConta(@PathVariable UUID id) {
        ContaResponseDto conta = contaService.buscarContaPorId(id);
        return ResponseEntity.status(OK).body(conta);
    }

    @GetMapping("/{contaId}/movimentacoes")
    public ResponseEntity<ContaMovimentacoesResponseDto> getContaMovimentacoes(@PathVariable UUID contaId,
                                                                               @RequestParam(defaultValue = "0") @PositiveOrZero int page) {
        ContaMovimentacoesResponseDto contaMovimentacoes = contaService.getContaMovimentacoes(contaId, page);
        return ResponseEntity.ok(contaMovimentacoes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConta(@PathVariable UUID id) {
        contaService.deleteConta(id);
        return ResponseEntity.noContent().build();
    }
}