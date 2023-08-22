
public class Boleto {
    private int codigo;
    private String data;
    private double valor;
    private String tipo;

    public Boleto(int codigo, String data, double valor) {
        this.codigo = codigo;
        this.data = data;
        this.valor = valor;
        this.tipo = "BOLETO";
    }

    public int getCodigo() {
        return this.codigo;
    }

    public String getData() {
        return this.data;
    }

    public double getValor() {
        return this.valor;
    }

    public String getTipo() {
        return this.tipo;
    }

    public String toString() {
        return "Boleto: " + this.codigo + " - " + this.data + " - " + this.valor;
    }
}
