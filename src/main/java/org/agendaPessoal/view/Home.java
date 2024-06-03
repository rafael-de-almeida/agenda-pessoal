package org.agendaPessoal.view;

import org.agendaPessoal.calendario.CalendarioUI;
import org.agendaPessoal.entidades.Evento;
import org.agendaPessoal.entidades.ItemAgendado;
import org.agendaPessoal.entidades.ListaItensAgendados;
import org.agendaPessoal.entidades.MapItensAgendados;
import org.agendaPessoal.entidades.Tarefa;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Home {
    private static final String FRAME_TITLE = "Simple Calendar";

    private MapItensAgendados mapItensAgendados;
    private AgendaPanel agendaPanel;
    private ItensAgendadosPanel itensAgendadosPanel;

    private JFrame frame;
    private CalendarioUI calendarioUI;

    public Home() {
        initialize();
    }

    private void initialize() {
        createMapItensAgendados();
        createAgendaPanel();
        createItensPanel();
        createFrame();
        criarUICalendario();
        createTopMenu();
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

    private void criarUICalendario() {
        calendarioUI = new CalendarioUI(mapItensAgendados, agendaPanel, itensAgendadosPanel);
        frame.add(calendarioUI, BorderLayout.CENTER);
    }

    private void createTopMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu AnotacoesMenu = new JMenu("Anotações");
        JMenuItem openItem = new JMenuItem("Abrir");
        JMenuItem saveItem = new JMenuItem("Salvar");

        openItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame newPage = new JFrame("Anotações");
                newPage.setSize(300, 200);

                JTextArea area = new JTextArea();

                JButton saveButton = new JButton("Salvar");
                saveButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JFileChooser fileChooser = new JFileChooser();
                        int option = fileChooser.showSaveDialog(newPage);
                        if (option == JFileChooser.APPROVE_OPTION) {
                            File file = fileChooser.getSelectedFile();
                            try {
                                FileWriter writer = new FileWriter(file);
                                writer.write(area.getText());
                                writer.close();
                            } catch (IOException ex) {
                                System.out.println("Erro: " + ex);
                            }
                        }
                    }
                });

                JButton openButton = new JButton("Abrir");
                openButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JFileChooser fileChooser = new JFileChooser();
                        int option = fileChooser.showOpenDialog(newPage);
                        if (option == JFileChooser.APPROVE_OPTION) {
                            File file = fileChooser.getSelectedFile();
                            try {
                                BufferedReader reader = new BufferedReader(new FileReader(file));
                                area.read(reader, null);
                                reader.close();
                            } catch (IOException ex) {
                                System.out.println("Erro: " + ex);
                            }
                        }
                    }
                });

                JPanel buttonPanel = new JPanel();
                buttonPanel.add(saveButton);
                buttonPanel.add(openButton);

                newPage.setLayout(new BorderLayout());
                newPage.add(buttonPanel, BorderLayout.NORTH);
                newPage.add(new JScrollPane(area), BorderLayout.CENTER);
                newPage.setVisible(true);
            }
        });

        AnotacoesMenu.add(openItem);
        menuBar.add(AnotacoesMenu);
        frame.setJMenuBar(menuBar);
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

                mapItensAgendados.addItemAgendado(calendarioUI.getCalendarModel().getLocalDate(),
                        new ItemAgendado(titulo, calendarioUI.getCalendarModel().getLocalDate().atStartOfDay()));
                ListaItensAgendados lista = mapItensAgendados.get(calendarioUI.getCalendarModel().getLocalDate());
                calendarioUI.getCalendarTable().repaint();
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

        calendarioUI.add(layeredPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}