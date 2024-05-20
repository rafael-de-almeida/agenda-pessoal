package org.agendaPessoal.view;

import java.awt.FlowLayout;
import java.text.ParseException;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

public class NewItem extends JFrame {
    public NewItem() {
        // Configura o título da janela
        setTitle("Adicionar Evento");

        // Configura o tamanho da janela
        setSize(300, 200);
        BoxLayout boxLayout = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS); // cria um BoxLayout
        this.setLayout(boxLayout); // define o layout do frame como BoxLayout

        // Configura a operação de fechamento da janela
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        FlowLayout defaulLayout = new FlowLayout(FlowLayout.LEFT);

        JPanel titleForm = new JPanel();
        JLabel titleLabel = new JLabel("Titulo: ");
        JTextField tituloField = new JTextField();
        tituloField.setColumns(10);
        titleForm.add(titleLabel);
        titleForm.add(tituloField);

        JPanel dataInicioForm = new JPanel(defaulLayout);
        JLabel dataInicioLabel = new JLabel("Data início:");
        JFormattedTextField dataInicioInput = new JFormattedTextField(createDayMask());
        dataInicioInput.setColumns(7);
        JLabel optionalLabel = new JLabel("(Opicional)");
        dataInicioForm.add(dataInicioLabel);
        dataInicioForm.add(dataInicioInput);
        dataInicioForm.add(optionalLabel);

        JPanel dataFimForm = new JPanel(defaulLayout);
        JLabel dataFimLabel = new JLabel("Data fim:");
        JFormattedTextField dataFimInput = new JFormattedTextField(createDayMask());
        dataFimInput.setColumns(7);
        dataFimForm.add(dataFimLabel);
        dataFimForm.add(dataFimInput);

        JPanel descriptionPanel = new JPanel(defaulLayout);
        JLabel descriptionLabel = new JLabel("descrição:");
        JTextArea descriptionInput = new JTextArea();
        descriptionInput.setColumns(30);
        descriptionInput.setRows(4);
        descriptionPanel.add(descriptionInput);

        JButton button = new JButton("Adicionar Evento");

        add(titleForm);
        add(dataInicioForm);
        add(dataFimForm);
        add(descriptionLabel);
        add(descriptionPanel);
        add(button);

        // Exibe a janela
        setVisible(true);
    }

    public MaskFormatter createDayMask() {
        MaskFormatter mask = null;
        try {
            mask = new MaskFormatter("##/##/####");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return mask;
    }
}
