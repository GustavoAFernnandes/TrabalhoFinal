package modelo;

import java.io.Serializable;

public class Midia implements Serializable {
    private String local;
    private int duracao;
    private String titulo;
    private double memoriaDisco;

    Midia(String titulo, double memoriaDisco , String local, int duracao) {
        setTitulo(titulo);
        setMemoriaDisco(memoriaDisco);
        setLocal(local);
        setDuracao(duracao);


/**
 * @autor: Rebeca, Amanda e Gustavo
 * @Versão: 1.0
 * @throw: IllegalArgumentException se o local estiver vazio, IllegalArgumentException se a duração for menor ou igual a zero,
 * IllegalArgumentException se o título for inválido, IllegalArgumentException se o tamanho em disco for menor ou igual a zero
 * @See: Filme, Livro, Serializable
 * @param: titulo título da mídia, memoriaDisco tamanho em disco da mídia, local caminho onde o arquivo será salvo,
 *  * duracao duração da mídia em minutos/páginas
 */

    }
    public String getLocal() {
        return local;
    }

    public void setLocal(String local)  throws IllegalArgumentException {
        if(local == null || local.trim().isEmpty()){
        throw new IllegalArgumentException("ERRO: Local Vazio");

        }
        this.local = local+"\\"+this.titulo+".tpoo";
    }

    public double getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) throws IllegalArgumentException {
        if( duracao <= 0 ){
            throw new IllegalArgumentException("ERRO: Duração Vazia");

        }
        this.duracao = duracao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) throws IllegalArgumentException {
        if(titulo == null || titulo.trim().isEmpty()){
            throw new IllegalArgumentException("ERRO: Titulo Vazio");

        }
        this.titulo = titulo;
    }

    public Double getMemoriaDisco() {
        return memoriaDisco;
    }

    public void setMemoriaDisco(double memoriaDisco) throws IllegalArgumentException {
        if ( memoriaDisco <= 0){

            throw new IllegalArgumentException("ERRO: Tamanho Invalido");
        }
        this.memoriaDisco = memoriaDisco;
    }


}
