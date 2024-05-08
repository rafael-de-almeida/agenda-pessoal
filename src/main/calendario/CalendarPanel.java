package calendario;

import javax.swing.*;

import java.awt.*;

public class CalendarPanel {
    private JPanel panel;
    private JLabel monthLabel;
    private CalendarModel calendarModel;

    public CalendarPanel(CalendarModel model) {
        this.calendarModel = model;

        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        monthLabel = new JLabel();
        monthLabel.setHorizontalAlignment(SwingConstants.CENTER);
        monthLabel.setText(calendarModel.getMonthName());

        JButton b1 = new JButton("<-");
        b1.addActionListener(ae -> calendarModel.previousMonth());

        JButton b2 = new JButton("->");
        b2.addActionListener(ae -> calendarModel.nextMonth());

        panel.add(b1, BorderLayout.WEST);
        panel.add(monthLabel, BorderLayout.CENTER);
        panel.add(b2, BorderLayout.EAST);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void updateMonth() {
        monthLabel.setText(calendarModel.getMonthName());
    }
}