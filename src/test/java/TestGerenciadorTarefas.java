import org.example.controller.GerenciadorTarefas;
import org.example.model.Tarefa;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestGerenciadorTarefas {

    private GerenciadorTarefas gerenciador;

    @BeforeEach
    public void init() {
        this.gerenciador = new GerenciadorTarefas();
    }

    @Test
    public void testCriarTarefa() {
        Tarefa tarefaTest = new Tarefa("fazer testes", "fazer testagem usando tdd", LocalDate.of(2018, 9, 28), "alta");
        assertEquals("fazer testes", tarefaTest.getTitulo());
        assertEquals("fazer testagem usando tdd", tarefaTest.getDescricao());
        assertEquals( LocalDate.of(2018, 9, 28), tarefaTest.getDataVencimento());
        assertEquals("alta", tarefaTest.getPrioridade());
    }

    @Test
    public void testGerenciadorTarefa(){
        Tarefa tarefaTest = new Tarefa("fazer testes", "fazer testagem usando tdd", LocalDate.of(2018, 9, 28), "alta");
        gerenciador.adicionar(tarefaTest);
        assertTrue(1, gerenciador.listarTarefas().size());
    }

}
