package painelEventos;

import javax.swing.BoxLayout;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import entidades.ListaItensAgendados;

public class AgendaPanel extends JPanel {

    public AgendaPanel() {
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