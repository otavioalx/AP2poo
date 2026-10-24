package ap2;

public class Main {
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("      SISTEMA BANCÁRIO - DEMONSTRAÇÃO AP2         ");
        System.out.println("==================================================\n");

        // 1. Criar um Banco
        System.out.println("--- Passo 1: Criando o Banco ---");
        Banco banco = new Banco("Banco Central AP2");
        System.out.println("Banco criado com sucesso: " + banco.getNome());
        System.out.println();

        // 2. Criar uma conta de cada tipo
        System.out.println("--- Passo 2: Criando uma conta de cada tipo ---");
        Conta contaComum = new Conta(101, "Alice");
        ContaPoupanca contaPoupanca = new ContaPoupanca(202, "Bruno", 0.05); // 5% de rendimento
        ContaCorrente contaCorrente = new ContaCorrente(303, "Carla", 500.0); // R$ 500.00 de limite

        System.out.println("Contas criadas inicialmente (saldo inicial = R$ 0.00):");
        contaComum.exibir();
        contaPoupanca.exibir();
        contaCorrente.exibir();
        System.out.println();

        // 3. Adicionar as três ao banco
        System.out.println("--- Passo 3: Adicionando as três contas ao Banco ---");
        banco.adicionarConta(contaComum);
        banco.adicionarConta(contaPoupanca);
        banco.adicionarConta(contaCorrente);
        System.out.println("Todas as contas foram adicionadas ao banco.");
        System.out.println();

        // 4. Fazer alguns depósitos e saques
        System.out.println("--- Passo 4: Realizando depósitos e saques com sucesso ---");
        System.out.println("-> Depositando R$ 1000.00 na conta comum de " + contaComum.getTitular());
        contaComum.depositar(1000.0);

        System.out.println("-> Sacando R$ 300.00 da conta comum de " + contaComum.getTitular());
        boolean saqueComumSucesso = contaComum.sacar(300.0);
        System.out.println("Saque realizado? " + saqueComumSucesso + " | Saldo atual: R$ " + contaComum.getSaldo());

        System.out.println("-> Depositando R$ 500.00 na conta poupança de " + contaPoupanca.getTitular());
        contaPoupanca.depositar(500.0);

        System.out.println("-> Depositando R$ 200.00 na conta corrente de " + contaCorrente.getTitular());
        contaCorrente.depositar(200.0);
        System.out.println();

        // 5. Mostrar um saque que falha por saldo insuficiente (retorno false em ação)
        System.out.println("--- Passo 5: Demonstrando saque que falha por saldo insuficiente ---");
        System.out.println("Saldo atual da conta comum (" + contaComum.getTitular() + "): R$ " + contaComum.getSaldo());
        System.out.println("Tentando sacar R$ 1500.00 (valor maior que o saldo)...");
        boolean saqueFalhou = contaComum.sacar(1500.0);
        System.out.println("Saque aprovado? " + saqueFalhou + " (false esperado)");
        System.out.println("Saldo após tentativa com falha: R$ " + contaComum.getSaldo() + " (saldo intacto)");
        System.out.println();

        // 6. Aplicar rendimento na poupança e mostrar o saldo antes e depois
        System.out.println("--- Passo 6: Aplicando rendimento na Conta Poupança ---");
        System.out.println("Taxa de rendimento: " + (contaPoupanca.getTaxaRendimento() * 100) + "%");
        System.out.println("Saldo ANTES do rendimento: R$ " + contaPoupanca.getSaldo());
        contaPoupanca.aplicarRendimento();
        System.out.println("Saldo DEPOIS do rendimento: R$ " + contaPoupanca.getSaldo());
        System.out.println();

        // 7. Sacar usando o limite na conta corrente, deixando o saldo negativo dentro do limite
        System.out.println("--- Passo 7: Sacando usando o limite na Conta Corrente ---");
        System.out.println("Saldo atual: R$ " + contaCorrente.getSaldo() + " | Limite disponível: R$ " + contaCorrente.getLimite());
        System.out.println("Tentando sacar R$ 600.00 com limite (disponível: saldo + limite = R$ 700.00)...");
        boolean saqueLimiteSucesso = contaCorrente.sacarComLimite(600.0);
        System.out.println("Saque com limite aprovado? " + saqueLimiteSucesso);
        System.out.println("Saldo resultante: R$ " + contaCorrente.getSaldo() + " (saldo negativo dentro do limite)");

        System.out.println("\nTentando saque excedendo o limite (tentando sacar mais R$ 200.00):");
        boolean saqueLimiteExcedido = contaCorrente.sacarComLimite(200.0);
        System.out.println("Saque excedendo limite aprovado? " + saqueLimiteExcedido + " (false esperado)");
        System.out.println("Saldo final da conta corrente: R$ " + contaCorrente.getSaldo());
        System.out.println();

        // Demonstração adicional: buscarPorNumero no Banco
        System.out.println("--- Verificação Adicional: Busca de conta por número ---");
        Conta buscaExistente = banco.buscarPorNumero(202);
        System.out.println("Buscando conta nº 202: " + (buscaExistente != null ? buscaExistente.getTitular() : "Não encontrada"));
        Conta buscaInexistente = banco.buscarPorNumero(999);
        System.out.println("Buscando conta nº 999: " + (buscaInexistente != null ? buscaInexistente.getTitular() : "null (Correto, conta não existe)"));
        System.out.println();

        // 8. Listar todas as contas e imprimir o saldo total do banco
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
