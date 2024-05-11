package calendario;

import java.awt.Color;
import java.awt.Component;
import java.util.Calendar;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

public class CalendarTable extends JTable {
    public CalendarTable(TableModel model) {
        super(model);
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

            // Compara os dias
            if (cellDay == currentDay) {
                // Muda a cor de fundo da célula
                c.setBackground(Color.GREEN);
            } else {
                c.setBackground(Color.WHITE);
            }
        }

        return c;
    }
}