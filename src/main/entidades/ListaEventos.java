package entidades;
import java.util.ArrayList;
import java.util.List;

public class ListaEventos {
    private List<Evento> listaEventos;

    public ListaEventos(List<Evento> listaEventos) {
        this.listaEventos = new ArrayList<>();
    }

    public void addEvento(String titulo, String dataInicio, String dataFim, String descricao){
        listaEventos.add(new Evento(titulo,dataInicio, dataFim, descricao));
    }
    
    public Evento pesquisarPorTitulo(String titulo){
        Evento eventosEncontrados = null;
        if(!listaEventos.isEmpty()){
            for(Evento evento : listaEventos){
                if(evento.getTitulo().equalsIgnoreCase(titulo)){
                    eventosEncontrados = evento;
                }
            }
        }
        return eventosEncontrados;
    }
}
