package entidades;
import java.util.ArrayList;
import java.util.List;

public class Evento {
    private String titulo;
    private String dataInicio;
    private String dataFim;

    private String descricao;
    private List<Convidado> convidados;
    
    public Evento(String titulo, String dataInicio,String dataFim, String descricao) {
        this.titulo = titulo;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.convidados = new ArrayList<>();
        this.descricao = descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String date) {
        this.dataInicio = date;
    }
    
    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public void addConvidado(String nome){
        convidados.add(new Convidado(nome));
    }

    @Override
    public String toString() {
        return "Evento [titulo=" + titulo + ", date=" + dataInicio + ", descricao=" + descricao + ", convidados=" + convidados
                + "]";
    }

    
}
