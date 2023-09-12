import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ProcessadorBoletos {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

    public void processarBoletos(Fatura fatura, List<Boleto> boletos) throws ParseException {
        Date limite = DATE_FORMAT.parse(fatura.getData());

        for (Boleto boleto : boletos) {
            Date dataBoleto = DATE_FORMAT.parse(boleto.getData());
            
            if (dataBoleto.before(limite) || dataBoleto.equals(limite)) {
                Pagamento pagamento = new Pagamento(boleto.getValor(), MeioDePagamento.BOLETO, boleto.getData());
                fatura.adicionaPagamento(pagamento);
            }
        }
        if (fatura.getValor() <= 0) {
            fatura.setPaga(true);
        }
    }
}