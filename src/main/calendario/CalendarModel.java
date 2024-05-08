package calendario;

import javax.swing.table.DefaultTableModel;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class CalendarModel {
    private static DefaultTableModel model;
    private static Calendar cal = new GregorianCalendar();

    public CalendarModel() {
        String[] columns = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
        model = new DefaultTableModel(null, columns);
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
}