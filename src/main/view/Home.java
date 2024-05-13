package view;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.swing.*;

import calendario.*;
import entidades.Evento;
import entidades.ListaItensAgendados;
import entidades.MapItensAgendados;
import entidades.Tarefa;
import painelEventos.AgendaPanel;

public class Home {
    private static final String FRAME_TITLE = "Simple Calendar";

    private MapItensAgendados mapItensAgendados;
    private AgendaPanel agendaPanel;

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
        // Criação de uma instância de ListaItensAgendados
        ListaItensAgendados lista = new ListaItensAgendados();
        Evento evento = new Evento("Evento 1", LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        Tarefa tarefa = new Tarefa("Tarefa 1", LocalDateTime.now(), LocalDateTime.now().plusHours(2), false, 1);
        // Adicionando os itens agendados à lista
        lista.adicionarItemAgendado(evento);
        lista.adicionarItemAgendado(tarefa);
        agendaPanel = new AgendaPanel();
    }

    private void createFrame() {
        frame = new JFrame(FRAME_TITLE);
        frame.setSize(500, 500);
        frame.setLayout(new GridLayout(1, 2));
    }

    private void createPanels() {
        leftPanel = new JPanel();
        leftPanel.setBackground(Color.BLUE);

        rightTopPanel = new JPanel(new BorderLayout());
        rightContainer = new JPanel();
        frame.add(leftPanel);
        frame.add(rightContainer);
    }

    private void createCalendar() {
        calendarModel = new CalendarModel();
        calendarPanel = new CalendarPanel(calendarModel);

        JTable table = new CalendarTable(calendarModel, mapItensAgendados, agendaPanel);
        table.setCellSelectionEnabled(true);
        JScrollPane tablePane = new JScrollPane(table);

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

        // Cria um botão
        JButton button = new JButton("+");

        // Cria um JLayeredPane para conter o JTabbedPane e o botão
        JLayeredPane layeredPane = new JLayeredPane();

        layeredPane.add(tabbedPane, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(button, JLayeredPane.PALETTE_LAYER);

        layeredPane.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                tabbedPane.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
                button.setBounds(layeredPane.getWidth() - button.getPreferredSize().width - 10,
                        layeredPane.getHeight() - 50,
                        button.getPreferredSize().width, button.getPreferredSize().height);
            }
        });

        rightContainer.add(layeredPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}