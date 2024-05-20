package org.agendaPessoal.entidades;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Evento extends ItemAgendado {
    List<Convidado> convidados;

    public Evento(String titulo, LocalDateTime dataInicio, LocalDateTime dataFim) {
        super(titulo, dataInicio, dataFim);
        this.convidados = new ArrayList<>();
    }

    public Evento(String titulo, LocalDateTime dataInicio, LocalDateTime dataFim, boolean semanal, boolean anual,
            boolean diario, boolean mensal, String descricao, List<Convidado> convidados) {
        super(titulo, dataInicio, dataFim, semanal, anual, diario, mensal, descricao);
        this.convidados = new ArrayList<>();
        ;
    }

    @Override
    public String toString() {
        return "Evento," + titulo + "," + dataInicio + "," + dataFim + ","
                + descricao + "," + diario + "," + semanal + "," + mensal + "," + anual + "," + convidados;
    }

    @Override
    public String displayItem() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" dd/MM/yyyy HH:mm");
        return titulo + ", inicio=" + dataInicio.format(formatter) + ", fim: " + dataFim.format(formatter)
                + ", descricao:" + descricao + ", convidados:" + convidados;
    }

}
