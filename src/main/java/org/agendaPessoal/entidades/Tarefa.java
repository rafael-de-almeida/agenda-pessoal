package org.agendaPessoal.entidades;

import java.time.LocalDateTime;

/**
 * Armazena tarefas geradas pelo calendario
 */
public class Tarefa extends ItemAgendado {
    private boolean concluida;
    private int prioridade;

    public Tarefa(String titulo, LocalDateTime dataInicio, LocalDateTime dataFim, boolean concluida, int prioridade) {
        super(titulo, dataFim);
        this.concluida = concluida;
        this.prioridade = prioridade;
    }

    public Tarefa(String titulo, LocalDateTime dataInicio, LocalDateTime dataFim, boolean semanal, boolean anual,
            boolean diario, boolean mensal, String descricao, boolean concluida, int prioridade) {
        super(titulo, dataInicio, dataFim, semanal, anual, diario, mensal, descricao);
        this.concluida = concluida;
        this.prioridade = prioridade;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    @Override
    public String toString() {
        return "tarefa," + titulo + "," + dataInicio + "," + dataFim + ","
                + descricao + "," + diario + "," + semanal + "," + mensal + "," + anual + "," + prioridade + ","
                + concluida;
    }

    @Override
    public String displayItem() {
        return titulo + "," + descricao;
    }

}
