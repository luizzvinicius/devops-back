package com.luiz.devops.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_CONTAS")
public class Conta implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id")
    private Pessoa pessoa;

    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Movimentacoes> movimentacoes;

    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Investimentos> investimentos;

    @PositiveOrZero(message = "Saldo não pode ser negativo")
    @Column(nullable = false)
    private BigDecimal saldo = new BigDecimal(0);

    public Conta(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}