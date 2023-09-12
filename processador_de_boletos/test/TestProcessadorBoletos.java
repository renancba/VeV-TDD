import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.ArrayList;

import org.junit.Test;

public class TestProcessadorBoletos {
    
    @Test
    public void testFaturaPagaComBoletos() throws ParseException {
        Fatura fatura = new Fatura("Cliente", 1500.0, "10-04-2017");
        ArrayList<Boleto> boletos = new ArrayList<Boleto>();

        boletos.add(new Boleto(1, "10-04-2017", 249.5));
        boletos.add(new Boleto(2, "10-04-2017", 400.0));
        boletos.add(new Boleto(3, "10-04-2017", 849.5));
        boletos.add(new Boleto(4, "10-04-2017", 1.0));

        ProcessadorBoletos processador = new ProcessadorBoletos();
        processador.processarBoletos(fatura, boletos);

        assertTrue(fatura.getPaga());
    }

    @Test
    public void testFaturaNaoPagaComBoletos() throws ParseException {
        Fatura fatura = new Fatura("Cliente", 1500.0, "10-04-2017");
        ArrayList <Boleto> boletos = new ArrayList<Boleto>();

        boletos.add(new Boleto(1, "10-04-2017", 249.5));
        boletos.add(new Boleto(2, "10-04-2017", 400.0));
        boletos.add(new Boleto(3, "10-04-2017", 849.5));
        boletos.add(new Boleto(4, "10-04-2017", 0.9));

        ProcessadorBoletos processador = new ProcessadorBoletos();
        processador.processarBoletos(fatura, boletos);

        assertTrue(!fatura.getPaga());
    }

    @Test
    public void testFaturaComBoletosAtrasados() throws ParseException {
        Fatura fatura = new Fatura("Cliente", 1500.0, "10-03-2017");
        ArrayList <Boleto> boletos = new ArrayList<Boleto>();

        boletos.add(new Boleto(1, "10-04-2017", 299.5));
        boletos.add(new Boleto(2, "10-04-2017", 350.0));
        boletos.add(new Boleto(3, "9-04-2017", 849.0));
        boletos.add(new Boleto(4, "10-04-2017", 1.5));

        ProcessadorBoletos processador = new ProcessadorBoletos();
        processador.processarBoletos(fatura, boletos);

        assertTrue(!fatura.getPaga());
    }

    @Test
    public void testFaturaComBoletosEmDia() throws ParseException {
        Fatura fatura = new Fatura("Cliente", 1500.0, "10-05-2017");
        ArrayList <Boleto> boletos = new ArrayList<Boleto>();

        boletos.add(new Boleto(1, "10-04-2017", 299.5));
        boletos.add(new Boleto(2, "11-05-2016", 350.0));
        boletos.add(new Boleto(3, "30-06-2014", 849.0));
        boletos.add(new Boleto(4, "09-05-2017", 1.5));

        ProcessadorBoletos processador = new ProcessadorBoletos();
        processador.processarBoletos(fatura, boletos);

        assertTrue(fatura.getPaga());
    }

    @Test
    public void testTipoDosBoletos() throws ParseException {
        Fatura fatura = new Fatura("Cliente", 1500.0, "10-05-2017");
        ArrayList <Boleto> boletos = new ArrayList<Boleto>();

        boletos.add(new Boleto(1, "10-04-2017", 299.5));
        boletos.add(new Boleto(2, "11-05-2016", 350.0));
        boletos.add(new Boleto(3, "30-06-2014", 849.0));
        boletos.add(new Boleto(4, "09-05-2017", 1.5));

        ProcessadorBoletos processador = new ProcessadorBoletos();
        processador.processarBoletos(fatura, boletos);

        assertTrue(fatura.getPagamentos().get(0).getForma() == MeioDePagamento.BOLETO);
    }

    @Test
    public void testFaturaAbaixoDoMinimoVencidaBoletoAbaixoDoMinimoPagoAtrasado() throws ParseException {
        Fatura fatura = new Fatura("Cliente", 100.0, "10-01-2022");
        ArrayList<Boleto> boletos = new ArrayList<Boleto>();

        boletos.add(new Boleto(1, "10-01-2022", 50.0));

        ProcessadorBoletos processador = new ProcessadorBoletos();
        processador.processarBoletos(fatura, boletos);

        assertEquals(false, fatura.getPaga());
    }

    @Test
    public void testFaturaAbaixoDoMinimoDentroDoPrazoBoletoDentroDosLimitesPagoATempo() throws ParseException {
        Fatura fatura = new Fatura("Cliente", 90.0, "10-01-2022");
        ArrayList<Boleto> boletos = new ArrayList<Boleto>();

        boletos.add(new Boleto(1, "10-01-2022", 90.0));

        ProcessadorBoletos processador = new ProcessadorBoletos();
        processador.processarBoletos(fatura, boletos);

        assertEquals(false, fatura.getPaga());
    }

    @Test
    public void testFaturaDentroDosLimitesDentroDoPrazoBoletoDentroDosLimitesPagoNoPrazo() throws ParseException {
        Fatura fatura = new Fatura("Cliente", 150.0, "10-01-2022");
        ArrayList<Boleto> boletos = new ArrayList<Boleto>();

        boletos.add(new Boleto(1, "10-01-2022", 150.0));

        ProcessadorBoletos processador = new ProcessadorBoletos();
        processador.processarBoletos(fatura, boletos);

        assertEquals(true, fatura.getPaga());
    }

    @Test
    public void testFaturaAcimaDoMaximoFuturaBoletoAcimaDoMaximoPagoATempo() throws ParseException {
        Fatura fatura = new Fatura("Cliente", 200.0, "10-01-2022");
        ArrayList<Boleto> boletos = new ArrayList<Boleto>();

        boletos.add(new Boleto(1, "10-01-2023", 250.0));

        ProcessadorBoletos processador = new ProcessadorBoletos();
        processador.processarBoletos(fatura, boletos);

        assertEquals(false, fatura.getPaga());
    }

    @Test
    public void testFaturaDentroDosLimitesDentroDoPrazoBoletoAbaixoDoMinimoPagoNoPrazo() throws ParseException {
        Fatura fatura = new Fatura("Cliente", 150.0, "10-01-2022");
        ArrayList<Boleto> boletos = new ArrayList<Boleto>();

        boletos.add(new Boleto(1, "10-01-2022", 100.0));

        ProcessadorBoletos processador = new ProcessadorBoletos();
        processador.processarBoletos(fatura, boletos);

        assertEquals(false, fatura.getPaga());
    }
}
