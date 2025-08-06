package com.luiz.devops.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_CONTAS", uniqueConstraints = {@UniqueConstraint(columnNames = {"numero_conta"})})
public class Conta implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Pessoa pessoa;

    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Movimentacoes> movimentacoes;

    @NotBlank(message = "Número da conta não pode ser nulo")
    @Column(name = "numero_conta", nullable = false)
    private String numero;

    @PositiveOrZero(message = "Saldo não pode ser negativo")
    @Column(nullable = false)
    private Double saldo = 0.0;

    public Conta(Pessoa pessoa, String numero) {
        this.pessoa = pessoa;
        this.numero = numero;
    }
}