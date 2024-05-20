package org.agendaPessoal.calendario;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import org.agendaPessoal.entidades.ListaItensAgendados;
import org.agendaPessoal.entidades.MapItensAgendados;
import org.agendaPessoal.painelEventos.AgendaPanel;

public class CalendarTable extends JTable {
    private static final Color SELECTED_COLOR = Color.GREEN;
    private static final Color DEFAULT_COLOR = Color.WHITE;
    private static final Color TEXT_COLOR = Color.BLACK;
    private static final Color SCHEDULED_COLOR = Color.CYAN;
    private LocalDate selectedDate;

    private CalendarModel model;
    private MapItensAgendados mapItensAgendados;

    public CalendarTable(CalendarModel model, MapItensAgendados mapItensAgendados, AgendaPanel agendaPanel) {
        super(model.getModel());
        this.model = model;
        this.mapItensAgendados = mapItensAgendados;

        LocalDate currentDate = LocalDate.now();
        ListaItensAgendados listaAtividadesDoDia = mapItensAgendados.get(currentDate);
        updateAgendaPanel(agendaPanel, listaAtividadesDoDia);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleMouseClick(e, agendaPanel);
            }
        });
    }

    private void handleMouseClick(MouseEvent evento, AgendaPanel agendaPanel) {
        int row = rowAtPoint(evento.getPoint());
        int col = columnAtPoint(evento.getPoint());
        if (row >= 0 && col >= 0) {
            Object day = getValueAt(row, col);
            System.out.println("Selected value: " + day);

            if (day == null) {
                updateAgendaPanel(agendaPanel, null);
                selectedDate = null;
                return;
            }

            model.setSelectedDay((int) day);
            selectedDate = model.getLocalDate();

            ListaItensAgendados lista = mapItensAgendados.get(selectedDate);
            updateAgendaPanel(agendaPanel, lista);
        }
    }

    private void updateAgendaPanel(AgendaPanel agendaPanel, ListaItensAgendados lista) {
        ListaItensAgendados novaLista = new ListaItensAgendados();
        if (lista == null) {
            agendaPanel.update(novaLista);
            return;
        }
        agendaPanel.update(lista);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component c = super.prepareRenderer(renderer, row, column);
        c.setBackground(getCellBackgroundColor(row, column));
        c.setForeground(TEXT_COLOR);
        return c;
    }

    private Color getCellBackgroundColor(int row, int column) {
        Object value = getValueAt(row, column);
        if (value instanceof Integer) {
            int cellDay = (Integer) value;

            Calendar currentDate = Calendar.getInstance();
            int currentDay = currentDate.get(Calendar.DAY_OF_MONTH);
            int currentMonth = currentDate.get(Calendar.MONTH);
            int currentYear = currentDate.get(Calendar.YEAR);

            LocalDate dateToCheck = LocalDate.of(model.getYear(), model.getMonth() + 1, cellDay);

            Map<LocalDate, ListaItensAgendados> itensMesSelecionado = mapItensAgendados
                    .getAllItensOfMonth(model.getMonth() + 1);

            if (cellDay == currentDay && model.getYear() == currentYear && model.getMonth() == currentMonth) {
                return SELECTED_COLOR;
            } else if (itensMesSelecionado.containsKey(dateToCheck)) {
                return SCHEDULED_COLOR;
            }

        }
        return DEFAULT_COLOR;
    }
}