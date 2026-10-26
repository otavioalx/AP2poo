package ap2;

/**
 * Subclasse que representa uma Conta Corrente bancária.
 * 
 * Conceito de Herança:
 * - Utiliza a palavra-chave 'extends Conta', estabelecendo a relação "é um tipo de" (ContaCorrente É UMA Conta).
 * - Herda todos os atributos e métodos da superclasse Conta sem precisar repeti-los.
 * 
 * Especialização:
 * - A Conta Corrente adiciona o recurso do "cheque especial" através do atributo 'limite',
 *   permitindo ao correntista realizar saques mesmo com saldo insuficiente, até o teto do limite.
 */
public class ContaCorrente extends Conta {

    // =========================================================================
    // ATRIBUTOS ESPECÍFICOS DA SUBCLASSE
    // =========================================================================

    /**
     * Limite de crédito adicional concedido pelo banco (cheque especial).
     * Visibilidade: 'private' (-) -> Encapsulado, garantindo integridade dos dados.
     */
    private double limite;

    // =========================================================================
    // CONSTRUTOR
    // =========================================================================

    /**
     * Construtor da ContaCorrente.
     * 
     * @param numero  Número identificador da conta.
     * @param titular Nome do correntista.
     * @param limite  Valor do limite de cheque especial disponível (ex: 500.0).
     */
    public ContaCorrente(int numero, String titular, double limite) {
        // 'super(numero, titular)' invoca o construtor da superclasse 'Conta',
        // repassando os parâmetros fundamentais para inicializar numero, titular e saldo.
        super(numero, titular);

        // Atribui o limite específico da ContaCorrente
        this.limite = limite;
    }

    // =========================================================================
    // MÉTODOS ESPECÍFICOS DA SUBCLASSE
    // =========================================================================

    /**
     * Realiza um saque especial utilizando o saldo em conta e o limite de cheque especial.
     * Regra de negócio:
     * - Permite sacar até 'saldo + limite'.
     * - O saldo pode ficar NEGATIVO, desde que a parte negativa não ultrapasse o limite disponível.
     * - Validação: 'valor > 0' E 'valor <= (saldo + limite)'.
     * - Se a condição for atendida: subtrai o valor de 'saldo' e retorna true.
     * - Se o valor ultrapassar o disponível (saldo + limite): não altera o saldo e retorna false.
     * 
     * Nota:
     * - O método padrão 'sacar(valor)' herdado de Conta só permite sacar até o saldo positivo.
     * - Este método 'sacarComLimite' é exclusivo da ContaCorrente para permitir o uso do cheque especial.
     * 
     * @param valor Quantia em dinheiro a ser sacada.
     * @return boolean 'true' se o saque foi aprovado, ou 'false' se excedeu o limite ou foi inválido.
     */
    public boolean sacarComLimite(double valor) {
        // Validação: valor precisa ser positivo e não pode exceder o montante total (saldo + limite)
        if (valor > 0 && valor <= (this.saldo + this.limite)) {
            this.saldo -= valor; // Subtrai o valor (o saldo pode se tornar negativo)
            return true;         // Saque com limite realizado com sucesso
        }
        return false;            // Saque negado: valor solicitado ultrapassa a soma de saldo + limite
    }

    /**
     * Retorna o valor do limite de cheque especial da conta corrente.
     * @return double limite disponível.
     */
    public double getLimite() {
        return this.limite;
    }
}
