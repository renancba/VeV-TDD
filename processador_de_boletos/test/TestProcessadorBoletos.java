import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class TestProcessadorBoletos {
    
    @Test
    public void testFaturaPagaComBoletos() throws ParseException {
        Fatura fatura = new Fatura("Cliente", 1500.0, "10-04-2017");
        List <Boleto> boletos = Arrays.asList(
            new Boleto(1, "10-04-2017", 249.5),
            new Boleto(2, "10-04-2017", 400.0),
            new Boleto(3, "10-04-2017", 849.5),
            new Boleto(4, "10-04-2017", 1.0)
        );

        ProcessadorBoletos processador = new ProcessadorBoletos();
        processador.processarBoletos(fatura, boletos);

        assertTrue(fatura.isPaga());
    }

    @Test
    public void testFaturaNaoPagaComBoletos() throws ParseException {
        Fatura fatura = new Fatura("Cliente", 1500.0, "10-04-2017");
        List <Boleto> boletos = Arrays.asList(
            new Boleto(1, "10-04-2017", 249.5),
            new Boleto(2, "10-04-2017", 400.0),
            new Boleto(3, "10-04-2017", 849.5)
        );

        ProcessadorBoletos processador = new ProcessadorBoletos();
        processador.processarBoletos(fatura, boletos);

        assertTrue(!fatura.isPaga());
    }

    @Test
    public void testFaturaComBoletosAtrasados() throws ParseException {
        Fatura fatura = new Fatura("Cliente", 1500.0, "10-03-2017");
        List <Boleto> boletos = Arrays.asList(
            new Boleto(1, "10-04-2017", 249.5),
            new Boleto(2, "10-04-2017", 400.0),
            new Boleto(3, "10-04-2017", 849.5),
            new Boleto(4, "10-04-2017", 1.0)
        );

        ProcessadorBoletos processador = new ProcessadorBoletos();
        processador.processarBoletos(fatura, boletos);

        assertTrue(!fatura.isPaga());
    }
}
