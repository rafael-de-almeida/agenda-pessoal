package org.agendaPessoal.view;

import org.agendaPessoal.calendario.CalendarModel;
import org.agendaPessoal.calendario.CalendarPanel;
import org.agendaPessoal.calendario.CalendarTable;
import org.agendaPessoal.entidades.Evento;
import org.agendaPessoal.entidades.ItemAgendado;
import org.agendaPessoal.entidades.ListaItensAgendados;
import org.agendaPessoal.entidades.MapItensAgendados;
import org.agendaPessoal.entidades.Tarefa;
import org.agendaPessoal.painelEventos.AgendaPanel;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Home {
    private static final String FRAME_TITLE = "Simple Calendar";

    private MapItensAgendados mapItensAgendados;
    private AgendaPanel agendaPanel;

    private JTable calendarTable;
    private CalendarModel calendarModel;
    private CalendarPanel calendarPanel;
    private JFrame frame;
    private JPanel leftPanel;
    private JPanel rightTopPanel;
    private JPanel rightContainer;

    public Home() {
        initialize();
    }

    private void initialize() {
        createMapItensAgendados();
        createAgendaPanel();
        createFrame();
        createPanels();
        createLateralMenu();
        createCalendar();
        finalizeInitialization();
    }

    private void createMapItensAgendados() {
        mapItensAgendados = new MapItensAgendados();
        Tarefa tarefa = new Tarefa("Tarefa 1", LocalDateTime.now(), LocalDateTime.now().plusHours(2), false, 1);
        LocalDate date = LocalDate.of(2024, 5, 25);
        mapItensAgendados.addItemAgendado(date, tarefa);
    }

    private void createAgendaPanel() {

        ListaItensAgendados lista = new ListaItensAgendados();
        Evento evento = new Evento("Evento 1", LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        Tarefa tarefa = new Tarefa("Tarefa 1", LocalDateTime.now(), LocalDateTime.now().plusHours(2), false, 1);

        lista.adicionarItemAgendado(evento);
        lista.adicionarItemAgendado(tarefa);

        agendaPanel = new AgendaPanel();
    }

    private void createFrame() {
        frame = new JFrame(FRAME_TITLE);
        frame.setSize(500, 500);
        frame.setLayout(new BorderLayout());
    }

    private void createPanels() {
        leftPanel = new JPanel();
        leftPanel.setBackground(Color.BLUE);

        rightTopPanel = new JPanel(new BorderLayout());
        rightContainer = new JPanel();
        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(rightContainer, BorderLayout.CENTER);
    }

    private void createLateralMenu() {
        JLabel Logo = new JLabel("myAgenda");
        leftPanel.add(Logo);
    }

    private void createCalendar() {
        calendarModel = new CalendarModel();
        calendarPanel = new CalendarPanel(calendarModel);

        calendarTable = new CalendarTable(calendarModel, mapItensAgendados, agendaPanel);
        calendarTable.setCellSelectionEnabled(true);
        JScrollPane tablePane = new JScrollPane(calendarTable);

        rightTopPanel.add(calendarPanel.getPanel(), BorderLayout.NORTH);
        rightTopPanel.add(tablePane, BorderLayout.CENTER);

        rightContainer.setLayout(new GridLayout(2, 1));
        rightContainer.add(rightTopPanel);

        calendarModel.updateMonth();
    }

    private void finalizeInitialization() {

        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel panel = new JPanel();
        tabbedPane.addTab("tarefas do dia", agendaPanel);
        tabbedPane.addTab("próximas tarefas", panel);

        JTextField titleForm = new JTextField(10);
        JButton button = new JButton("+");
        Border roundedBorder = new LineBorder(new Color(183, 183, 183, 128), 2, true);
        Border emptyBorder = new EmptyBorder(5, 5, 5, 5);
        Border border = new CompoundBorder(roundedBorder, emptyBorder);
        titleForm.setBorder(border);

        button.addActionListener(ae -> {
            String titulo = titleForm.getText();
            titleForm.setText("");
            mapItensAgendados.addItemAgendado(calendarModel.getLocalDate(), new ItemAgendado(titulo));
            calendarTable.repaint();
        });

        JLayeredPane layeredPane = new JLayeredPane();

        layeredPane.add(tabbedPane, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(button, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(titleForm, JLayeredPane.PALETTE_LAYER);

        layeredPane.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                tabbedPane.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
                button.setBounds(layeredPane.getWidth() - button.getPreferredSize().width - 10,
                        layeredPane.getHeight() - 50,
                        button.getPreferredSize().width, button.getPreferredSize().height);
                titleForm.setBounds(layeredPane.getWidth() - titleForm.getPreferredSize().width - 100,
                        layeredPane.getHeight() - 50,
                        titleForm.getPreferredSize().width, titleForm.getPreferredSize().height);
            }
        });

        rightContainer.add(layeredPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}