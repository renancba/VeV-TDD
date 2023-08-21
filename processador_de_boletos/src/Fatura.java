
public class Fatura {
    private String cliente;
    private double valor;
    private String data;

    public Fatura(String cliente, double valor, String data) {
        this.cliente = cliente;
        this.valor = valor;
        this.data = data;
    }

    public String getCliente() {
        return this.cliente;
    }

    public double getValor() {
        return this.valor;
    }

    public String getData() {
        return this.data;
    }

    public boolean isPaga() {
        return this.valor == 0.0;
    }

    public void adicionaPagamento(Pagamento pagamento) {
        if (pagamento.getValor() >= this.valor) {
            this.valor = 0.0;
        } else {
            this.valor -= pagamento.getValor();
        }
    }

    public String toString() {
        return "Fatura de " + this.cliente + " no valor de " + this.valor + " reais.";
    }
}
