package org.agendaPessoal.agenda;

import java.awt.Color;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;

import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.agendaPessoal.calendario.CalendarioPanel;
import org.agendaPessoal.entidades.ItemAgendado;
import org.agendaPessoal.entidades.ListaItensAgendados;
import org.agendaPessoal.entidades.MapItensAgendados;

public class PainelAgenda extends JLayeredPane {
    public PainelAgenda(MapItensAgendados mapItensAgendados, CalendarioPanel calendarioUI,
            ListaItensDoDia listaItensDoDia,
            ListaItens itensAgendadosPanel) {
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("tarefas do dia", listaItensDoDia);
        tabbedPane.addTab("próximas tarefas", itensAgendadosPanel);

        JTextField titleForm = new JTextField(10);
        JButton button = new JButton("+");
        Border roundedBorder = new LineBorder(new Color(183, 183, 183, 128), 2, true);
        Border emptyBorder = new EmptyBorder(5, 5, 5, 5);
        Border border = new CompoundBorder(roundedBorder, emptyBorder);
        titleForm.setBorder(border);
        titleForm.setText("");

        button.addActionListener(ae -> {
            String titulo = titleForm.getText().trim();
            if (!titulo.isEmpty()) {
                titleForm.setText("");

                mapItensAgendados.addItemAgendado(calendarioUI.getModeloCalendario().getLocalDate(),
                        new ItemAgendado(titulo, calendarioUI.getModeloCalendario().getLocalDate().atStartOfDay()));
                ListaItensAgendados lista = mapItensAgendados.get(calendarioUI.getModeloCalendario().getLocalDate());
                calendarioUI.getTabelaCalendario().repaint();
                listaItensDoDia.update(lista);
                itensAgendadosPanel.update(mapItensAgendados.getItensAgendadosOrdered());
            } else {
                NewItem newItem = new NewItem();
                newItem.setVisible(true);
            }

        });

        this.add(tabbedPane, JLayeredPane.DEFAULT_LAYER);
        this.add(button, JLayeredPane.PALETTE_LAYER);
        this.add(titleForm, JLayeredPane.PALETTE_LAYER);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                tabbedPane.setBounds(0, 0, PainelAgenda.this.getWidth(), PainelAgenda.this.getHeight());
                button.setBounds(PainelAgenda.this.getWidth() - button.getPreferredSize().width - 10,
                        PainelAgenda.this.getHeight() - 50,
                        button.getPreferredSize().width, button.getPreferredSize().height);
                titleForm.setBounds(PainelAgenda.this.getWidth() - titleForm.getPreferredSize().width - 100,
                        PainelAgenda.this.getHeight() - 50,
                        titleForm.getPreferredSize().width, titleForm.getPreferredSize().height);
            }
        });
    }

}