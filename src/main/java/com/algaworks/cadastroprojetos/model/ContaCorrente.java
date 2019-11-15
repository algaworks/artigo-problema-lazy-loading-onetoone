package com.algaworks.cadastroprojetos.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "conta_corrente")
public class ContaCorrente {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String banco;

    private String agencia;

    private String numero;

    @OneToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;
}
