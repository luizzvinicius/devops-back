package com.luiz.devops.repositories;

import com.luiz.devops.models.Movimentacoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimentacoesRepository extends JpaRepository<Movimentacoes, Long> {
}