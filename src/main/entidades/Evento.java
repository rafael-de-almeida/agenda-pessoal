package entidades;

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
            boolean diario, String descricao, List<Convidado> convidados) {
        super(titulo, dataInicio, dataFim, semanal, anual, diario, descricao);
        this.convidados = new ArrayList<>();
        ;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" dd/MM/yyyy HH:mm");
        return titulo + ", inicio=" + dataInicio.format(formatter) + ", fim: " + dataFim.format(formatter)
                + ", descricao:" + descricao + ", convidados:" + convidados;
    }

}
