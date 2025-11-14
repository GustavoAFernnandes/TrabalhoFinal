package TestJUnit;

import controller.GerenciadorMidia;
import modelo.*;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.text.DecimalFormat;
import java.util.Scanner;


public class TestJUnit {

    /*
    * Teste de modelos
    * */
    @Test
    public void PessoaTest() {
        Pessoa pessoa = new Pessoa("Robert Robertson III");
        System.out.println("Pessoa: "+pessoa.getNome());

    }
    /*
    * Teste de Categorias
    * */
    @Test
    public void CategoriaTest() {
        // aprender como fazer testes de enum
        Scanner sc = new Scanner(System.in);
        EnumCategoriaFilmes filmes;
        EnumCategoriaLivros livros;
        EnumCategoriaMusicas musicas;

        filmes = EnumCategoriaFilmes.valueOf("DORAMA");
        livros = EnumCategoriaLivros.valueOf("DRAMA");
        musicas = EnumCategoriaMusicas.valueOf("KPOP");


        System.out.println(filmes);
        System.out.println(livros);
        System.out.println(musicas);



    }
    /*
    * Teste de Livros
    * A duração varia para cada tipo de mídia, sendo que em filmes a duração é dada em
minutos, músicas em segundos e livros em páginas
    * */
    @Test
    public void LivrosTest() {
        Pessoa robinho = new Pessoa("Robinho");
        EnumCategoriaLivros categoria = EnumCategoriaLivros.DRAMA;
        Midia livro = new Livro("A grande Salada",10, "/desktop", 1000, robinho , categoria );
        System.out.println(livro);

    }

    @Test
    public void FilmesTest() {
        EnumCategoriaFilmes categoria = EnumCategoriaFilmes.DORAMA;
        Midia filme = new Filme("Ta dando onda",10, "/desktop", 10, "PT-BR" , categoria );
        System.out.println(filme);
    }
    @Test
    public void MusicasTest() {
        EnumCategoriaMusicas categoria = EnumCategoriaMusicas.KPOP;
        Pessoa robinho = new Pessoa("Robinho");
        Midia musica = new Musica("A grande Salada",10, "/desktop", 10,robinho , categoria );
        System.out.println(musica);
    }
    @Test
    public void criarArquivoTest() throws IOException {
        EnumCategoriaMusicas categoria = EnumCategoriaMusicas.KPOP;
        Pessoa robinho = new Pessoa("Robinho");
        Midia musica = new Musica("TESTE", 10, "src\\controller\\saves\\", 10, robinho, categoria);

        GerenciadorMidia g = new GerenciadorMidia();

        g.salvar(musica);


    }


    @Test
    public void moverArquivoTest() throws IOException {



        EnumCategoriaMusicas categoria = EnumCategoriaMusicas.KPOP;
        Pessoa robinho = new Pessoa("Robinho");
        Midia musica = new Musica("TESTE", 10, "src\\controller\\saves\\", 10, robinho, categoria);

        GerenciadorMidia g = new GerenciadorMidia();

        g.salvar(musica);


        System.out.println(g.mover(musica));
    }

    @Test
    public void abrirArquivoTest() throws IOException {
        GerenciadorMidia g = new GerenciadorMidia();
            System.out.println(g.abrir());

    }

    @Test
    public void carregarListaTest() throws IOException {
        GerenciadorMidia g = new GerenciadorMidia();

            System.out.println(g.abrir());


    }
    @Test
    public void excluirTest() throws IOException {
        EnumCategoriaMusicas categoria = EnumCategoriaMusicas.KPOP;
        Pessoa robinho = new Pessoa("Robinho");
        Midia musica = new Musica("TESTE", 10, "src\\controller\\saves\\", 10, robinho, categoria);

        GerenciadorMidia g = new GerenciadorMidia();

        g.excluir(musica);

    }
}