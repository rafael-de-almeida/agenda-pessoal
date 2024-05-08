package entidades;

public class Tarefa{
    private String titulo;
    private String descricao;
    private boolean concluida;
    private boolean semanal;
    private boolean anual;
    private boolean diario;
    private int prioridade;

    public Tarefa(String titulo, String descricao, boolean concluida, boolean semanal, boolean anual, boolean diario,
            int prioridade) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.concluida = concluida;
        this.semanal = semanal;
        this.anual = anual;
        this.diario = diario;
        this.prioridade = prioridade;
    }
    

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public boolean isConcluida() {
        return concluida;
    }
    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }
    public boolean isSemanal() {
        return semanal;
    }
    public void setSemanal(boolean Semanalmente) {
        this.semanal = Semanalmente;
    }
    public boolean isAnual() {
        return anual;
    }
    public void setAnual(boolean Anualmente) {
        this.anual = Anualmente;
    }
    public boolean isDiario() {
        return diario;
    }
    public void setDiario(boolean Diariamente) {
        this.diario = Diariamente;
    }
    public int getPrioridade() {
        return prioridade;
    }
    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }
    @Override
    public String toString() {
        return "tarefas [titulo=" + titulo + ", descricao=" + descricao + ", prioridade=" + prioridade + "]";
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
        result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Tarefa other = (Tarefa) obj;
        if (titulo == null) {
            if (other.titulo != null)
                return false;
        } else if (!titulo.equals(other.titulo))
            return false;
        if (descricao == null) {
            if (other.descricao != null)
                return false;
        } else if (!descricao.equals(other.descricao))
            return false;
        return true;
    }

    
}
