package org.agendaPessoal.anotacao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class AnotacaoPanel {
    public AnotacaoPanel() {
        JFrame newPage = new JFrame("Anotações");
        newPage.setSize(300, 200);

        JTextArea area = new JTextArea();

        JButton saveButton = new JButton("Salvar");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int option = fileChooser.showSaveDialog(newPage);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try {
                        FileWriter writer = new FileWriter(file);
                        writer.write(area.getText());
                        writer.close();
                    } catch (IOException ex) {
                        System.out.println("Erro: " + ex);
                    }
                }
            }
        });

        JButton openButton = new JButton("Abrir");
        openButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int option = fileChooser.showOpenDialog(newPage);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader(file));
                        area.read(reader, null);
                        reader.close();
                    } catch (IOException ex) {
                        System.out.println("Erro: " + ex);
                    }
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        buttonPanel.add(openButton);

        newPage.setLayout(new BorderLayout());
        newPage.add(buttonPanel, BorderLayout.NORTH);
        newPage.add(new JScrollPane(area), BorderLayout.CENTER);
        newPage.setVisible(true);
    }
}
