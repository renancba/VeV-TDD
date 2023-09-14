package org.example.controller;

import org.example.Enum.Prioridade;
import org.example.model.Tarefa;
import org.example.repository.GerenciadorData;

import java.time.LocalDate;
import java.util.Comparator;
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

    public boolean removerIndex(int index){
        return tarefaData.removerTarefa(index);
    }

    public void editarTitulo(int index, String tituloNovo){
        tarefaData.getTarefa(index).setTitulo(tituloNovo);
    }
    public void editarDescricao(int index, String descricaoNova){
        tarefaData.getTarefa(index).setDescricao(descricaoNova);
    }
    public void editarData(int index, LocalDate dataNova){
        tarefaData.getTarefa(index).setDataVencimento(dataNova);
    }
    public void editarPrioridade(int index, Prioridade prioridadeNova){
        tarefaData.getTarefa(index).setPrioridade(prioridadeNova);
    }

    public List<Tarefa> getTarefasOrdenadas() {
        List<Tarefa> tarefas = tarefaData.getTarefas();
        return tarefas.stream().sorted(Comparator.comparing(Tarefa::getDataVencimento).thenComparing(Tarefa::getPrioridade)).toList();
    }
    public Tarefa getTarefa(int index) {
       return tarefaData.getTarefa(index);
    }
}
