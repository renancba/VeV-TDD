import org.example.Enum.Prioridade;
import org.example.controller.GerenciadorTarefas;
import org.example.model.Tarefa;
import org.junit.jupiter.api.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestGerenciadorNovo {

    private GerenciadorTarefas gerenciador;

    @BeforeEach
    public void init() {
        this.gerenciador = new GerenciadorTarefas();
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Init {
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Cleanup {
    }

    @Init
    @Test
    public void testCriarTarefa() {
        Tarefa tarefaTest = new Tarefa("fazer testes", "fazer testagem usando tdd", LocalDate.of(2018, 9, 28), Prioridade.ALTA);
        assertEquals("fazer testes", tarefaTest.getTitulo());
        assertEquals("fazer testagem usando tdd", tarefaTest.getDescricao());
        assertEquals(LocalDate.of(2018, 9, 28), tarefaTest.getDataVencimento());
        assertEquals(Prioridade.ALTA, tarefaTest.getPrioridade());
    }

    @Cleanup
    @Test
    public void testGerenciadorTarefa() {
        gerenciador.adicionar("fazer testes", "fazer testagem usando tdd", LocalDate.of(2018, 9, 28), Prioridade.ALTA);
        assertEquals(1, gerenciador.listar().size());
    }

    @Cleanup
    @Test
    public void testDeleteTarefaIndex() {
        gerenciador.adicionar("fazer testes", "fazer testagem usando tdd", LocalDate.of(2018, 9, 28), Prioridade.ALTA);
        gerenciador.removerIndex(0);
        assertEquals(0, gerenciador.listar().size());
    }

    @Cleanup
    @Test
    public void testEditTitutlo() {
        Tarefa tarefa = gerenciador.adicionar("fazer testes", "fazer testagem usando tdd", LocalDate.of(2018, 9, 28), Prioridade.ALTA);
        gerenciador.editarTitulo(0, "Fazendo os testes");
        assertEquals("Fazendo os testes", tarefa.getTitulo());
    }

    @Cleanup
    @Test
    public void testEditDescricao() {
        Tarefa tarefa = gerenciador.adicionar("fazer testes", "fazer testagem usando tdd", LocalDate.of(2018, 9, 28), Prioridade.ALTA);
        gerenciador.editarDescricao(0, "Fazendo a testagem usando tdd");
        assertEquals("Fazendo a testagem usando tdd", tarefa.getDescricao());
    }

    @Cleanup
    @Test
    public void testEditData() {
        Tarefa tarefa = gerenciador.adicionar("fazer testes", "fazer testagem usando tdd", LocalDate.of(2018, 9, 28), Prioridade.ALTA);
        gerenciador.editarData(0, LocalDate.of(2019, 9, 18));
        assertEquals(LocalDate.of(2019, 9, 18), tarefa.getDataVencimento());
    }

    @Cleanup
    @Test
    public void testEditPrioridade() {
        Tarefa tarefa = gerenciador.adicionar("fazer testes", "fazer testagem usando tdd", LocalDate.of(2018, 9, 28), Prioridade.ALTA);
        gerenciador.editarPrioridade(0, Prioridade.INTERMEDIARIA);
        assertEquals(Prioridade.INTERMEDIARIA, tarefa.getPrioridade());
    }

    @Test
    public void testGetTarefasOrdenadas() {
        Tarefa tarefa1 = gerenciador.adicionar("tarefa 1", "descrição 1", LocalDate.of(2023, 8, 31), Prioridade.ALTA);
        Tarefa tarefa2 = gerenciador.adicionar("tarefa 2", "descrição 2", LocalDate.of(2023, 9, 30), Prioridade.INTERMEDIARIA);
        Tarefa tarefa3 = gerenciador.adicionar("tarefa 3", "descrição 3", LocalDate.of(2023, 8, 31), Prioridade.BAIXA);
        Tarefa tarefa4 = gerenciador.adicionar("tarefa 4", "descrição 4", LocalDate.of(2023, 8, 30), Prioridade.ALTA);
        Tarefa tarefa5 = gerenciador.adicionar("tarefa 5", "descrição 5", LocalDate.of(2023, 8, 31), Prioridade.BAIXA);

        List<Tarefa> tarefasOrdenadas = gerenciador.getTarefasOrdenadas();

        assertEquals(tarefa4, tarefasOrdenadas.get(0));
        assertEquals(tarefa1, tarefasOrdenadas.get(1));
        assertEquals(tarefa3, tarefasOrdenadas.get(2));
        assertEquals(tarefa5, tarefasOrdenadas.get(3));
        assertEquals(tarefa2, tarefasOrdenadas.get(4));
    }

    @Test
    public void testRemoverTarefaComPrioridadeBaixaVencida() {
        Tarefa tarefa = gerenciador.adicionar("tarefa 1", "descrição 1", LocalDate.of(2022, 8, 31), Prioridade.BAIXA);
        boolean result = gerenciador.removerIndex(0);
        assertTrue(result);
    }

    @Test
    public void testRemoverTarefaComPrioridadeBaixaDentroPrazo() {
        Tarefa tarefa = gerenciador.adicionar("tarefa qualquer", "descrição qualquer", LocalDate.of(2023, 8, 31), Prioridade.BAIXA);
        boolean result = gerenciador.removerIndex(0);
        assertTrue(result);
    }

    @Test
    public void testExistenciaTarefaComPrioridadeBaixaVencida() {
        Tarefa tarefa = gerenciador.adicionar("tarefa qualquer", "descrição qualquer", LocalDate.of(2022, 8, 31), Prioridade.BAIXA);
        assertEquals(1, gerenciador.listar().size());
    }

    @Test
    public void testExistenciaTarefaComPrioridadeAltaFutura() {
        Tarefa tarefa = gerenciador.adicionar("tarefa qualquer", "descrição qualquer", LocalDate.of(2024, 8, 31), Prioridade.ALTA);
        assertEquals(1, gerenciador.listar().size());
    }

    @Test
    public void testExistenciaTarefaComPrioridadeMediaVencida() {
        Tarefa tarefa = gerenciador.adicionar("tarefa qualquer", "descrição qualquer", LocalDate.of(2022, 8, 31), Prioridade.INTERMEDIARIA);
        assertEquals(1, gerenciador.listar().size());
    }
}
