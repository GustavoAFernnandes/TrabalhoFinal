package controller;

import modelo.Midia;
import modelo.Musica;
import modelo.Livro;
import modelo.Filme;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GerenciadorMidia {
    private List<Midia> lista;

    public GerenciadorMidia() {
        criarListaVazia();
    }

    public void criarListaVazia() {
        lista = new ArrayList<>();
    }

    public void adicionar(Midia midia) {
        lista.add(midia);
    }

    public void alterar(Midia midia) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getTitulo().equals(midia.getTitulo())) {
                lista.set(i, midia);
                break;
            }
        }
    }

    public void excluir(Midia midia) {
        lista.remove(midia);
    }

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

    public void mover(Midia midia, String novoLocal) {
        midia.setLocal(novoLocal);
    }

    public void renomearArquivo(Midia midia, String novoNome) {
        midia.setTitulo(novoNome);
    }

    public void abrir(String nomeArquivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            lista = (List<Midia>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
