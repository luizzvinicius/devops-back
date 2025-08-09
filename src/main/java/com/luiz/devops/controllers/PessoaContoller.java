package com.luiz.devops.controllers;

import com.luiz.devops.dtos.pessoa.PessoaPageDto;
import com.luiz.devops.dtos.pessoa.PessoaRequestDto;
import com.luiz.devops.dtos.pessoa.PessoaResponseDto;
import com.luiz.devops.services.PessoaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RequestMapping("/api/v1/pessoa")
public class PessoaContoller {
    private final PessoaService pessoaService;

    public PessoaContoller(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping("/all")
    public ResponseEntity<PessoaPageDto> buscarTodasPessoas(@RequestParam(defaultValue = "0") @PositiveOrZero int page) {
        PessoaPageDto pessoa = pessoaService.buscarTodasPessoas(page);
        return ResponseEntity.ok(pessoa);
    }

    @PostMapping
    public ResponseEntity<PessoaResponseDto> criarPessoa(@RequestBody @Valid PessoaRequestDto dto) {
        PessoaResponseDto savedPessoa = pessoaService.criarPessoa(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPessoa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaResponseDto> atualizarPessoa(@PathVariable Long id, @RequestBody @Valid PessoaRequestDto dto) {
        PessoaResponseDto updatedPessoa = pessoaService.atualizarPessoa(id, dto);
        return ResponseEntity.ok(updatedPessoa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPessoa(@PathVariable Long id) {
        pessoaService.deletarPessoa(id);
        return ResponseEntity.noContent().build();
    }
}