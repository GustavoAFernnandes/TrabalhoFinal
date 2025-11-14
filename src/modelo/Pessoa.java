package modelo;

import java.io.Serializable;

public class Pessoa implements Serializable {
    String nome;
    public Pessoa(String nome) {
        setNome(nome);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws IllegalArgumentException {
        if(nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("ERRO: Nome Vazio");
        }
        this.nome = nome;
    }
}

/**
 * @autor: Rebeca, Amanda e Gustavo
 * @Versão: 1.0
 * @throw: IllegalArgumentException Caso o nome seja nulo ou vazio.
 * @See: Pessoa#getNome(), Pessoa#setNome(String)
 * @param: nome Nome válido que será definido para a pessoa.
 *
 */