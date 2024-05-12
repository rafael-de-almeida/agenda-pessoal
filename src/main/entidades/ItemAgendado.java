package entidades;

import java.time.LocalDateTime;

/**
 * ScheduledItem
 * Author: Fábio-k
 */
public abstract class ItemAgendado {
    protected String titulo;
    protected LocalDateTime dataInicio;
    protected LocalDateTime dataFim;
    protected boolean semanal;
    protected boolean anual;
    protected boolean diario;
    protected String descricao;

    public ItemAgendado(String titulo, LocalDateTime dataInicio, LocalDateTime dataFim) {
        this.titulo = titulo;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        semanal = false;
        anual = false;
        diario = false;
        descricao = "";
    }

    public ItemAgendado(String titulo, LocalDateTime dataInicio, LocalDateTime dataFim, boolean semanal, boolean anual,
            boolean diario, String descricao) {
        this.titulo = titulo;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.semanal = semanal;
        this.anual = anual;
        this.diario = diario;
        this.descricao = descricao;
    }

    public String toString() {
        return "ItemAgendado [titulo=" + titulo + ", dataInicio=" + dataInicio + ", dataFim=" + dataFim + ", descricao="
                + descricao + "]";
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDateTime getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDateTime dataFim) {
        this.dataFim = dataFim;
    }

    public boolean isSemanal() {
        return semanal;
    }

    public void setSemanal(boolean semanal) {
        this.semanal = semanal;
    }

    public boolean isAnual() {
        return anual;
    }

    public void setAnual(boolean anual) {
        this.anual = anual;
    }

    public boolean isDiario() {
        return diario;
    }

    public void setDiario(boolean diario) {
        this.diario = diario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}