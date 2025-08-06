package com.luiz.devops.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luiz.devops.models.Conta;
import com.luiz.devops.models.Movimentacoes;

public interface MovimentacoesRepository extends JpaRepository<Movimentacoes, Long> {
    void deleteByConta(Conta conta);
}