package org.agendaPessoal.view;

import org.agendaPessoal.calendario.CalendarModel;
import org.agendaPessoal.calendario.CalendarPanel;
import org.agendaPessoal.calendario.CalendarTable;
import org.agendaPessoal.entidades.Evento;
import org.agendaPessoal.entidades.ItemAgendado;
import org.agendaPessoal.entidades.ListaItensAgendados;
import org.agendaPessoal.entidades.MapItensAgendados;
import org.agendaPessoal.entidades.Tarefa;

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
    private ItensAgendadosPanel itensAgendadosPanel;

    private JTable calendarTable;
    private CalendarModel calendarModel;
    private CalendarPanel calendarPanel;
    private JFrame frame;
    private JPanel rightTopPanel;
    private JPanel rightContainer;
    private JScrollPane tablePane;

    public Home() {
        initialize();
    }

    private void initialize() {
        createMapItensAgendados();
        createAgendaPanel();
        createItensPanel();
        createFrame();
        createPanels();
        createCalendar();
        createLateralMenu();
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

    private void createItensPanel() {
        this.itensAgendadosPanel = new ItensAgendadosPanel();
    }

    private void createFrame() {
        frame = new JFrame(FRAME_TITLE);
        frame.setSize(500, 500);
        frame.setLayout(new BorderLayout());
    }

    private void createPanels() {

        rightTopPanel = new JPanel(new BorderLayout());
        rightContainer = new JPanel();
        frame.add(rightContainer, BorderLayout.CENTER);
    }

    private void createLateralMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu AnotacoesMenu = new JMenu("Anotações");
        JMenuItem openItem = new JMenuItem("Open");
        AnotacoesMenu.add(openItem);
        menuBar.add(AnotacoesMenu);
        frame.setJMenuBar(menuBar);
    }

    private void createCalendar() {
        calendarModel = new CalendarModel();
        calendarPanel = new CalendarPanel(calendarModel);

        calendarTable = new CalendarTable(calendarModel, mapItensAgendados, agendaPanel, itensAgendadosPanel);
        calendarTable.setCellSelectionEnabled(true);

        tablePane = new JScrollPane(calendarTable);
        tablePane.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int height = e.getComponent().getHeight();
                int headerHeight = calendarTable.getTableHeader().getHeight();
                int scrollBarWidth = ((Integer) UIManager.get("ScrollBar.width")).intValue();
                int rows = 6;
                int rowHeight = Math.round((float) (height - headerHeight - scrollBarWidth) / rows);
                calendarTable.setRowHeight(rowHeight > 30 ? rowHeight : 30);
            }
        });

        rightTopPanel.add(calendarPanel.getPanel(), BorderLayout.NORTH);
        rightTopPanel.add(tablePane, BorderLayout.CENTER);

        rightContainer.setLayout(new GridLayout(2, 1));
        rightContainer.add(rightTopPanel);

        calendarModel.updateMonth();
    }

    private void finalizeInitialization() {

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("tarefas do dia", agendaPanel);
        tabbedPane.addTab("próximas tarefas", itensAgendadosPanel);

        JTextField titleForm = new JTextField(10);
        JButton button = new JButton("+");
        Border roundedBorder = new LineBorder(new Color(183, 183, 183, 128), 2, true);
        Border emptyBorder = new EmptyBorder(5, 5, 5, 5);
        Border border = new CompoundBorder(roundedBorder, emptyBorder);
        titleForm.setBorder(border);
        titleForm.setText("");

        button.addActionListener(ae -> {
            String titulo = titleForm.getText().trim();
            if (!titulo.isEmpty()) {
                titleForm.setText("");

                mapItensAgendados.addItemAgendado(calendarModel.getLocalDate(),
                        new ItemAgendado(titulo, calendarModel.getLocalDate().atStartOfDay()));
                ListaItensAgendados lista = mapItensAgendados.get(calendarModel.getLocalDate());
                calendarTable.repaint();
                agendaPanel.update(lista);
                itensAgendadosPanel.update(mapItensAgendados.getItensAgendadosOrdered());
            } else {
                NewItem newItem = new NewItem();
                newItem.setVisible(true);
            }

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