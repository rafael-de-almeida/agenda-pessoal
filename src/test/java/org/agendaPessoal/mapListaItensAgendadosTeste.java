package org.agendaPessoal;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

        Tarefa item2 = new Tarefa("tarefa 1", LocalDateTime.now(), LocalDateTime.now(), false, 0);

        map.addItemAgendado(LocalDate.now(), item2);
        map.addListaItemAgendado(LocalDate.now().plusDays(1), list);

        assertTrue(map.size() == 2);
    }

}
