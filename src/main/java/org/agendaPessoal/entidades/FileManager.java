package org.agendaPessoal.entidades;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 * Classe responsável por gerenciar operações em arquivos
 */
public class FileManager {
    private static final String PATH = "src/main/java/org/agendaPessoal/data";

    public String[] getFiles() {
        File directory = new File(PATH);
        String[] files = directory.list();
        return files;
    }

    public String getPath() {
        return PATH;
    }

    public void abrirArquivo(String filename, JTextArea area) {
        File file = new File(PATH + "/" + filename);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            area.read(reader, null);
            reader.close();
        } catch (IOException ex) {
            System.out.println("Erro: " + ex);
        }

    }

    public String gerarNovoArquivo() {
        String filename;
        File file = null;
        do {
            filename = JOptionPane.showInputDialog("Enter the filename:");
            file = new File(PATH + "/" + filename);
            if (file.exists()) {
                JOptionPane.showMessageDialog(null, "A file with this name already exists.");
                continue;
            }

        } while (filename != null);
        return filename;
    }

    public void salvarArquivo(String filename, String content) {
        File file = new File(PATH + "/" + filename);
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(content);
            writer.close();
        } catch (IOException ex) {
            System.out.println("erro ao tentar salvar arquivo");
        }

    }
}
