import org.example.Enum.Prioridade;
import org.example.controller.GerenciadorTarefas;
import org.example.model.Tarefa;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestGerenciadorTarefas {

    private GerenciadorTarefas gerenciador;

    @Before
    public void init() {
        this.gerenciador = new GerenciadorTarefas();
    }

    @Test
    public void testCriarTarefa() {
        Tarefa tarefaTest = new Tarefa("fazer testes", "fazer testagem usando tdd", LocalDate.of(2018, 9, 28), Prioridade.ALTA);
        assertEquals("fazer testes", tarefaTest.getTitulo());
        assertEquals("fazer testagem usando tdd", tarefaTest.getDescricao());
        assertEquals( LocalDate.of(2018, 9, 28), tarefaTest.getDataVencimento());
        assertEquals(Prioridade.ALTA, tarefaTest.getPrioridade());
    }

    @Test
    public void testGerenciadorTarefa(){
        gerenciador.adicionar("fazer testes", "fazer testagem usando tdd", LocalDate.of(2018, 9, 28), Prioridade.ALTA);
        assertEquals(1, gerenciador.listar().size());
    }
    @Test
    public void testDeleteTarefaIndex(){
        gerenciador.adicionar("fazer testes", "fazer testagem usando tdd", LocalDate.of(2018, 9, 28), Prioridade.ALTA);
        gerenciador.removerIndex(0);
    }

    @Test
    public void testEditTitutlo(){
        gerenciador.adicionar("fazer testes", "fazer testagem usando tdd", LocalDate.of(2018, 9, 28), Prioridade.ALTA);
        gerenciador.editarTitulo(0, "Fazendo os testes");
    }
    @Test
    public void testEditDescricao(){
        gerenciador.adicionar("fazer testes", "fazer testagem usando tdd", LocalDate.of(2018, 9, 28), Prioridade.ALTA);
        gerenciador.editarDescricao(0, "Fazendo a testagem usando tdd");
    }
    @Test
    public void testEditData(){
        gerenciador.adicionar("fazer testes", "fazer testagem usando tdd", LocalDate.of(2018, 9, 28), Prioridade.ALTA);
        gerenciador.editarData(0, LocalDate.of(2019, 9, 18));
    }
    @Test
    public void testEditPrioridade(){
        gerenciador.adicionar("fazer testes", "fazer testagem usando tdd", LocalDate.of(2018, 9, 28), Prioridade.ALTA);
        gerenciador.editarPrioridade(0, Prioridade.INTERMEDIARIA);
    }

    @Test
    public void testGetTarefasOrdenadas(){
        Tarefa tarefa1 = gerenciador.adicionar("tarefa 1", "descrição 1", LocalDate.of(2023, 8, 31), Prioridade.ALTA);
        Tarefa tarefa2 = gerenciador.adicionar("tarefa 2", "descrição 2", LocalDate.of(2023, 9, 30), Prioridade.INTERMEDIARIA);
        Tarefa tarefa3 = gerenciador.adicionar("tarefa 3", "descrição 3", LocalDate.of(2023, 8, 31), Prioridade.BAIXA);
        Tarefa tarefa4 = gerenciador.adicionar("tarefa 4", "descrição 4", LocalDate.of(2023, 8, 30), Prioridade.ALTA);
        Tarefa tarefa5 = gerenciador.adicionar("tarefa 5", "descrição 5", LocalDate.of(2023, 8, 31), Prioridade.BAIXA);

        List<Tarefa> tarefasOrdenadas = gerenciador.getTarefasOrdenadas();

        assertEquals(tarefa4, tarefasOrdenadas.get(0));
        assertEquals(tarefa5, tarefasOrdenadas.get(1));
        assertEquals(tarefa1, tarefasOrdenadas.get(2));
        assertEquals(tarefa3, tarefasOrdenadas.get(3));
        assertEquals(tarefa2, tarefasOrdenadas.get(4));

    }

}
