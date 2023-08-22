package org.example.repository;

import org.example.model.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorData {
    private final List<Tarefa> tarefas;

    public GerenciadorData() {
        this.tarefas = new ArrayList<>();
    }

    public void addTarefa(Tarefa tarefa){
        tarefas.add(tarefa);
    }

    public List<Tarefa> getTarefas() {
        return tarefas;
    }

    public void removerTarefa(Tarefa tarefa){
        tarefas.remove(tarefa);
    }

    public void removerTarefa(int tarefaIndex){
        tarefas.remove(tarefaIndex);
    }
}
