package entidades;

import java.util.*;
import java.util.function.*;

public class ListaTarefas {
    private Set<Tarefa> tarefas;

    public ListaTarefas() {
        this.tarefas = new HashSet<>();

    }

    public void adicionarTarefa(String titulo, String descricao, boolean concluida, boolean semanal, boolean anual,
            boolean diario,
            int prioridade) {
        tarefas.add(new Tarefa(titulo, descricao, concluida, semanal, anual, diario, prioridade));
    }

    public List<Tarefa> getTarefaComPrioridade(int prioridade) {
        Predicate<Tarefa> igualPrioridade = tarefa -> tarefa.getPrioridade() == prioridade;

        List<Tarefa> tarefaComPrioridade = tarefas.stream().filter(igualPrioridade).toList();

        return tarefaComPrioridade;
    }
}
