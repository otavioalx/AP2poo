package ap2;

public class ContaPoupanca extends Conta {
    private double taxaRendimento;

    public ContaPoupanca(int numero, String titular, double taxaRendimento) {
        super(numero, titular);
        this.taxaRendimento = taxaRendimento;
    }

    public void aplicarRendimento() {
        this.saldo += this.saldo * this.taxaRendimento;
    }

    public double getTaxaRendimento() {
        return this.taxaRendimento;
    }
}

