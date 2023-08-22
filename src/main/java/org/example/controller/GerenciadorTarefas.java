package org.example.controller;

import org.example.Enum.Prioridade;
import org.example.model.Tarefa;
import org.example.repository.GerenciadorData;

import java.time.LocalDate;
import java.util.List;

public class GerenciadorTarefas {

    private final GerenciadorData tarefaData;

    public GerenciadorTarefas() {
        this.tarefaData = new GerenciadorData();
    }

    public Tarefa adicionar(String titulo, String descricao, LocalDate dataVencimento, Prioridade prioridade) {
        Tarefa tarefa = new Tarefa(titulo, descricao, dataVencimento, prioridade);
        tarefaData.addTarefa(tarefa);
        return tarefa;
    }

    public List<Tarefa> listar() {
        return tarefaData.getTarefas();
    }

}
