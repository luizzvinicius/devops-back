package com.luiz.devops.controllers;

import com.luiz.devops.dtos.conta.ContaRequestDto;
import com.luiz.devops.dtos.conta.ContaResponseDto;
import com.luiz.devops.services.ContaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConta(@PathVariable UUID id) {
        contaService.deleteConta(id);
        return ResponseEntity.noContent().build();
    }
}