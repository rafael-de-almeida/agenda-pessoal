package org.agendaPessoal.agenda;

import javax.swing.BoxLayout;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.agendaPessoal.entidades.ListaItensAgendados;

public class ListaItensDoDia extends JPanel {

    public ListaItensDoDia() {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    }

    public void update(ListaItensAgendados lista) {
        this.removeAll();

        JList<String> list = new JList<>(lista.getItems());
        JScrollPane scrollPane = new JScrollPane(list);

        this.add(scrollPane);

        this.revalidate();
        this.repaint();
    }
}
