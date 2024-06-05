package org.agendaPessoal.entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Classe responsável por manter uma lista de itens agendados de acordo com o
 * dia (LocalDate)
 */
public class MapItensAgendados {
    private TreeMap<LocalDate, ListaItensAgendados> itensAgendados;

    public MapItensAgendados() {
        this.itensAgendados = new TreeMap<>();
    }

    public void addListaItemAgendado(LocalDate date, ListaItensAgendados lista) {
        if (itensAgendados.containsKey(date)) {
            ListaItensAgendados exitingList = itensAgendados.get(date);
            exitingList.addAll(lista.getItensAgendados());
        } else {
            itensAgendados.put(date, lista);
        }
    }

    public void addItemAgendado(LocalDate date, ItemAgendado item) {
        if (itensAgendados.containsKey(date)) {
            ListaItensAgendados exitingList = itensAgendados.get(date);
            exitingList.adicionarItemAgendado(item);
        } else {
            ListaItensAgendados lista = new ListaItensAgendados();
            lista.adicionarItemAgendado(item);
            itensAgendados.put(date, lista);
        }

    }

    public ListaItensAgendados get(LocalDate date) {
        return itensAgendados.get(date);
    }

    public int size() {
        return itensAgendados.size();
    }

    public Map<LocalDate, ListaItensAgendados> getAllItensOfMonth(int month) {
        Map<LocalDate, ListaItensAgendados> itensDoMes = new HashMap<>();
        for (Map.Entry<LocalDate, ListaItensAgendados> entry : itensAgendados.entrySet()) {
            if (entry.getKey().getMonthValue() == month) {
                itensDoMes.put(entry.getKey(), entry.getValue());
            }
        }
        return itensDoMes;
    }

    public List<ListaItensAgendados> getItensAgendadosOrdered() {
        List<ListaItensAgendados> list = new ArrayList<>(itensAgendados.values());
        return list;
    }

}
