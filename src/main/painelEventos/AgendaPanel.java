package painelEventos;

import javax.swing.BoxLayout;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import entidades.ListaItensAgendados;

public class AgendaPanel extends JPanel {

    public AgendaPanel(ListaItensAgendados listaItensAgendados) {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JList<String> list = new JList<>(listaItensAgendados.getItems());
        JScrollPane scrollPane = new JScrollPane(list);

        this.add(scrollPane);
    }
}
