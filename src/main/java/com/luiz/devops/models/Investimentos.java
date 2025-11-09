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
@Table(name = "TB_INVESTIMENTOS")
public class Investimentos implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private BigDecimal taxa;

    @Column(nullable = false)
    private BigDecimal valor;

    @NotNull(message = "Data não pode ser nula")
    @Column(nullable = false)
    private LocalDateTime resgate;

    @NotNull(message = "Data não pode ser nula")
    @Column(nullable = false)
    private LocalDateTime dataInicio = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conta_id", referencedColumnName = "id")
    private Conta conta;
}