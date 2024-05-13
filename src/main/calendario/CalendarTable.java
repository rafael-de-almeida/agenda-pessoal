package calendario;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import entidades.ListaItensAgendados;
import entidades.MapItensAgendados;
import painelEventos.AgendaPanel;

public class CalendarTable extends JTable {
    private static final Color SELECTED_COLOR = Color.GREEN;
    private static final Color DEFAULT_COLOR = Color.WHITE;
    private static final Color TEXT_COLOR = Color.BLACK;
    private static final Color SCHEDULED_COLOR = Color.CYAN;

    private CalendarModel model;
    private MapItensAgendados mapItensAgendados;

    public CalendarTable(CalendarModel model, MapItensAgendados mapItensAgendados, AgendaPanel agendaPanel) {
        super(model.getModel());
        this.model = model;
        this.mapItensAgendados = mapItensAgendados;

        LocalDate currentDate = LocalDate.now();
        ListaItensAgendados lista = mapItensAgendados.get(currentDate);
        if (lista == null) {
            lista = new ListaItensAgendados(); // Cria uma nova lista vazia
        }
        agendaPanel.update(lista);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleMouseClick(e, mapItensAgendados, agendaPanel);
            }
        });
    }

    private void handleMouseClick(MouseEvent e, MapItensAgendados mapItensAgendados, AgendaPanel agendaPanel) {
        int row = rowAtPoint(e.getPoint());
        int col = columnAtPoint(e.getPoint());
        if (row >= 0 && col >= 0) {
            Object day = getValueAt(row, col);
            System.out.println("Selected value: " + day);

            ListaItensAgendados listaNull = new ListaItensAgendados();
            if (day == null) {
                agendaPanel.update(listaNull);
                return;
            }

            if (day instanceof String) {
                try {
                    day = Integer.parseInt((String) day);
                } catch (NumberFormatException ex) {
                    System.out.println("Cannot convert " + day + " to Integer");
                    return;
                }
            }

            LocalDate selectedDate = model.getLocalDate((Integer) day);
            ListaItensAgendados lista = mapItensAgendados.get(selectedDate);
            if (lista == null) {
                lista = new ListaItensAgendados(); // Cria uma nova lista vazia
            }
            agendaPanel.update(lista);
        }

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

            // Obtém o dia atual
            Calendar currentDate = Calendar.getInstance();
            int currentDay = currentDate.get(Calendar.DAY_OF_MONTH);
            int currentMonth = currentDate.get(Calendar.MONTH);
            int currentYear = currentDate.get(Calendar.YEAR);

            LocalDate dateToCheck = LocalDate.of(model.getYear(), model.getMonth() + 1, cellDay);
            Map<LocalDate, ListaItensAgendados> itensMesSelecionado = mapItensAgendados
                    .getAllItensOfMonth(model.getMonth() + 1);

            // Compara os dias
            if (cellDay == currentDay && model.getYear() == currentYear && model.getMonth() == currentMonth) {
                // Muda a cor de fundo da célula
                return SELECTED_COLOR;
            } else if (itensMesSelecionado.containsKey(dateToCheck)) {
                return SCHEDULED_COLOR;
            }

        }
        return DEFAULT_COLOR;
    }
}