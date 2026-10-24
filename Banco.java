package ap2;

import java.util.ArrayList;

public class Banco {
    private String nome;
    private ArrayList<Conta> contas;

    public Banco(String nome) {
        this.nome = nome;
        this.contas = new ArrayList<>();
    }

    public void adicionarConta(Conta conta) {
        this.contas.add(conta);
    }

    public Conta buscarPorNumero(int numero) {
        for (Conta conta : this.contas) {
            if (conta.getNumero() == numero) {
                return conta;
            }
        }
        return null;
    }

    public void listarContas() {
        for (Conta conta : this.contas) {
            conta.exibir();
        }
    }

    public double saldoTotal() {
        double total = 0.0;
        for (Conta conta : this.contas) {
            total += conta.getSaldo();
        }
        return total;
    }

    public String getNome() {
        return this.nome;
    }
}
