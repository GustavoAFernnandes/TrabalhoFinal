package modelo;

import java.text.DecimalFormat;

public class Livro extends Midia {
 Pessoa autor;
 EnumCategoriaLivros categoria;

     /**
     * @autor: Rebeca, Amanda e Gustavo
     * @Versão: 1.0
     * @throw: IllegalArgumentException se o autor for nulo ou sem nome, IllegalArgumentException se a categoria for inválida
     * @See: EnumCategoriaFilmes, Pessoa
     * @param: titulo título do livro, memoriaDisco espaço usado no disco, local caminho onde o livro está salvo,
     * paginas quantidade de páginas do livro, autor autor do livro e categoria categoria do livro
     *
     */
    public Livro(String titulo, double memoriaDisco, String local, int paginas, Pessoa autor,  EnumCategoriaLivros categoria) {

        super(titulo,memoriaDisco, local, paginas);
        setAutor(autor);
        setCategoria(categoria);
        if(true){}
    }

    public Pessoa getAutor() {
        return autor;
    }

    public void setAutor(Pessoa autor) {
        if (autor == null || autor.getNome().isEmpty()) {
            throw new IllegalArgumentException("ERRO: Autor vazio");
        }
        this.autor = autor;
    }

    public EnumCategoriaLivros getCategoria() {
        return categoria;
    }

    public void setCategoria(EnumCategoriaLivros categoria) throws IllegalArgumentException {
        if (categoria == null || categoria.name().isEmpty()) {
            throw new IllegalArgumentException("ERRO: Categoria vazia");
        }
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Titulo: "+super.getTitulo()+
                "\nAutor: "+this.getAutor().getNome()+
                "\nCategoria: "+this.getCategoria().name()+
                "\nPaginas: "+super.getDuracao()+" paginas"+
                "\nMemoria em Disco: "+super.getMemoriaDisco()+
                "\nPath: "+super.getLocal();
    }


}
