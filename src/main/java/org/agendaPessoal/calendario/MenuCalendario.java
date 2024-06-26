package org.agendaPessoal.calendario;

import javax.swing.*;

import java.awt.*;

/**
 * Gera e configura o menu do calendario
 */
public class MenuCalendario {
    private JPanel panel;
    private JLabel monthLabel;
    private ModeloCalendario calendarModel;

    public MenuCalendario(ModeloCalendario model) {
        this.calendarModel = model;

        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        createButtons();
        createMonthLabel();
    }

    public JPanel getPanel() {
        return panel;
    }

    public void updateMonth() {
        monthLabel.setText(calendarModel.getMonthName());
    }

    private void createButtons() {
        JButton b1 = new JButton("<");
        b1.addActionListener(ae -> {
            calendarModel.previousMonth();
            updateMonth();
        });

        JButton b2 = new JButton(">");
        b2.addActionListener(ae -> {
            calendarModel.nextMonth();
            updateMonth();
        });
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(b1);
        buttonPanel.add(b2);
        panel.add(buttonPanel, BorderLayout.EAST);
    }

    private void createMonthLabel() {
        monthLabel = new JLabel();
        monthLabel.setHorizontalAlignment(SwingConstants.CENTER);
        monthLabel.setText(calendarModel.getMonthName());

        panel.add(monthLabel, BorderLayout.WEST);
    }
}