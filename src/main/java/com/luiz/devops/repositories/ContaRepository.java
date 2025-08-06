package com.luiz.devops.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luiz.devops.models.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long> {
    Optional<Conta> findByNumero(String numero);
}