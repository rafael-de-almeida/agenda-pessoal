package org.agendaPessoal.anotacao;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class AnotacaoPanel {
    private JPanel fileListPanel;

    private String selectedValue;
    private DefaultListModel<String> listModel;
    private JList<String> fileList;
    JTextArea area = new JTextArea();
    private FileManager fileManager = new FileManager();

    public AnotacaoPanel() {
        JFrame newPage = new JFrame("Anotações");
        newPage.setSize(300, 200);
        JMenuBar menuBar = new JMenuBar();
        JMenu AnotacoesMenu = new JMenu("arquivos");
        JMenuItem saveItem = new JMenuItem("salvar");
        JMenuItem novoItem = new JMenuItem("novo arquivo");

        createListAnotacoes();
        novoItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String filename = fileManager.gerarNovoArquivo();
                System.out.println("Generated filename: " + filename);
                fileManager.salvarArquivo(filename, area.getText());
                atualizarLista(filename);
            }
        });
        saveItem.addActionListener(new ActionListener() {
            String filename;

            public void actionPerformed(ActionEvent e) {
                if (selectedValue == null) {
                    filename = fileManager.gerarNovoArquivo();
                    fileManager.salvarArquivo(filename, "");
                    atualizarLista(filename);
                }

                else
                    fileManager.salvarArquivo(selectedValue, area.getText());

            }
        });

        AnotacoesMenu.add(saveItem);
        AnotacoesMenu.add(novoItem);
        menuBar.add(AnotacoesMenu);

        newPage.setLayout(new BorderLayout());
        newPage.setJMenuBar(menuBar);
        newPage.add(new JScrollPane(area), BorderLayout.CENTER);
        newPage.add(fileListPanel, BorderLayout.WEST);
        newPage.setVisible(true);
    }

    public void createListAnotacoes() {
        fileListPanel = new JPanel();
        fileListPanel.setLayout(new BoxLayout(fileListPanel, BoxLayout.Y_AXIS));
        listModel = new DefaultListModel<>();
        fileList = new JList<>(listModel);
        atualizarLista();

        JScrollPane scrollPane = new JScrollPane(fileList); // Add JList to JScrollPane
        fileListPanel.add(scrollPane); // Add JScrollPane to panel

        fileList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    selectedValue = AnotacaoPanel.this.fileList.getSelectedValue();
                    System.out.println("Selected value: " + selectedValue);
                    fileManager.abrirArquivo(selectedValue, area);
                }
            }
        });
    }

    public void atualizarLista(String filename) {
        String[] files = fileManager.getFiles();
        if (files != null) {
            listModel.clear();
            for (String file : files) {
                listModel.addElement(file);
            }
            fileList.setSelectedValue(filename, true);
        } else {
            System.err.println("Error: Unable to list files in directory " + fileManager.getPath());
        }
    }

    public void atualizarLista() {
        String[] files = fileManager.getFiles();
        System.out.println("Files: " + Arrays.toString(files));
        if (files != null) {
            listModel.clear();
            for (String file : files) {
                listModel.addElement(file);
            }
        } else {
            System.err.println("Error: Unable to list files in directory " + fileManager.getPath());
        }
    }
}
