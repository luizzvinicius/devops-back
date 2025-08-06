package com.luiz.devops.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import teste.fiesc.conta.dtos.conta.ContaRequestDto;
import teste.fiesc.conta.dtos.conta.ContaResponseDto;
import com.luiz.devops.services.ContaService;

@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RequestMapping("/api/v1/conta")
public class ContaController {
    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }
    
    @PostMapping
    public ResponseEntity<ContaResponseDto> criarConta(@RequestBody ContaRequestDto dto) {
        ContaResponseDto contaResponse = contaService.criarConta(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(contaResponse);
    }
}