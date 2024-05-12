package entidades;

import java.util.*;

public class ListaItensAgendados {
    private List<ItemAgendado> itensAgendados;

    public ListaItensAgendados() {
        this.itensAgendados = new ArrayList<>();

    }

    public void adicionarTarefa(ItemAgendado item) {
        itensAgendados.add(item);
    }

    public String[] getItems() {
        String[] itensAgendadosArray = itensAgendados.stream()
                .map(item -> item.toString())
                .toArray(String[]::new);
        return itensAgendadosArray;
    }
}
