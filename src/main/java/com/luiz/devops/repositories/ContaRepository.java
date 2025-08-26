package com.luiz.devops.repositories;

import com.luiz.devops.dtos.conta.ContaMovimentacoesDto;
import com.luiz.devops.models.Conta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContaRepository extends JpaRepository<Conta, UUID> {
    @Query("""
                SELECT new com.luiz.devops.dtos.conta.ContaMovimentacoesDto(
                    c.id,
                    m.id,
                    m.valor,
                    m.data
                )
                FROM Conta c
                JOIN c.movimentacoes m
                WHERE c.id = :contaId
            """)
    Page<ContaMovimentacoesDto> getContaMovimentacoes(@Param("contaId") UUID contaId, Pageable pageable);
}