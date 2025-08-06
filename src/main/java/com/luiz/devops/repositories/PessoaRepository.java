package com.luiz.devops.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luiz.devops.models.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    
}