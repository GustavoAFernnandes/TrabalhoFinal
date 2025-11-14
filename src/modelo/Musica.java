package modelo;

import java.text.DecimalFormat;

public class Musica extends Midia {
    Pessoa artista;
    EnumCategoriaMusicas categoria;

    public Musica(String titulo, double memoriaDisco, String local, int segundos, Pessoa artista, EnumCategoriaMusicas categoria) {

        super(titulo, memoriaDisco, local, segundos);
        setArtista(artista);
        setCategoria(categoria);
    }
    /**
     * @autor: Rebeca, Amanda e Gustavo
     * @Versão: 1.0
     * @throw: IllegalArgumentException se artista ou categoria forem inválidos
     * @See: ategoria forem inválidos
     * @param: titulo título da música, memoriaDisco tamanho do arquivo em disco, local diretório onde a música será salva,
     * segundos duração da música em segundos, artista artista responsável pela música,
     * Categoria categoria da música, artista novo artista da música, categoria categoria da música
     */

    public Pessoa getArtista() {
        return artista;
    }

    public void setArtista(Pessoa artista)  throws IllegalArgumentException {
        if(artista == null ||  artista.getNome().isEmpty()) {
            throw new IllegalArgumentException("ERRO: Artista Vazio");
        }
        this.artista = artista;
    }

    public EnumCategoriaMusicas getCategoria() {
        return categoria;
    }

    public void setCategoria(EnumCategoriaMusicas categoria) throws IllegalArgumentException {
        if(categoria == null || categoria.name().isEmpty()){
            throw new IllegalArgumentException("ERRO: Categoria Vazia");
        }
        this.categoria = categoria;
    }
    @Override
    public String toString() {
        return "Titulo: "+super.getTitulo()+
                "\nArtista: "+this.getArtista().getNome()+
                "\nCategoria: "+this.getCategoria().name()+
                "\nDuração: "+super.getDuracao()+ " Segundos"+
                "\nMemoria em Disco: "+super.getMemoriaDisco()+
                "\nPath: "+super.getLocal();
    }
}
