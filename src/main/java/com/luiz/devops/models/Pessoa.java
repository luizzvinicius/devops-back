package com.luiz.devops.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_PESSOAS", uniqueConstraints = {@UniqueConstraint(name = "UK_TB_PESSOAS_CPF", columnNames = {"cpf"})})
public class Pessoa implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PESSOAS")
    @SequenceGenerator(name = "SEQ_PESSOAS", sequenceName = "SEQ_PESSOAS", allocationSize = 1)
    private Long id;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Conta> contas;

    @NotBlank(message = "Nome Pessoa não pode ser nulo")
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "Nome Pessoa não pode ser nulo")
    @Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 dígitos")
    @Column(nullable = false)
    private String cpf;

    @NotBlank(message = "Nome Pessoa não pode ser nulo")
    @Column(nullable = false)
    private String endereco;

    public Pessoa(String nome, String cpf, String endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
    }
}