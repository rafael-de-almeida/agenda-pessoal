package view;

import javax.swing.*;

import calendario.*;

import java.awt.*;

public class Home {
    private CalendarModel calendarModel;
    private CalendarPanel calendarPanel;

    public Home() {
        JFrame frame = new JFrame("Simple Calendar");
        frame.setSize(500, 500);
        frame.setLayout(new GridLayout(1, 2));

        calendarModel = new CalendarModel();
        calendarPanel = new CalendarPanel(calendarModel);

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.BLUE);

        JPanel rightTopPanel = new JPanel(new BorderLayout());

        JPanel rightContainer = new JPanel();
        rightContainer.setLayout(new BoxLayout(rightContainer, BoxLayout.Y_AXIS));

        JTable table = new CalendarTable(calendarModel.getModel());
        table.setCellSelectionEnabled(true);
        JScrollPane pane = new JScrollPane(table);

        rightTopPanel.add(calendarPanel.getPanel(), BorderLayout.NORTH);
        rightTopPanel.add(pane, BorderLayout.CENTER);

        rightContainer.add(rightTopPanel);

        frame.add(leftPanel);
        frame.add(rightContainer);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        calendarModel.updateMonth();
    }
}