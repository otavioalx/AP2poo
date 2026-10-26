package ap2;

/**
 * Subclasse que representa uma Conta Poupança bancária.
 * 
 * Conceito de Herança:
 * - Utiliza a palavra-chave 'extends Conta', estabelecendo a relação "é um tipo de" (ContaPoupanca É UMA Conta).
 * - Herda automaticamente todos os atributos (numero, titular, saldo) e métodos (depositar, sacar, exibir, etc.) da superclasse Conta.
 * 
 * Regra de Ouro da Herança:
 * - A subclasse NÃO deve redeclarar atributos que já existem na classe mãe (como numero, titular ou saldo).
 * - Na subclasse só declaramos o que ela possui a mais: no caso, 'taxaRendimento'.
 */
public class ContaPoupanca extends Conta {

    // =========================================================================
    // ATRIBUTOS ESPECÍFICOS DA SUBCLASSE
    // =========================================================================

    /**
     * Taxa percentual aplicada para render o saldo (exemplo: 0.05 representa 5%).
     * Visibilidade: 'private' (-) -> Encapsulado, acessível apenas dentro desta classe.
     */
    private double taxaRendimento;

    // =========================================================================
    // CONSTRUTOR
    // =========================================================================

    /**
     * Construtor da ContaPoupanca.
     * 
     * @param numero          Número da conta a ser criada.
     * @param titular         Nome do titular da conta.
     * @param taxaRendimento  Taxa percentual de rendimento mensal/periódico (ex: 0.05 para 5%).
     */
    public ContaPoupanca(int numero, String titular, double taxaRendimento) {
        // 'super(numero, titular)' invoca o construtor da superclasse 'Conta'.
        // É OBRIGATÓRIO ser a PRIMEIRA instrução dentro do construtor da subclasse em Java,
        // garantindo que a base do objeto (numero, titular e saldo = 0) seja inicializada corretamente.
        super(numero, titular);

        // Atribui o atributo específico da ContaPoupanca
        this.taxaRendimento = taxaRendimento;
    }

    // =========================================================================
    // MÉTODOS ESPECÍFICOS DA SUBCLASSE
    // =========================================================================

    /**
     * Aplica o rendimento sobre o saldo atual da conta poupança.
     * Regra de negócio:
     * - Aumenta o saldo em: 'saldo * taxaRendimento'.
     * - Exemplo prático: com saldo de R$ 100.00 e taxa de 0.05 (5%), o saldo passa para R$ 105.00.
     * 
     * Nota sobre Encapsulamento:
     * - Conseguimos acessar e modificar 'this.saldo' diretamente aqui porque na classe Conta
     *   o saldo foi definido como 'protected' (#). Se fosse 'private', esta linha não compilaria.
     */
    public void aplicarRendimento() {
        this.saldo += this.saldo * this.taxaRendimento;
    }

    /**
     * Retorna o percentual da taxa de rendimento cadastrada para a poupança.
     * @return double valor da taxa (ex: 0.05).
     */
    public double getTaxaRendimento() {
        return this.taxaRendimento;
    }
}
