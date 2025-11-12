package TestJUnit;

import controller.GerenciadorMidia;
import modelo.*;
import org.junit.jupiter.api.Test;

import javax.swing.*;
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
    public void CriarArquivoTest() {
        /*
        * Criar um arquivo com objeto salvo
        * */

        EnumCategoriaMusicas categoria = EnumCategoriaMusicas.KPOP;
        Pessoa robinho = new Pessoa("Robinho");
        Midia musica = new Musica("A grande Salada",10, "C:\\Users\\gafernandes\\Desktop\\oi.tpoo", 10,robinho , categoria );

        File f = new File("C:\\Users\\gafernandes\\Desktop\\oi.tpoo");
        try(FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream o = new ObjectOutputStream(fos);) {
            o.writeObject(musica);
            System.out.println("Salvo com sucesso!");
            o.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("------------------------------------------------------------------------------");
        /*
         * abrir e ler um arquivo com objeto salvo
         * */
        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView());
        fileChooser.setDialogTitle("Salvar arquivo");

            //NÃO PRECISA IMPLEMENTAR, estarei usando somente como teste
            JFrame frame = new JFrame("Save File Dialog Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.setVisible(true);
            //------------------------------------------------------------

        int resposta = fileChooser.showOpenDialog(
                frame /* <- esta variavel é referente o componente de tela do view*/);

            String path = "";
         if(resposta != fileChooser.CANCEL_OPTION){
            path = fileChooser.getSelectedFile().toString();
        }
        Midia arquivo;
        try (FileInputStream fis = new FileInputStream(path);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            arquivo = (Musica) ois.readObject();
            System.out.println("Arquivo lido com sucesso!: \n"+arquivo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    public void moverArquivoTest() throws IOException {
        //NÃO PRECISA IMPLEMENTAR, estarei usando somente como teste
        JFrame frame = new JFrame("Save File Dialog Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setVisible(true);
        //------------------------------------------------------------
        EnumCategoriaMusicas categoria = EnumCategoriaMusicas.KPOP;
        Pessoa robinho = new Pessoa("Robinho");
        Midia musica = new Musica("A grande Salada",10, "C:\\Users\\PC\\Desktop", 10,robinho , categoria );
        Midia musica2 = new Musica("A grande Salada",10, "C:\\Users\\PC\\Documents", 10,robinho , categoria );

        GerenciadorMidia g = new GerenciadorMidia();
       g.salvar(musica);
        g.salvar(musica2);

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecione local");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.showOpenDialog(frame);
        String path = fileChooser.getSelectedFile().getAbsolutePath();




        System.out.println(g.mover(musica,path));
    }
}