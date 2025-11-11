package modelo;

import java.text.DecimalFormat;

public class Livro extends Midia {
 Pessoa autor;
 EnumCategoriaLivros categoria;


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
