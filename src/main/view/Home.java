package view;

import java.awt.*;
import java.time.LocalDateTime;

import javax.swing.*;

import calendario.*;
import entidades.Evento;
import entidades.ListaItensAgendados;
import entidades.Tarefa;
import painelEventos.AgendaPanel;

public class Home {
    private CalendarModel calendarModel;
    private CalendarPanel calendarPanel;
    private JFrame frame;
    private JPanel leftPanel;
    private JPanel rightTopPanel;
    private JPanel rightContainer;

    public Home() {
        createFrame();
        createPanels();
        createCalendar();
        Evento evento = new Evento("Evento 1", LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        Tarefa tarefa = new Tarefa("Tarefa 1", LocalDateTime.now(), LocalDateTime.now().plusHours(2), false, 1);

        // Criação de uma instância de ListaItensAgendados
        ListaItensAgendados lista = new ListaItensAgendados();

        // Adicionando os itens agendados à lista
        lista.adicionarTarefa(evento);
        lista.adicionarTarefa(tarefa);
        AgendaPanel agendaPanel = new AgendaPanel(lista);
        rightContainer.add(agendaPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void createFrame() {
        frame = new JFrame("Simple Calendar");
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

        JTable table = new CalendarTable(calendarModel);
        table.setCellSelectionEnabled(true);
        JScrollPane tablePane = new JScrollPane(table);

        rightTopPanel.add(calendarPanel.getPanel(), BorderLayout.NORTH);
        rightTopPanel.add(tablePane, BorderLayout.CENTER);

        rightContainer.setLayout(new GridLayout(2, 1));
        rightContainer.add(rightTopPanel);

        calendarModel.updateMonth();
    }
}