package controller;

import modelo.*;

import javax.swing.*;
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
                    lista.add(abrir(arquivo.getAbsolutePath()));
                    System.out.println("- " + arquivo.getName());

                }
            }
        } else {
            System.out.println("A pasta não foi encontrada ou não é um diretório.");
            System.out.println("caminho: " + pasta.getAbsolutePath());
        }

    }
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
                System.out.println("Salvo com sucesso!");
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

    public boolean alterar(Midia midiaAlterada) {
        if(midiaAlterada == null  ) {
            System.out.println("Erro ao Alterar: Arquivo é Nulo!");
            return false ;
        }
        for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getLocal().equals(midiaAlterada.getLocal())) {
                lista.set(i, midiaAlterada);
                System.out.println("Alterado com sucesso!");
                return true;
            }
        }
        System.out.println("Erro Desconhecido!");
        return false;
    }
    // todo adicionar uma validação
    public boolean excluir(Midia midia) {
        if(midia == null  ) {

            return false ;
        }
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getLocal().equals(midia.getLocal())) {
                File f = new File(lista.get(i).getLocal());
                if(f.delete()){
                    lista.remove(midia);
                    return true;

                }

            }
        }
        return false;


    }
    //todo entender este codigo
    public List<Midia> listar(Class<? extends Midia> midia) {
        return lista.stream()
                .filter(m -> midia.isInstance(m))
                .collect(Collectors.toList());
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

    public String mover(Midia midia, String novoLocal) throws IOException {
        if(midia == null  ) {

            return "Impossivel Mover Midia Vazia" ;
        } else if (midia.getLocal().equals(novoLocal)){
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
                        return "Midia Movida com sucesso para: "+midia.getLocal();
                    }

                }
            }
        }


        return "Erro desconhecido";
    }


    public Midia abrir(String path) {


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
