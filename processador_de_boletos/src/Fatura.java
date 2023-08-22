import java.util.ArrayList;

public class Fatura {
    private String cliente;
    private double valor;
    private String data;
    private ArrayList<Pagamento> pagamentos;

    public Fatura(String cliente, double valor, String data) {
        this.cliente = cliente;
        this.valor = valor;
        this.data = data;
        this.pagamentos = new ArrayList<Pagamento>();
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

    public ArrayList<Pagamento> getPagamentos() {
        return this.pagamentos;
    }

    public void adicionaPagamento(Pagamento pagamento) {
        if (pagamento.getValor() >= this.valor) {
            this.valor = 0.0;
        } else {
            this.valor -= pagamento.getValor();
        }
        this.pagamentos.add(pagamento);
    }

    public String toString() {
        return "Fatura de " + this.cliente + " no valor de " + this.valor + " reais.";
    }
}
