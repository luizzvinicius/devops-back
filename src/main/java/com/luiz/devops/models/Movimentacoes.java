package com.luiz.devops.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_MOVIMENTACOES")
public class Movimentacoes implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MOVIMENTACOES")
    @SequenceGenerator(name = "SEQ_MOVIMENTACOES", sequenceName = "SEQ_MOVIMENTACOES", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conta_id", referencedColumnName = "id")
    private Conta conta;

    @NotNull(message = "Valor da transação não pode ser nulo")
    @Column(nullable = false)
    private BigDecimal valor;

    @NotNull(message = "Data não pode ser nula")
    @Column(nullable = false)
    private LocalDateTime data = LocalDateTime.now();

    public Movimentacoes(Conta conta, BigDecimal valor) {
        this.valor = valor;
        this.conta = conta;
    }
}