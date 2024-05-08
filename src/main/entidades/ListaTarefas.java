package entidades;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

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

    public void exibirTarefaPorPrioridade(int prioridade) {
        Consumer<Tarefa> imprimirTarefaPorPrioridade = tarefa -> {
            if (tarefa.getPrioridade() == prioridade) {
                System.out.println(tarefa);
            }
        };

        tarefas.stream().forEach(imprimirTarefaPorPrioridade);
    }
}
