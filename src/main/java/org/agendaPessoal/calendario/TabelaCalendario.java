package org.agendaPessoal.calendario;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import org.agendaPessoal.agenda.ListaItens;
import org.agendaPessoal.agenda.ListaItensDoDia;
import org.agendaPessoal.entidades.ListaItensAgendados;
import org.agendaPessoal.entidades.MapItensAgendados;

public class TabelaCalendario extends JTable {
    private static final Color SELECTED_COLOR = Color.GREEN;
    private static final Color DEFAULT_COLOR = Color.WHITE;
    private static final Color TEXT_COLOR = Color.BLACK;
    private static final Color SCHEDULED_COLOR = Color.CYAN;
    private LocalDate selectedDate;

    private ModeloCalendario model;
    private MapItensAgendados mapItensAgendados;

    private DefaultTableCellRenderer renderer;
    private ListaItens itensAgendadosPanel;

    public TabelaCalendario(ModeloCalendario model, MapItensAgendados mapItensAgendados, ListaItensDoDia agendaPanel,
            ListaItens itensAgendadosPanel) {
        super(model.getModel());
        this.model = model;
        this.mapItensAgendados = mapItensAgendados;
        this.itensAgendadosPanel = itensAgendadosPanel;

        getTableHeader().setReorderingAllowed(false);
        TableColumnModel columnModel = getColumnModel();
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            columnModel.getColumn(i).setResizable(false);
        }

        LocalDate currentDate = LocalDate.now();
        ListaItensAgendados listaAtividadesDoDia = mapItensAgendados.get(currentDate);
        updatePanels(agendaPanel, listaAtividadesDoDia);

        renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus,
                    int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setVerticalAlignment(TOP);
                setHorizontalAlignment(LEFT);
                setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                if (isSelected) {
                    setBorder(BorderFactory.createLineBorder(Color.BLUE));
                }
                return this;
            }
        };
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleMouseClick(e, agendaPanel);
            }
        });
    }

    private void handleMouseClick(MouseEvent evento, ListaItensDoDia agendaPanel) {
        int row = rowAtPoint(evento.getPoint());
        int col = columnAtPoint(evento.getPoint());
        if (row >= 0 && col >= 0) {
            Object day = getValueAt(row, col);
            System.out.println("Selected value: " + day);

            if (day == null) {
                updatePanels(agendaPanel, null);
                selectedDate = null;
                return;
            }

            model.setSelectedDay((int) day);
            selectedDate = model.getLocalDate();

            ListaItensAgendados lista = mapItensAgendados.get(selectedDate);
            updatePanels(agendaPanel, lista);
        }
    }

    private void updatePanels(ListaItensDoDia agendaPanel, ListaItensAgendados lista) {
        ListaItensAgendados novaLista = new ListaItensAgendados();
        List<ListaItensAgendados> allItens = mapItensAgendados.getItensAgendadosOrdered();
        itensAgendadosPanel.update(allItens);

        if (lista == null) {
            agendaPanel.update(novaLista);
            return;
        }
        agendaPanel.update(lista);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    @Override
    public TableCellRenderer getCellRenderer(int row, int column) {
        return renderer;
    }

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component c = super.prepareRenderer(renderer, row, column);
        c.setBackground(getCellBackgroundColor(row, column));
        c.setForeground(TEXT_COLOR);
        return c;
    }

    @Override
    public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend) {
        Object value = getValueAt(rowIndex, columnIndex);
        if (value == null || value.toString().isEmpty()) {
            return;
        }
        super.changeSelection(rowIndex, columnIndex, toggle, extend);
    }

    private Color getCellBackgroundColor(int row, int column) {
        Object value = getValueAt(row, column);
        if (value instanceof Integer) {
            int cellDay = (Integer) value;

            Calendar currentDate = Calendar.getInstance();
            int currentDay = currentDate.get(Calendar.DAY_OF_MONTH);
            int currentMonth = currentDate.get(Calendar.MONTH);
            int currentYear = currentDate.get(Calendar.YEAR);

            LocalDate dateToCheck = LocalDate.of(model.getYear(), model.getMonth() + 1, cellDay);

            Map<LocalDate, ListaItensAgendados> itensMesSelecionado = mapItensAgendados
                    .getAllItensOfMonth(model.getMonth() + 1);

            if (cellDay == currentDay && model.getYear() == currentYear && model.getMonth() == currentMonth) {
                return SELECTED_COLOR;
            } else if (itensMesSelecionado.containsKey(dateToCheck)) {
                return SCHEDULED_COLOR;
            }

        }
        return DEFAULT_COLOR;
    }
}