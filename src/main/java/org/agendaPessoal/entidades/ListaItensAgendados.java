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

    /**
     * Adiciona um item agendado à lista.
     *
     * @param item O item agendado a ser adicionado.
     */
    public void adicionarItemAgendado(ItemAgendado item) {
        itensAgendados.add(item);
    }

    /**
     * Adiciona todos os itens agendados de uma lista à lista.
     *
     * @param lista A lista de itens agendados a serem adicionados.
     */
    public void addAll(List<ItemAgendado> lista) {
        itensAgendados.addAll(lista);
    }

    /**
     * Retorna uma representação em string de todos os itens agendados na lista.
     *
     * @return Um array de strings representando os itens agendados.
     */
    public String[] getItems() {
        String[] itensAgendadosArray = itensAgendados.stream()
                .map(item -> item.displayItem())
                .toArray(String[]::new);
        return itensAgendadosArray;
    }

    /**
     * Retorna uma representação em string de todos os itens agendados na lista.
     *
     * @return Um array de strings representando os itens agendados.
     */
    public String[] getDataFromObjects() {
        String[] itensAgendadosArray = itensAgendados.stream()
                .map(item -> item.toString())
                .toArray(String[]::new);
        return itensAgendadosArray;
    }

    /**
     * Retorna uma cópia da lista de itens agendados.
     *
     * @return Uma cópia da lista de itens agendados.
     */
    public List<ItemAgendado> getItensAgendados() {
        return new ArrayList<>(itensAgendados);
    }
}