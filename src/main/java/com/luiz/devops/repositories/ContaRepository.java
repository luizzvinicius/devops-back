package com.luiz.devops.repositories;

import com.luiz.devops.models.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContaRepository extends JpaRepository<Conta, Long> {
    Optional<Conta> findByNumero(String numero);
}