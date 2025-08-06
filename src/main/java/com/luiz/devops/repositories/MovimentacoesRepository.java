package com.luiz.devops.repositories;

import com.luiz.devops.models.Movimentacoes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimentacoesRepository extends JpaRepository<Movimentacoes, Long> {
}