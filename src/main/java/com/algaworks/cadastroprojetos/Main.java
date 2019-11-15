package com.algaworks.cadastroprojetos;

import com.algaworks.cadastroprojetos.model.Funcionario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence
                .createEntityManagerFactory("Projetos-PU");
        EntityManager entityManager = factory.createEntityManager();

        System.out.println("----------------------------------------------------------------");

        Funcionario funcionarioFind = entityManager.find(Funcionario.class, 1);

        System.out.println("---");

        // Usando a solução NO_PROXY, essa chamada vai disparar a
        // consulta para conta_corrente.
        funcionarioFind.getContaCorrente();

        // Limpando o entityManager para não afetar a próxima consulta.
        entityManager.clear();

        System.out.println("----------------------------------------------------------------");

        Funcionario funcionarioSingle = entityManager
                .createQuery(
                        "select f from Funcionario f join fetch f.contaCorrente cc where f.id = 1"
                        , Funcionario.class)
                .getSingleResult();

        System.out.println("---");

        // Como foi utilizando um JOIN FETCH, então, mesmo utilizando a
        // opção NO_PROXY, a chamada para o getter não vai disparar
        // uma nova consulta.
        funcionarioSingle.getContaCorrente();

        entityManager.close();
        factory.close();
    }
}
