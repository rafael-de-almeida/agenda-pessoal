package org.agendaPessoal.agenda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.agendaPessoal.entidades.ListaItensAgendados;

public class ListaItens extends JPanel {

    public ListaItens() {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    }

    public void update(List<ListaItensAgendados> lista) {
        this.removeAll();
        List<String> stringList = new ArrayList<>();
        for (ListaItensAgendados itens : lista) {
            Collections.addAll(stringList, itens.getItems());
        }
        String[] stringArray = stringList.toArray(new String[0]);
        JList<String> list = new JList<>(stringArray);
        JScrollPane scrollPane = new JScrollPane(list);

        this.add(scrollPane);

        this.revalidate();
        this.repaint();
    }
}
