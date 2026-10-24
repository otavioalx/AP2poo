package ap2;

public class Conta {
    private int numero;
    private String titular;
    protected double saldo;

    public Conta(int numero, String titular) {
        this.numero = numero;
        this.titular = titular;
        this.saldo = 0.0;
    }

    public void depositar(double valor) {
        if (valor > 0) {
            this.saldo += valor;
        } else {
            System.out.println("Aviso: O valor do depósito deve ser maior que zero.");
        }
    }

    public boolean sacar(double valor) {
        if (valor > 0 && valor <= this.saldo) {
            this.saldo -= valor;
            return true;
        }
        return false;
    }

    public int getNumero() {
        return this.numero;
    }

    public String getTitular() {
        return this.titular;
    }

    public double getSaldo() {
        return this.saldo;
    }

    public void exibir() {
        System.out.printf("Conta: %d | Titular: %-10s | Saldo: R$ %8.2f%n", this.numero, this.titular, this.saldo);
    }
}
