package calendario;

import javax.swing.table.DefaultTableModel;

import entidades.ListaItensAgendados;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Map;

public class CalendarModel {
    private static DefaultTableModel model;
    private static Calendar cal = new GregorianCalendar();
    Map<LocalDate, ListaItensAgendados> itemsAgendados;

    public CalendarModel() {
        String[] columns = { "Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sab" };
        model = new DefaultTableModel(null, columns);
        // this.itemsAgendados = tarefas;
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public void updateMonth() {
        cal.set(Calendar.DAY_OF_MONTH, 1);

        int startDay = cal.get(Calendar.DAY_OF_WEEK);
        int numberOfDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        int weeks = cal.getActualMaximum(Calendar.WEEK_OF_MONTH);

        model.setRowCount(0);
        model.setRowCount(weeks);

        int i = startDay - 1;
        for (int day = 1; day <= numberOfDays; day++) {
            model.setValueAt(day, i / 7, i % 7);
            i = i + 1;
        }
    }

    public void nextMonth() {
        cal.add(Calendar.MONTH, +1);
        updateMonth();
    }

    public void previousMonth() {
        cal.add(Calendar.MONTH, -1);
        updateMonth();
    }

    public String getMonthName() {
        return cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US) + " " + cal.get(Calendar.YEAR);
    }

    public int getYear() {
        return cal.get(Calendar.YEAR);
    }

    public int getMonth() {
        return cal.get(Calendar.MONTH);
    }
}