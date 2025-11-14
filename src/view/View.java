package view;

import controller.GerenciadorMidia;
import modelo.Midia;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class View extends JFrame {

    private GerenciadorMidia gerenciador;
    private JList<Midia> listaMidias;
    private DefaultListModel<Midia> listModel;

    public View() {
        gerenciador = new GerenciadorMidia();
        configurarTela();
        carregarLista();
        setVisible(true);
    }

    private void configurarTela() {
        setTitle("Gerenciador de Mídias");
        setSize(700, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        // LISTA
        listModel = new DefaultListModel<>();
        listaMidias = new JList<>(listModel);
        add(new JScrollPane(listaMidias), BorderLayout.CENTER);

        // BOTOES
        JPanel painelBotoes = new JPanel();

        JButton btnAbrir = new JButton("Abrir");
        JButton btnExcluir = new JButton("Excluir");
        JButton btnAlterar = new JButton("Alterar");
        JButton btnDetalhes = new JButton("Detalhes"); // novo

        painelBotoes.add(btnAbrir);
        painelBotoes.add(btnExcluir);
        painelBotoes.add(btnAlterar);
        painelBotoes.add(btnDetalhes);

        add(painelBotoes, BorderLayout.SOUTH);

        //ABRIR
        btnAbrir.addActionListener(e -> abrirMidia());

        //EXCLUIR
        btnExcluir.addActionListener(e -> excluirMidia());

        //ALTERAR
        btnAlterar.addActionListener(e -> alterarMidia());

        //DETALHES (TELA EXTRA)
        btnDetalhes.addActionListener(e -> mostrarDetalhes());
    }

    private void carregarLista() {
        listModel.clear();
        List<Midia> lista = gerenciador.getLista();
        for (Midia m : lista){
            listModel.addElement(m);
        }
    }

    private Midia getSelecionada() {
        return listaMidias.getSelectedValue();
    }

    private void abrirMidia() {


            Midia aberta = gerenciador.abrir();
            if (aberta != null) {
                carregarLista();
                JOptionPane.showMessageDialog(this, aberta.toString(), "Mídia", JOptionPane.INFORMATION_MESSAGE);


            }
    }

    private void excluirMidia() {
        Midia m = getSelecionada();
        if (m == null) {
            JOptionPane.showMessageDialog(this, "Selecione uma mídia!");
            return;
        }

        int opcao = JOptionPane.showConfirmDialog(
                this,
                "Tem certeza disso?\nExcluir: " + m.getTitulo(),
                "Confirmação",
                JOptionPane.YES_NO_OPTION
        );

        if (opcao == JOptionPane.YES_OPTION) {
            if (gerenciador.excluir(m)) {
                JOptionPane.showMessageDialog(this, "Excluído com sucesso!");
                carregarLista();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao excluir.");
            }
        }
    }

    // ALTERAR TITULO
    private void alterarMidia() {
        Midia m = getSelecionada();
        if (m == null) {
            JOptionPane.showMessageDialog(this, "Selecione uma mídia!");
            return;
        }

        String novoTitulo = JOptionPane.showInputDialog(this, "Novo título:", m.getTitulo());

        if (novoTitulo != null && !novoTitulo.trim().isEmpty()) {
            try {
                m.setTitulo(novoTitulo);
                gerenciador.alterar(m);
                carregarLista();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            }
        }
    }


    private void mostrarDetalhes() {
        Midia m = getSelecionada();
        if (m == null) {
            JOptionPane.showMessageDialog(this, "Selecione uma mídia!");
            return;
        }

        try {
            Midia aberta = gerenciador.abrir();

            JTextArea area = new JTextArea();
            area.setEditable(false);
            area.setText(
                    "Título: " + aberta.getTitulo() + "\n" +
                            "Duração: " + aberta.getDuracao() + "\n" +
                            "Local: " + aberta.getLocal() + "\n" +
                            "Memória: " + aberta.getMemoriaDisco() + "\n"
            );

            JScrollPane scroll = new JScrollPane(area);

            JOptionPane.showMessageDialog(
                    this,
                    scroll,
                    "Detalhes da Mídia",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao abrir detalhes: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new View();
    }
}
