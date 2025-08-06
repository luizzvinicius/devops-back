package com.luiz.devops.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import teste.fiesc.conta.dtos.pessoa.PessoaPageDto;
import teste.fiesc.conta.dtos.pessoa.PessoaRequestDto;
import teste.fiesc.conta.dtos.pessoa.PessoaResponseDto;
import com.luiz.devops.services.PessoaService;

@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RequestMapping("/api/v1/pessoa")
public class PessoaContoller {
    private final PessoaService pessoaService;

    public PessoaContoller(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping("/all")
    public ResponseEntity<PessoaPageDto> buscarTodasPessoas(@RequestParam(defaultValue = "0") @PositiveOrZero int p) {
        PessoaPageDto pessoa = pessoaService.buscarTodasPessoas(p);
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