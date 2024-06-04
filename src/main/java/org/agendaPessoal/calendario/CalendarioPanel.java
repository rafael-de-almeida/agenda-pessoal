package org.agendaPessoal.calendario;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;

import org.agendaPessoal.agenda.ListaItens;
import org.agendaPessoal.agenda.ListaItensDoDia;
import org.agendaPessoal.entidades.MapItensAgendados;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.BorderLayout;

public class CalendarioPanel extends JPanel {
    private JTable tabelaCalendario;
    private ModeloCalendario modeloCalendario;
    private MenuCalendario menuCalendario;
    private JScrollPane painelTabelaCalendario;

    public CalendarioPanel(MapItensAgendados mapItensAgendados, ListaItensDoDia agendaPanel,
            ListaItens itensAgendadosPanel) {
        modeloCalendario = new ModeloCalendario();
        menuCalendario = new MenuCalendario(modeloCalendario);

        tabelaCalendario = new TabelaCalendario(modeloCalendario, mapItensAgendados, agendaPanel, itensAgendadosPanel);
        tabelaCalendario.setCellSelectionEnabled(true);

        this.setLayout(new BorderLayout());
        painelTabelaCalendario = new JScrollPane(tabelaCalendario);
        criarPainelResponsivo();

        this.add(menuCalendario.getPanel(), BorderLayout.NORTH);
        this.add(painelTabelaCalendario, BorderLayout.CENTER);
        modeloCalendario.updateMonth();
    }

    public void criarPainelResponsivo() {

        painelTabelaCalendario.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int height = e.getComponent().getHeight();
                int headerHeight = tabelaCalendario.getTableHeader().getHeight();
                int scrollBarWidth = ((Integer) UIManager.get("ScrollBar.width")).intValue();
                int rows = 6;
                int rowHeight = Math.round((float) (height - headerHeight - scrollBarWidth) / rows);
                tabelaCalendario.setRowHeight(rowHeight > 25 ? rowHeight : 32);
            }
        });
    }

    public JTable getTabelaCalendario() {
        return tabelaCalendario;
    }

    public ModeloCalendario getModeloCalendario() {
        return modeloCalendario;
    }

}
