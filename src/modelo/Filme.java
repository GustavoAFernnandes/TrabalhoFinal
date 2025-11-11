package modelo;

import java.text.DecimalFormat;

public class Filme extends Midia {
    String idiomaAudio;
    EnumCategoriaFilmes categoria;

    public Filme(String titulo, double memoriaDisco, String local, int minutos, String idioma, EnumCategoriaFilmes categoria) {
        super(titulo, memoriaDisco, local, minutos);
        setIdiomaAudio(idioma);
        setCategoria(categoria);

    }
        // opa bão
    public String getIdiomaAudio() {
        return idiomaAudio;
    }

    public void setIdiomaAudio(String idiomaAudio) throws IllegalArgumentException{
        if(idiomaAudio == null || idiomaAudio.trim().isEmpty()) {
            throw  new IllegalArgumentException("ERRO: Idioma Vazio");
        }

        this.idiomaAudio = idiomaAudio;
    }

    public EnumCategoriaFilmes getCategoria() {
        return categoria;
    }

    public void setCategoria(EnumCategoriaFilmes categoria) throws IllegalArgumentException {
        if(categoria == null || categoria.ordinal() < 0) {
            throw  new IllegalArgumentException("ERRO: Categoria Vazia");

        }
        this.categoria = categoria;
    }
    @Override
    public String toString() {
        return "Titulo: "+super.getTitulo()+
                "\nIdiomaAudio: "+this.getIdiomaAudio()+
                "\nCategoria: "+this.getCategoria().name()+
                "\nDuração: "+this.getDuracao()+" Minutos"+
                "\nMemoria em Disco: "+super.getMemoriaDisco()+
                "\nPath: "+super.getLocal();
    }
}
