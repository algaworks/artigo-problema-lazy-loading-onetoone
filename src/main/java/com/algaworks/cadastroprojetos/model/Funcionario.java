package com.algaworks.cadastroprojetos.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.engine.spi.PersistentAttributeInterceptable;
import org.hibernate.engine.spi.PersistentAttributeInterceptor;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "funcionario")
public class Funcionario implements PersistentAttributeInterceptable {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @OneToOne(mappedBy = "funcionario", fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    private ContaCorrente contaCorrente;

    @Transient
    private PersistentAttributeInterceptor persistentAttributeInterceptor;

    public ContaCorrente getContaCorrente() {
        if (this.persistentAttributeInterceptor != null) {
            return (ContaCorrente) this.persistentAttributeInterceptor.readObject(
                    this, "contaCorrente", this.contaCorrente);
        }

        return this.contaCorrente;
    }

    public void setContaCorrente(ContaCorrente contaCorrente) {
        if (this.persistentAttributeInterceptor != null) {
            this.contaCorrente = (ContaCorrente) persistentAttributeInterceptor.writeObject(
                    this, "contaCorrente", this.contaCorrente, contaCorrente);
        } else {
            this.contaCorrente = contaCorrente;
        }
    }

    @Override
    public PersistentAttributeInterceptor $$_hibernate_getInterceptor() {
        return persistentAttributeInterceptor;
    }

    @Override
    public void $$_hibernate_setInterceptor(PersistentAttributeInterceptor persistentAttributeInterceptor) {
        this.persistentAttributeInterceptor = persistentAttributeInterceptor;
    }
}
