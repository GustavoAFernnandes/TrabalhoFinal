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
    //todo refazer com novos metodos
    public void CriarArquivoTest() throws IOException {
        /*
        * Criar um arquivo com objeto salvo
        * */
        //usando somente como teste
        JFrame frame = new JFrame("Save File Dialog Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setVisible(true);
        //------------------------------------------------------------
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecione local");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.showOpenDialog(frame);
        String path = fileChooser.getSelectedFile().getAbsolutePath();

        EnumCategoriaMusicas categoria = EnumCategoriaMusicas.KPOP;

        Pessoa robinho = new Pessoa("Robinho");
        Midia musica = new Musica("A grande Salada",10, "C:\\Users\\gafernandes\\Desktop", 10,robinho , categoria );
        GerenciadorMidia g = new GerenciadorMidia();

        g.salvar(musica);
        System.out.println("------------------------------------------------------------------------------");





    }
    @Test
    public void moverArquivoTest() throws IOException {
        //usando somente como teste
        JFrame frame = new JFrame("Save File Dialog Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setVisible(true);
        //------------------------------------------------------------
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecione local");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.showOpenDialog(frame);
        String path = fileChooser.getSelectedFile().getAbsolutePath();


        EnumCategoriaMusicas categoria = EnumCategoriaMusicas.KPOP;
        Pessoa robinho = new Pessoa("Robinho");
        Midia musica = new Musica("A grande Salada", 10, path, 10, robinho, categoria);

        GerenciadorMidia g = new GerenciadorMidia();

        g.salvar(musica);


        JFileChooser fileChooser2 = new JFileChooser();
        fileChooser2.setDialogTitle("Selecione local");
        fileChooser2.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser2.showOpenDialog(frame);
        String path2 = fileChooser2.getSelectedFile().getAbsolutePath();


        System.out.println(g.mover(musica, path2));
    }

    @Test
    public void abrirArquivoTest() throws IOException {
        GerenciadorMidia g = new GerenciadorMidia();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Arquivos tpoo (*.tpoo)", "tpoo");
        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView());
        fileChooser.setDialogTitle("Abrir arquivo");
        fileChooser.setFileFilter(filtro);


        //usando somente como teste
        JFrame frame = new JFrame("Open File Dialog Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setVisible(true);
        //------------------------------------------------------------

        if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {

            System.out.println(g.abrir(fileChooser.getSelectedFile().getAbsolutePath()));

        }




    }

    @Test
    public void carregarListaTest() throws IOException {
        GerenciadorMidia g = new GerenciadorMidia();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Arquivos tpoo (*.tpoo)", "tpoo");
        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView());
        fileChooser.setDialogTitle("Abrir arquivo");
        fileChooser.setFileFilter(filtro);



        //usando somente como teste
        JFrame frame = new JFrame("Open File Dialog Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setVisible(true);
        //------------------------------------------------------------

        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

            System.out.println(g.abrir(fileChooser.getSelectedFile().getAbsolutePath()));

        }
    }
}