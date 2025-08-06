package com.luiz.devops.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import teste.fiesc.conta.dtos.movimentacoes.MovimentacoesRequestDto;
import com.luiz.devops.models.Movimentacoes;
import com.luiz.devops.services.MovimentacoesService;

@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RequestMapping("/api/v1/movimentacoes")
public class MovimentacoesController {
    private final MovimentacoesService movimentacoesService;
    
    public MovimentacoesController(MovimentacoesService movimentacoesService) {
        this.movimentacoesService = movimentacoesService;
    }

    @PostMapping
    public ResponseEntity<Movimentacoes> criarMovimentacao(@RequestBody MovimentacoesRequestDto dto) {
        Movimentacoes movimentacaoResponse = movimentacoesService.criarMovimentacao(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(movimentacaoResponse);
    }
}