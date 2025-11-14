package controller;

import modelo.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GerenciadorMidia {
    private List<Midia> lista =  new ArrayList<>();

    public GerenciadorMidia() {

        criarLista();
    }

    /**
     * @autor: Rebeca, Amanda e Gustavo
     * @Versão: 1.0
     * @throw: IOException Caso ocorra erro de escrita no arquivo
     * @See: abrir(String)
     * @param: objeto de midia a ser salvo
     *
     */
    public void criarLista() {
        // O caminho "recursos" é relativo à pasta raiz do projeto (diretório de trabalho atual)
        String nomeDaPasta = "src\\controller\\saves\\";

        File pasta = new File(nomeDaPasta);

        if (pasta.exists() && pasta.isDirectory()) {
            System.out.println("Pasta carregada com sucesso: " + pasta.getAbsolutePath());

            // Exemplo de como listar os arquivos dentro dela
            File[] arquivos = pasta.listFiles();
            if (arquivos != null) {
                System.out.println("Arquivos na pasta:");
                for (File arquivo : arquivos) {

                        abrir(arquivo.getAbsolutePath());
                        System.out.println("- " + arquivo.getName());



                }
            }
        } else {
            System.out.println("A pasta não foi encontrada ou não é um diretório.");
            System.out.println("caminho: " + pasta.getAbsolutePath());
        }

    }
    /*
     *  uma função que salva uma midia no dirtetorio informado dentro da midia
     *  também salva no sistema se ainda não existir
     *
     */
    public boolean salvar(Midia midia) throws IOException {
        if(midia == null  ) {
            System.out.println("Erro ao salvar: Arquivo é Nulo!");
            return false;
        }
            boolean isMidiaExiste = false;
            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getLocal().equals(midia.getLocal())) {
                    isMidiaExiste = true;
                }
            }
        if (isMidiaExiste){
            System.out.println("Erro ao salvar: Arquivo já existe!");
            return false;
        }else{


            File f = new File(midia.getLocal());
            try(FileOutputStream fos = new FileOutputStream(f);
                ObjectOutputStream o = new ObjectOutputStream(fos)) {
                o.writeObject(midia);
                System.out.println("Salvo com sucesso no diretorio:  " + f.getAbsolutePath());
                o.close();
                lista.add(midia);
                //salva no sistema
                File saveFile = new File("src\\controller\\saves\\"+midia.getTitulo()+".tpoo");
                try (FileOutputStream saveFos = new FileOutputStream(saveFile);
                     ObjectOutputStream saveObject = new ObjectOutputStream(saveFos);){
                    saveObject.writeObject(midia);
                    System.out.println("Adicionado a Nuvem com sucesso!");
                }

                return true;
            } catch (Exception e) {

                throw new RuntimeException(e);
            }

        }


    }
    /*
     *  uma função que altera uma midia, excluindo a antiga e salvando a nova no lugar da antiga
     *
     */
    public boolean alterar(Midia midiaAlterada) throws IOException {
        if(midiaAlterada == null  ) {
            JOptionPane.showMessageDialog(null,"Erro ao Alterar: Arquivo é Nulo!" , "Erro!", JOptionPane.INFORMATION_MESSAGE);

            System.out.println("Erro ao Alterar: Arquivo é Nulo!");
            return false ;
        }
        for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getLocal().equals(midiaAlterada.getLocal())) {
                excluir(lista.get(i));
                salvar(midiaAlterada);
                    JOptionPane.showMessageDialog(null,"Alterado com sucesso!" , "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println("Alterado com sucesso!");
                return true;
            }
        }
        System.out.println("Erro Desconhecido!");
        return false;
    }


    /*
     *  uma função que exclui uma midia
     *
     */
    public boolean excluir(Midia midia) {
        if(midia == null  ) {
            JOptionPane.showMessageDialog(null,"Impossivel Mover Midia Vazia", "Erro!", JOptionPane.INFORMATION_MESSAGE);

            return false ;
        }




                    File f = new File(midia.getLocal());
                    File savedF = new File("src\\controller\\saves\\"+midia.getTitulo()+".tpoo");
                    if (savedF.exists() && savedF.delete()) {
                        System.out.println("Removido com sucesso da nuvem!");
                    }

                    if(f.delete()){


                        lista.remove(midia);
                        JOptionPane.showMessageDialog(null,"Midia Removida com sucesso", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);

                        return true;


                    } else{

            JOptionPane.showMessageDialog(null,"Erro desconhecido!" , "Erro!", JOptionPane.INFORMATION_MESSAGE);
            return false;
                    }


    }


    public List<Midia> getLista() {
        return this.lista;
    }

    public List<Midia> listar(Enum categoria) {
        return lista.stream()
                .filter(m -> {
                    if (m instanceof Musica) {
                        return ((Musica) m).getCategoria() == categoria;
                    } else if (m instanceof Livro) {
                        return ((Livro) m).getCategoria() == categoria;
                    } else if (m instanceof Filme) {
                        return ((Filme) m).getCategoria() == categoria;
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }
    public List<Midia> listar(Enum categoria, Class<? extends Midia> midia) {
        return lista.stream()
                .filter(m -> midia.isInstance(m))
                .filter(m -> {
                    if (m instanceof Musica) {
                        return ((Musica) m).getCategoria() == categoria;
                    } else if (m instanceof Livro) {
                        return ((Livro) m).getCategoria() == categoria;
                    } else if (m instanceof Filme) {
                        return ((Filme) m).getCategoria() == categoria;
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }
    public List<Midia> exibirAlfabetico() {
        return lista.stream()
                .sorted(Comparator.comparing(Midia::getTitulo))
                .collect(Collectors.toList());
    }
    /*
     *  uma função que move uma midia de local
     *  ela procura um novo local com o file chooser
     *  valida se: existe uma igual, se é nula e qual o tipo de resposta
     *  chama o metodo de excluir e salvar logo apos
     *
     */
    public String mover(Midia midia) throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecione local");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int resposta =fileChooser.showOpenDialog(null);
        if (resposta == JFileChooser.APPROVE_OPTION){
            String novoLocal = fileChooser.getSelectedFile().getAbsolutePath();
            if(midia == null  ) {
                JOptionPane.showMessageDialog(null,"Impossivel Mover Midia Vazia", "Erro!", JOptionPane.INFORMATION_MESSAGE);

                return "Impossivel Mover Midia Vazia" ;
            } else if (midia.getLocal().equals(novoLocal)){
                JOptionPane.showMessageDialog(null,"Uma Midia com o mesmo Nome Já existente neste local", "Erro!", JOptionPane.INFORMATION_MESSAGE);

                return "Uma Midia com o mesmo Nome Já existente neste local";

            }else{

                for (int i = 0; i < lista.size(); i++) {
                    if (lista.get(i).getTitulo().equals(midia.getTitulo()) ) {
                        if (lista.get(i).getLocal().equals(novoLocal)){

                            return "Uma Midia com o mesmo Nome Já existente neste local";
                        }else {
                            excluir(lista.get(i));
                            midia.setLocal(novoLocal);
                            salvar(midia);
                            JOptionPane.showMessageDialog(null,"Midia Movida com sucesso para: "+midia.getLocal(), "Sucesso!", JOptionPane.INFORMATION_MESSAGE);

                            return "Midia Movida com sucesso para: "+midia.getLocal();
                        }

                    }
                }
            }
    }else if (resposta == JFileChooser.CANCEL_OPTION) {
        JOptionPane.showMessageDialog(null,"Operação cancelada!" , "Erro!", JOptionPane.INFORMATION_MESSAGE);

    }else{
        JOptionPane.showMessageDialog(null,"Erro desconhecido!" , "Erro!", JOptionPane.INFORMATION_MESSAGE);

    }



        return "Erro desconhecido";
    }
    /*
     *  uma função que abre uma midia apartior do computador e retorna está midia
     *  ela procura uma midia com o file chooser
     *  valida se: o path n é nu, qual o tipo de resposta
     *  apos ele da um cast midia e chama o metodo salvar
     *  retorna midia ( para poder conversar com qualquer metodo ou logica que precise de uma midia do computador )
     */
    public Midia abrir() {
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Arquivos tpoo (*.tpoo)", "tpoo");
        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView());
        fileChooser.setDialogTitle("Abrir arquivo");
        fileChooser.setFileFilter(filtro);
        int resposta = fileChooser.showOpenDialog(null);
        if ( resposta == JFileChooser.APPROVE_OPTION) {
            String path = fileChooser.getSelectedFile().getAbsolutePath();

            if (path != null && !path.trim().isEmpty()) {
                Midia arquivo;
                try (FileInputStream fis = new FileInputStream(path);
                     ObjectInputStream ois = new ObjectInputStream(fis)) {
                    arquivo = (Midia) ois.readObject();
                    salvar(arquivo);

                    System.out.println("Arquivo lido com sucesso!");
                    ois.close();
                    return arquivo;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }


            }


        }else if (resposta == JFileChooser.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(null,"Operação cancelada!" , "Erro!", JOptionPane.INFORMATION_MESSAGE);

        }else{
            JOptionPane.showMessageDialog(null,"Erro desconhecido!" , "Erro!", JOptionPane.INFORMATION_MESSAGE);

        }


        return null;
    }
    /*
     *  uma função que abre uma midia apartir do computador e retorna está midia
     *  similar ao metodo abrir sem parametro, porém usado somente dentro de Gerenciador de arquivos
     *  é usado em criarLista()
     *
     */
    protected Midia abrir(String path) {



            if (path != null && !path.trim().isEmpty()) {
                Midia arquivo;
                try (FileInputStream fis = new FileInputStream(path);
                     ObjectInputStream ois = new ObjectInputStream(fis)) {
                    arquivo = (Midia) ois.readObject();
                    salvar(arquivo);
                    System.out.println("Arquivo lido com sucesso!");
                    ois.close();
                    return arquivo;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }


            }





        return null;
    }




}
