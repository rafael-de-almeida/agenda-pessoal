package org.agendaPessoal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.agendaPessoal.entidades.Evento;
import org.agendaPessoal.entidades.ListaItensAgendados;
import org.agendaPessoal.entidades.MapItensAgendados;
import org.agendaPessoal.entidades.Tarefa;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class mapListaItensAgendadosTeste {
    @Test
    void deveAdicionarItemListaItem() {
        MapItensAgendados map = new MapItensAgendados();
        ListaItensAgendados list = new ListaItensAgendados();

        Evento item1 = new Evento("Evento 1", LocalDateTime.now(), LocalDateTime.now());
        Tarefa item2 = new Tarefa("tarefa 1", LocalDateTime.now(), LocalDateTime.now(), false, 0);

        list.adicionarItemAgendado(item1);

        map.addItemAgendado(LocalDate.now(), item2);
        map.addListaItemAgendado(LocalDate.now().plusDays(1), list);

        assertTrue(map.size() == 2);
    }

    @Test
    void deveRetornarItensEmOrder() {
        MapItensAgendados map = new MapItensAgendados();

        Evento item1 = new Evento("Evento 1", LocalDateTime.now(), LocalDateTime.now());
        Tarefa item2 = new Tarefa("tarefa 1", LocalDateTime.now(), LocalDateTime.now(), false, 0);

        map.addItemAgendado(LocalDate.now(), item2);
        map.addItemAgendado(LocalDate.now().plusDays(1), item1);

        List<LocalDate> dates = new ArrayList<>(map.getItensAgendadosOrdered().keySet());

        for (int i = 0; i < dates.size() - 1; i++) {
            assertTrue(dates.get(i).compareTo(dates.get(i + 1)) <= 0);
        }
    }
}
