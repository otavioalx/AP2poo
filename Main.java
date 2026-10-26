package ap2;

/**
 * Classe principal executável (Main) que demonstra o funcionamento completo do Sistema Bancário.
 * 
 * Objetivo:
 * - Comprovar que todas as regras de negócio, o encapsulamento, a herança e o gerenciamento via ArrayList
 *   estão operando perfeitamente, atendendo a todos os 8 passos obrigatórios da Parte 4 do enunciado.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("      SISTEMA BANCÁRIO - DEMONSTRAÇÃO AP2         ");
        System.out.println("==================================================\n");

        // =========================================================================
        // PASSO 1: Criar um Banco
        // =========================================================================
        // Instancia a classe gerenciadora que guardará e administrará as contas.
        System.out.println("--- Passo 1: Criando o Banco ---");
        Banco banco = new Banco("Banco Central AP2");
        System.out.println("Banco criado com sucesso: " + banco.getNome());
        System.out.println();

        // =========================================================================
        // PASSO 2: Criar uma conta de cada tipo
        // =========================================================================
        // Criamos 3 objetos distintos:
        // - contaComum: instância da classe base Conta.
        // - contaPoupanca: instância da subclasse ContaPoupanca (com taxa de rendimento de 5% = 0.05).
        // - contaCorrente: instância da subclasse ContaCorrente (com limite de cheque especial de R$ 500.00).
        System.out.println("--- Passo 2: Criando uma conta de cada tipo ---");
        Conta contaComum = new Conta(101, "Alice");
        ContaPoupanca contaPoupanca = new ContaPoupanca(202, "Bruno", 0.05); // 5% de rendimento
        ContaCorrente contaCorrente = new ContaCorrente(303, "Carla", 500.0); // R$ 500.00 de limite

        // Exibe o estado inicial de cada uma para comprovar que o saldo começa estritamente em zero
        System.out.println("Contas criadas inicialmente (saldo inicial = R$ 0.00):");
        contaComum.exibir();
        contaPoupanca.exibir();
        contaCorrente.exibir();
        System.out.println();

        // =========================================================================
        // PASSO 3: Adicionar as três contas ao Banco
        // =========================================================================
        // Como ContaPoupanca e ContaCorrente herdam de Conta ("é um tipo de Conta"),
        // elas cabem naturalmente no ArrayList<Conta> do banco (relação "é um" na prática).
        System.out.println("--- Passo 3: Adicionando as três contas ao Banco ---");
        banco.adicionarConta(contaComum);
        banco.adicionarConta(contaPoupanca);
        banco.adicionarConta(contaCorrente);
        System.out.println("Todas as contas foram adicionadas ao banco.");
        System.out.println();

        // =========================================================================
        // PASSO 4: Fazer alguns depósitos e saques com sucesso
        // =========================================================================
        // Realizamos movimentações financeiras válidas para demonstrar o fluxo positivo dos métodos.
        System.out.println("--- Passo 4: Realizando depósitos e saques com sucesso ---");
        
        // Depósito de R$ 1000.00 na conta comum
        System.out.println("-> Depositando R$ 1000.00 na conta comum de " + contaComum.getTitular());
        contaComum.depositar(1000.0);

        // Saque com sucesso de R$ 300.00 na conta comum (retornará true pois 300 <= 1000)
        System.out.println("-> Sacando R$ 300.00 da conta comum de " + contaComum.getTitular());
        boolean saqueComumSucesso = contaComum.sacar(300.0);
        System.out.println("Saque realizado? " + saqueComumSucesso + " | Saldo atual: R$ " + contaComum.getSaldo());

        // Depósito de R$ 500.00 na conta poupança
        System.out.println("-> Depositando R$ 500.00 na conta poupança de " + contaPoupanca.getTitular());
        contaPoupanca.depositar(500.0);

        // Depósito de R$ 200.00 na conta corrente
        System.out.println("-> Depositando R$ 200.00 na conta corrente de " + contaCorrente.getTitular());
        contaCorrente.depositar(200.0);
        System.out.println();

        // =========================================================================
        // PASSO 5: Mostrar um saque que falha por saldo insuficiente
        // =========================================================================
        // Prova do retorno 'false' em ação quando o valor do saque supera o saldo disponível.
        // A conta comum possui saldo de R$ 700.00, tentamos sacar R$ 1500.00.
        System.out.println("--- Passo 5: Demonstrando saque que falha por saldo insuficiente ---");
        System.out.println("Saldo atual da conta comum (" + contaComum.getTitular() + "): R$ " + contaComum.getSaldo());
        System.out.println("Tentando sacar R$ 1500.00 (valor maior que o saldo)...");
        boolean saqueFalhou = contaComum.sacar(1500.0);
        System.out.println("Saque aprovado? " + saqueFalhou + " (false esperado)");
        System.out.println("Saldo após tentativa com falha: R$ " + contaComum.getSaldo() + " (saldo intacto)");
        System.out.println();

        // =========================================================================
        // PASSO 6: Aplicar rendimento na poupança e mostrar saldo antes e depois
        // =========================================================================
        // Prova do cálculo: Saldo anterior (R$ 500.00) + Rendimento de 5% (R$ 25.00) = R$ 525.00.
        System.out.println("--- Passo 6: Aplicando rendimento na Conta Poupança ---");
        System.out.println("Taxa de rendimento: " + (contaPoupanca.getTaxaRendimento() * 100) + "%");
        System.out.println("Saldo ANTES do rendimento: R$ " + contaPoupanca.getSaldo());
        contaPoupanca.aplicarRendimento(); // Executa o cálculo da taxa
        System.out.println("Saldo DEPOIS do rendimento: R$ " + contaPoupanca.getSaldo());
        System.out.println();

        // =========================================================================
        // PASSO 7: Sacar usando o limite na conta corrente (saldo negativo)
        // =========================================================================
        // Carla possui Saldo = R$ 200.00 e Limite = R$ 500.00 (Total disponível = R$ 700.00).
        // Sacar R$ 600.00 é permitido via 'sacarComLimite' e deixará o saldo em -R$ 400.00 (dentro do limite).
        System.out.println("--- Passo 7: Sacando usando o limite na Conta Corrente ---");
        System.out.println("Saldo atual: R$ " + contaCorrente.getSaldo() + " | Limite disponível: R$ " + contaCorrente.getLimite());
        System.out.println("Tentando sacar R$ 600.00 com limite (disponível: saldo + limite = R$ 700.00)...");
        boolean saqueLimiteSucesso = contaCorrente.sacarComLimite(600.0);
        System.out.println("Saque com limite aprovado? " + saqueLimiteSucesso);
        System.out.println("Saldo resultante: R$ " + contaCorrente.getSaldo() + " (saldo negativo dentro do limite)");

        // Prova adicional: tentativa de saque que ultrapassa o cheque especial
        // Com saldo em -R$ 400.00 e limite de R$ 500.00, resta apenas R$ 100.00 disponível.
        // Tentar sacar R$ 200.00 deve falhar e retornar false.
        System.out.println("\nTentando saque excedendo o limite (tentando sacar mais R$ 200.00):");
        boolean saqueLimiteExcedido = contaCorrente.sacarComLimite(200.0);
        System.out.println("Saque excedendo limite aprovado? " + saqueLimiteExcedido + " (false esperado)");
        System.out.println("Saldo final da conta corrente: R$ " + contaCorrente.getSaldo());
        System.out.println();

        // =========================================================================
        // VERIFICAÇÃO ADICIONAL: Buscar por número no Banco
        // =========================================================================
        // Comprova o funcionamento do método 'buscarPorNumero(int numero)' do Banco.
        System.out.println("--- Verificação Adicional: Busca de conta por número ---");
        Conta buscaExistente = banco.buscarPorNumero(202);
        System.out.println("Buscando conta nº 202: " + (buscaExistente != null ? buscaExistente.getTitular() : "Não encontrada"));
        Conta buscaInexistente = banco.buscarPorNumero(999);
        System.out.println("Buscando conta nº 999: " + (buscaInexistente != null ? buscaInexistente.getTitular() : "null (Correto, conta não existe)"));
        System.out.println();

        // =========================================================================
        // PASSO 8: Listar todas as contas e imprimir o saldo total do banco
        // =========================================================================
        // 1. Invoca 'banco.listarContas()' que chama 'conta.exibir()' para cada conta da lista.
        // 2. Invoca 'banco.saldoTotal()' somando todos os saldos:
        //    Conta Alice: R$ 700.00 + Conta Bruno: R$ 525.00 + Conta Carla: -R$ 400.00 = R$ 825.00.
        System.out.println("--- Passo 8: Listando todas as contas e calculando saldo total ---");
        System.out.println("Lista de contas registradas no banco:");
        banco.listarContas();
        System.out.println("--------------------------------------------------");
        System.out.printf("Saldo total em custódia no banco: R$ %.2f%n", banco.saldoTotal());
        System.out.println("==================================================");
        System.out.println("     TODOS OS 8 PASSOS CONCLUÍDOS COM SUCESSO!    ");
        System.out.println("==================================================");
    }
}
