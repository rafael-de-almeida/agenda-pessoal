package org.agendaPessoal.entidades;

import java.util.*;

/**
 * Classe que representa uma lista de itens agendados.
 */
public class ListaItensAgendados {
    private List<ItemAgendado> itensAgendados;

    public ListaItensAgendados() {
        this.itensAgendados = new ArrayList<>();
    }

    public void adicionarItemAgendado(ItemAgendado item) {
        itensAgendados.add(item);
    }

    public void addAll(List<ItemAgendado> lista) {
        itensAgendados.addAll(lista);
    }

    public String[] getItems() {
        String[] itensAgendadosArray = itensAgendados.stream()
                .map(item -> item.displayItem())
                .toArray(String[]::new);
        return itensAgendadosArray;
    }

    public String[] getDataFromObjects() {
        String[] itensAgendadosArray = itensAgendados.stream()
                .map(item -> item.toString())
                .toArray(String[]::new);
        return itensAgendadosArray;
    }

    public List<ItemAgendado> getItensAgendados() {
        return new ArrayList<>(itensAgendados);
    }
}