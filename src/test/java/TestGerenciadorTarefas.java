import org.example.model.Tarefa;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class TestGerenciadorTarefas {

    @Test
    public void testCriarTarefa() {
        Tarefa tarefaTest = new Tarefa("fazer testes", "fazer testagem usando tdd", LocalDate.of(2018, 9, 28), "alta");
        assertEquals("fazer testes", tarefaTest.getTitulo());
        assertEquals("fazer testagem usando tdd", tarefaTest.getDescricao());
        assertEquals( LocalDate.of(2018, 9, 28), tarefaTest.getDataVencimento());
        assertEquals("alta", tarefaTest.getPrioridade());
    }

}
