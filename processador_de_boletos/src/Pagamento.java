
public class Pagamento {
    private double valor;
    private MeioDePagamento forma;
    private String data;

    public Pagamento(double valor, MeioDePagamento forma, String data) {
        this.valor = valor;
        this.forma = forma;
        this.data = data;
    }

    public double getValor() {
        return this.valor;
    }

    public MeioDePagamento getForma() {
        return this.forma;
    }

    public String getData() {
        return this.data;
    }
}
