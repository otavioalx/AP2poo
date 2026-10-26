package ap2;

import java.util.ArrayList;

/**
 * Classe gerenciadora que representa a instituição bancária (Banco).
 * 
 * Conceito de Associação:
 * - O Banco estabelece uma relação de ASSOCIAÇÃO com Conta ("O Banco tem/guarda várias contas").
 * - Diferente da herança ("é um"), aqui temos a relação "tem um" / "tem muitos".
 * - No diagrama UML, é representado pela seta simples com multiplicidade 1 no Banco e * (muitos) em Conta.
 * 
 * Conceito de Polimorfismo e Coleções:
 * - A lista é do tipo genérico 'ArrayList<Conta>'.
 * - Como 'ContaPoupanca' e 'ContaCorrente' herdam de 'Conta', qualquer uma delas pode ser guardada
 *   dentro desta mesma lista perfeitamente. É o princípio da herança funcionando na prática.
 */
public class Banco {

    // =========================================================================
    // ATRIBUTOS
    // =========================================================================

    /**
     * Nome da instituição bancária (ex: "Banco Central AP2").
     * Visibilidade: 'private' (-) -> Encapsulado.
     */
    private String nome;

    /**
     * Lista dinâmica que armazena todas as contas cadastradas no banco.
     * Visibilidade: 'private' (-) -> Protegida contra acessos externos diretos.
     */
    private ArrayList<Conta> contas;

    // =========================================================================
    // CONSTRUTOR
    // =========================================================================

    /**
     * Construtor da classe Banco.
     * 
     * @param nome Nome do banco a ser criado.
     */
    public Banco(String nome) {
        this.nome = nome;
        // Instancia a lista dinâmica na memória, inicialmente vazia
        this.contas = new ArrayList<>();
    }

    // =========================================================================
    // MÉTODOS DE GERENCIAMENTO (Regras de Negócio do Banco)
    // =========================================================================

    /**
     * Adiciona uma nova conta (Conta comum, ContaPoupanca ou ContaCorrente) à lista do banco.
     * 
     * @param conta Objeto do tipo Conta a ser adicionado ao ArrayList.
     */
    public void adicionarConta(Conta conta) {
        // Utiliza o método 'add' do ArrayList para armazenar o objeto na coleção
        this.contas.add(conta);
    }

    /**
     * Percorre a lista de contas procurando pela conta que possui o número informado.
     * Regra de negócio:
     * - Retorna a referência da conta correspondente se encontrada.
     * - Retorna 'null' se a conta não existir na lista.
     * 
     * @param numero Número identificador da conta a ser buscada.
     * @return Conta O objeto Conta encontrado, ou 'null' se inexistente.
     */
    public Conta buscarPorNumero(int numero) {
        // Laço 'for-each': percorre cada elemento 'conta' presente na lista 'contas'
        for (Conta conta : this.contas) {
            // Compara o número da conta atual com o número recebido por parâmetro
            if (conta.getNumero() == numero) {
                return conta; // Encontrou a conta: retorna imediatamente o objeto
            }
        }
        // Se percorreu toda a lista e não encontrou nenhum número correspondente:
        return null;
    }

    /**
     * Percorre todas as contas da lista e invoca o método 'exibir()' de cada uma.
     * Permite visualizar no console os dados de todas as contas cadastradas.
     */
    public void listarContas() {
        // Itera sobre cada conta e chama sua rotina de exibição formatada
        for (Conta conta : this.contas) {
            conta.exibir();
        }
    }

    /**
     * Calcula e retorna a soma dos saldos de todas as contas presentes no banco.
     * 
     * @return double Valor total consolidado em custódia no banco.
     */
    public double saldoTotal() {
        double total = 0.0; // Variável acumuladora iniciada em zero

        // Percorre cada conta da lista somando seu saldo atual ao montante total
        for (Conta conta : this.contas) {
            total += conta.getSaldo(); // Obtém o saldo através do getter encapsulado
        }

        return total; // Retorna o saldo total somado
    }

    // =========================================================================
    // GETTER
    // =========================================================================

    /**
     * Retorna o nome da instituição bancária.
     * @return String nome do banco.
     */
    public String getNome() {
        return this.nome;
    }
}
