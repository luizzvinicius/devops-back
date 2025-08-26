package com.luiz.devops.repositories;

import com.luiz.devops.dtos.pessoa.PessoaAndContaDto;
import com.luiz.devops.models.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    Optional<Pessoa> findByCpf(String cpf);

    @Query(
            value = """
                    SELECT
                    pessoa.id, pessoa.nome, pessoa.cpf, pessoa.endereco,
                    conta.id, conta.saldo
                    FROM TB_PESSOAS pessoa
                    INNER JOIN TB_CONTAS conta
                    ON pessoa.id = conta.pessoa_id
                    WHERE pessoa.id = :id
                    """,
            countQuery = """
                    SELECT COUNT(1) FROM TB_CONTAS;
                    """,
            nativeQuery = true
    )
    Page<PessoaAndContaDto> findPessoaAndConta(int id, Pageable pagination);
    Page<Pessoa> findAllByNomeContains(String nome, Pageable pagination);
}