package com.luiz.devops.repositories;

import com.luiz.devops.dtos.investimento.ContaInvestimentosDto;
import com.luiz.devops.models.Investimentos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface InvestimentoRepository extends JpaRepository<Investimentos, Integer> {

    @Query(value = """
            select
                conta.id,
                investimento.id,
                investimento.tipo,
                investimento.valor,
                investimento.taxa,
                investimento.data_inicio,
                investimento.resgate
             from
            	tb_contas conta
            inner join tb_investimentos investimento on
            	conta.id = investimento.conta_id
            where
            	conta.id = :idConta
            """, nativeQuery = true)
    List<ContaInvestimentosDto> getContaInvestimentos(UUID idConta);
}