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
