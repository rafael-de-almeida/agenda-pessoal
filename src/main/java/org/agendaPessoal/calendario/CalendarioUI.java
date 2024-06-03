package org.agendaPessoal.calendario;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;

import org.agendaPessoal.entidades.MapItensAgendados;
import org.agendaPessoal.view.AgendaPanel;
import org.agendaPessoal.view.ItensAgendadosPanel;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.BorderLayout;
import java.awt.GridLayout;

public class CalendarioUI extends JPanel {
    private JTable calendarTable;
    private CalendarModel calendarModel;
    private CalendarPanel calendarPanel;
    private JPanel rightTopPanel;
    private JScrollPane tablePane;

    public CalendarioUI(MapItensAgendados mapItensAgendados, AgendaPanel agendaPanel,
            ItensAgendadosPanel itensAgendadosPanel) {
        calendarModel = new CalendarModel();
        calendarPanel = new CalendarPanel(calendarModel);

        calendarTable = new CalendarTable(calendarModel, mapItensAgendados, agendaPanel, itensAgendadosPanel);
        calendarTable.setCellSelectionEnabled(true);

        rightTopPanel = new JPanel(new BorderLayout());

        tablePane = new JScrollPane(calendarTable);
        criarPainelResponsivo();

        rightTopPanel.add(calendarPanel.getPanel(), BorderLayout.NORTH);
        rightTopPanel.add(tablePane, BorderLayout.CENTER);

        this.setLayout(new GridLayout(2, 1));
        this.add(rightTopPanel);

        calendarModel.updateMonth();
    }

    public void criarPainelResponsivo() {

        tablePane.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int height = e.getComponent().getHeight();
                int headerHeight = calendarTable.getTableHeader().getHeight();
                int scrollBarWidth = ((Integer) UIManager.get("ScrollBar.width")).intValue();
                int rows = 6;
                int rowHeight = Math.round((float) (height - headerHeight - scrollBarWidth) / rows);
                calendarTable.setRowHeight(rowHeight > 25 ? rowHeight : 32);
            }
        });
    }

    public JTable getCalendarTable() {
        return calendarTable;
    }

    public CalendarModel getCalendarModel() {
        return calendarModel;
    }

}
