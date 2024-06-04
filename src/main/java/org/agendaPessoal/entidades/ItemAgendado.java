package org.agendaPessoal.entidades;

import java.time.LocalDateTime;

/**
 * ScheduledItem
 */
public class ItemAgendado {
    protected String titulo;
    protected LocalDateTime dataInicio;
    protected LocalDateTime dataFim;
    protected boolean diario;
    protected boolean semanal;
    protected boolean mensal;
    protected boolean anual;

    protected String descricao;

    public ItemAgendado(String titulo) {
        this.titulo = titulo;
        this.dataInicio = null;
        this.dataFim = null;
        diario = false;
        semanal = false;
        mensal = false;
        anual = false;
        descricao = "";
    }

    public ItemAgendado(String titulo, LocalDateTime dataFim) {
        this.titulo = titulo;
        this.dataFim = dataFim;
        this.dataInicio = dataFim;
        diario = false;
        semanal = false;
        mensal = false;
        anual = false;
        descricao = "";
    }

    public ItemAgendado(String titulo, LocalDateTime dataInicio, LocalDateTime dataFim, boolean semanal, boolean anual,
            boolean diario, boolean mensal, String descricao) {
        this.titulo = titulo;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.diario = diario;
        this.semanal = semanal;
        this.mensal = mensal;
        this.anual = anual;
        this.descricao = descricao;
    }

    public String toString() {
        return "ItemAgendado," + titulo + "," + dataInicio + "," + dataFim + ","
                + descricao + "," + diario + "," + semanal + "," + mensal + "," + anual;
    }

    public String displayItem() {
        return dataFim + " " + "item agendado: " + titulo + " " + descricao;
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