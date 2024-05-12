package calendario;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Optional;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import entidades.ListaItensAgendados;

public class CalendarTable extends JTable {
    private CalendarModel model;

    public CalendarTable(CalendarModel model) {
        super(model.getModel());
        this.model = model;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = rowAtPoint(e.getPoint());
                int col = columnAtPoint(e.getPoint());
                if (row >= 0 && col >= 0) {
                    Object value = getValueAt(row, col);
                    System.out.println("Selected value: " + value);
                    // Optional<ListaItensAgendados> tarefas = model.itemsAgendados.get();
                }
            }
        });
    }

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component c = super.prepareRenderer(renderer, row, column);

        // Obtém o dia da célula
        Object value = getValueAt(row, column);
        if (value instanceof Integer) {
            int cellDay = (Integer) value;

            // Obtém o dia atual
            Calendar currentDate = Calendar.getInstance();
            int currentDay = currentDate.get(Calendar.DAY_OF_MONTH);
            int currentMonth = currentDate.get(Calendar.MONTH);
            int currentYear = currentDate.get(Calendar.YEAR);

            // Compara os dias
            if (cellDay == currentDay && model.getYear() == currentYear && model.getMonth() == currentMonth) {
                // Muda a cor de fundo da célula
                c.setBackground(Color.GREEN);
            } else {
                c.setBackground(Color.WHITE);
            }
        } else
            c.setBackground(Color.WHITE);

        if (isCellSelected(row, column)) {
            c.setForeground(Color.BLACK);
        } else {
            c.setForeground(Color.BLACK);
        }

        return c;
    }
}