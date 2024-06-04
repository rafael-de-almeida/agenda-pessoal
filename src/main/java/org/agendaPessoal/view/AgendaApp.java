package org.agendaPessoal.view;

import org.agendaPessoal.agenda.ListaItens;
import org.agendaPessoal.agenda.ListaItensDoDia;
import org.agendaPessoal.agenda.PainelAgenda;
import org.agendaPessoal.anotacao.AnotacaoPanel;
import org.agendaPessoal.calendario.CalendarioPanel;
import org.agendaPessoal.entidades.MapItensAgendados;
import org.agendaPessoal.entidades.Tarefa;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AgendaApp extends JFrame {
    private static final String APP_TITLE = "Agenda Pessoal";

    private MapItensAgendados mapItensAgendados;
    private ListaItensDoDia agendaPanel;
    private ListaItens itensAgendadosPanel;

    private CalendarioPanel calendarioUI;
    private JPanel rightContainer;

    public AgendaApp() {
        this.setTitle(APP_TITLE);
        this.setSize(500, 500);
        this.setLayout(new BorderLayout());

        initialize();
    }

    private void initialize() {

        mapItensAgendados = new MapItensAgendados();
        Tarefa tarefa = new Tarefa("Apresentação Java", LocalDateTime.now(), LocalDateTime.now().plusHours(2), false,
                1);
        LocalDate date = LocalDate.of(2024, 6, 12);
        mapItensAgendados.addItemAgendado(date, tarefa);

        createAgendaPanel();
        criarConteudoPrincipal();
        createTopMenu();
        finalizeInitialization();
    }

    private void createAgendaPanel() {
        agendaPanel = new ListaItensDoDia();
        itensAgendadosPanel = new ListaItens();
    }

    private void criarConteudoPrincipal() {
        rightContainer = new JPanel();
        rightContainer.setLayout(new GridLayout(2, 1));
        calendarioUI = new CalendarioPanel(mapItensAgendados, agendaPanel, itensAgendadosPanel);
        PainelAgenda painelAgenda = new PainelAgenda(mapItensAgendados, calendarioUI, agendaPanel, itensAgendadosPanel);

        rightContainer.add(calendarioUI);
        rightContainer.add(painelAgenda);

    }

    private void createTopMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu AnotacoesMenu = new JMenu("Anotações");
        JMenuItem openItem = new JMenuItem("Abrir");

        openItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AnotacaoPanel();
            }
        });

        AnotacoesMenu.add(openItem);
        menuBar.add(AnotacoesMenu);
        this.setJMenuBar(menuBar);
    }

    private void finalizeInitialization() {
        this.add(rightContainer, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}