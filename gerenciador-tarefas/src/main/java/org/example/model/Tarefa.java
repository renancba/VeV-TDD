package org.example.model;

import org.example.Enum.Prioridade;

import java.time.LocalDate;
import java.util.Objects;

public class Tarefa {

    private String titulo;

    private String descricao;

    private LocalDate dataVencimento;

    private Prioridade prioridade;

    public Tarefa(String titulo, String descricao, LocalDate dataVencimento, Prioridade prioridade) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataVencimento = dataVencimento;
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

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tarefa tarefa = (Tarefa) o;
        return Objects.equals(titulo, tarefa.titulo) && Objects.equals(descricao, tarefa.descricao) && Objects.equals(dataVencimento, tarefa.dataVencimento) && prioridade == tarefa.prioridade;
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, descricao, dataVencimento, prioridade);
    }

    @Override
    public String toString() {
        return "Tarefa{" +
                "titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dataVencimento=" + dataVencimento +
                ", prioridade='" + prioridade + '\'' +
                '}';
    }
}
