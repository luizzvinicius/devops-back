package com.luiz.devops.controllers;

import com.luiz.devops.dtos.movimentacoes.MovimentacoesRequestDto;
import com.luiz.devops.dtos.movimentacoes.MovimentacoesResponseDto;
import com.luiz.devops.services.MovimentacoesService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RequestMapping("/api/v1/movimentacoes")
public class MovimentacoesController {
    private final MovimentacoesService movimentacoesService;

    public MovimentacoesController(MovimentacoesService movimentacoesService) {
        this.movimentacoesService = movimentacoesService;
    }

    @PostMapping
    public ResponseEntity<MovimentacoesResponseDto> criarMovimentacao(@RequestBody @Valid MovimentacoesRequestDto dto) {
        MovimentacoesResponseDto movimentacaoResponse = movimentacoesService.criarMovimentacao(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(movimentacaoResponse);
    }
}