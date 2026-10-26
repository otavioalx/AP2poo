package ap2;

/**
 * Classe base (superclasse) que representa uma Conta bancária genérica.
 * 
 * Conceitos de POO aplicados nesta classe:
 * 1. Abstração e Modelagem: Define as características comuns (atributos) e 
 *    comportamentos (métodos) compartilhados por qualquer tipo de conta.
 * 2. Encapsulamento: Os dados internos são protegidos por modificadores de acesso
 *    (private e protected) e o acesso/modificação só ocorre através de métodos validados.
 */
public class Conta {

    // =========================================================================
    // ATRIBUTOS (Estado do Objeto)
    // =========================================================================

    /**
     * Identificador numérico exclusivo da conta bancária.
     * Visibilidade: 'private' (-) -> Só pode ser acessado diretamente dentro desta própria classe.
     * Imutabilidade: Não possui método 'setNumero', garantindo que o número nunca mude após ser criado.
     */
    private int numero;

    /**
     * Nome do titular/proprietário da conta bancária.
     * Visibilidade: 'private' (-) -> Acesso restrito à própria classe Conta.
     * Imutabilidade: Não possui método 'setTitular', fixando o titular definido na abertura da conta.
     */
    private String titular;

    /**
     * Saldo monetário disponível na conta.
     * Visibilidade: 'protected' (#) -> É a visibilidade protegida.
     * Por que 'protected' e não 'private'?
     * - As classes filhas (ContaPoupanca e ContaCorrente) herdam da classe Conta e precisam
     *   alterar o saldo diretamente (para render juros ou debitar além do saldo com cheque especial).
     * - Se fosse 'private', as filhas não conseguiriam mexer no saldo.
     * - Se fosse 'public', qualquer classe externa poderia alterar o saldo sem validação, quebrando o encapsulamento.
     */
    protected double saldo;

    // =========================================================================
    // CONSTRUTOR
    // =========================================================================

    /**
     * Construtor da classe Conta.
     * É executado no momento da instanciação com o operador 'new Conta(...)'.
     * 
     * @param numero  Número da nova conta a ser aberta.
     * @param titular Nome do titular da conta.
     */
    public Conta(int numero, String titular) {
        // 'this' referencia o atributo da instância atual, diferenciando-o do parâmetro recebido
        this.numero = numero;
        this.titular = titular;

        // Regra de negócio do enunciado: "O saldo começa em zero e só muda por depósito/saque"
        this.saldo = 0.0;
    }

    // =========================================================================
    // MÉTODOS DE OPERAÇÃO BANCÁRIA (Regras de Negócio)
    // =========================================================================

    /**
     * Realiza o depósito de um determinado valor no saldo da conta.
     * Regra de negócio:
     * - Soma 'valor' ao saldo SOMENTE SE 'valor' for estritamente maior que zero.
     * - Se o valor for zero ou negativo, a operação não é realizada e exibe um aviso.
     * 
     * @param valor Quantia em dinheiro a ser depositada.
     */
    public void depositar(double valor) {
        // Validação defensiva: impede depósitos nulos ou negativos
        if (valor > 0) {
            this.saldo += valor; // Incrementa o saldo com o valor depositado
        } else {
            System.out.println("Aviso: O valor do depósito deve ser maior que zero.");
        }
    }

    /**
     * Realiza o saque de um determinado valor do saldo da conta.
     * Regra de negócio:
     * - Só permite o saque se 'valor' for maior que zero E menor ou igual ao saldo atual.
     * - Se a condição for atendida: debita do saldo e retorna true (sucesso).
     * - Se não for atendida (saldo insuficiente ou valor inválido): não mexe no saldo e retorna false (falha).
     * 
     * @param valor Quantia em dinheiro a ser sacada.
     * @return boolean 'true' se o saque foi realizado com sucesso, ou 'false' se falhou.
     */
    public boolean sacar(double valor) {
        // Validação: valor deve ser positivo e não pode ultrapassar o saldo disponível
        if (valor > 0 && valor <= this.saldo) {
            this.saldo -= valor; // Subtrai o valor do saldo
            return true;         // Indica que a operação foi bem-sucedida
        }
        // Caso não tenha saldo suficiente ou o valor seja inválido, o saldo permanece intacto
        return false;            // Indica que o saque não foi permitido
    }

    // =========================================================================
    // MÉTODOS GETTERS (Acesso controlado aos atributos privados)
    // =========================================================================

    /**
     * Retorna o número da conta.
     * @return int número identificador.
     */
    public int getNumero() {
        return this.numero;
    }

    /**
     * Retorna o nome do titular da conta.
     * @return String nome do titular.
     */
    public String getTitular() {
        return this.titular;
    }

    /**
     * Retorna o saldo atual da conta.
     * @return double saldo monetário.
     */
    public double getSaldo() {
        return this.saldo;
    }

    // =========================================================================
    // MÉTODO DE EXIBIÇÃO
    // =========================================================================

    /**
     * Exibe no console os dados formatados da conta: número, titular e saldo.
     * Conforme solicitado no diagrama (+ exibir(): void).
     */
    public void exibir() {
        // Imprime os dados alinhados de forma legível e com formatação monetária (2 casas decimais)
        System.out.printf("Conta: %d | Titular: %-10s | Saldo: R$ %8.2f%n", this.numero, this.titular, this.saldo);
    }
}
