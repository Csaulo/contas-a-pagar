
import javax.swing.JOptionPane;
import java.time.LocalDate;

public class Main {

    static final int TAM = 100;
    static String[] beneficiarios = new String[TAM];
    static String[] documentos = new String[TAM];
    static String[] descricoes = new String[TAM];
    static double[] valores = new double[TAM];
    static LocalDate[] vencimentos = new LocalDate[TAM];
    static int quantidade = 0;
    static double[] saldoDevedor = new double[TAM];
    static boolean[] pagas = new boolean[TAM];

    static void resumoFinanceiro() {

        int totalPagas = 0;
        int totalPendentes = 0;
        int totalVencidas = 0;
        double valorTotal = 0;
        double valorPago = 0;
        double valorVencido = 0;
        double valorPendente = 0;

        double totalEmAberto = 0;

        for (int i = 0; i < quantidade; i++) {
            valorTotal += valores[i];

            if (pagas[i]) {
                totalPagas++;
                valorPago += valores[i];

            } else if (vencimentos[i].isBefore(LocalDate.now())) {
                totalVencidas++;
                totalEmAberto += valores[i];
                valorVencido += valores[i];

            } else {
                totalPendentes++;
                totalEmAberto += valores[i];
                valorPendente += valores[i];
            }
        }
        String mensagem = "=== RESUMO FINANCEIRO ===\n\n"
                + "Total de despesas: " + quantidade
                + "\nDespesas pagas: " + totalPagas
                + "\nDespesas pendentes: " + totalPendentes
                + "\nDespesas vencidas: " + totalVencidas
                + "\nValor em aberto: R$ " + totalEmAberto
                + "\n\n=== VALORES FINANCEIROS ==="
                + "\nValor total cadastrado: R$ " + valorTotal
                + "\nValor já pago: R$ " + valorPago
                + "\nValor vencido: R$ " + valorVencido
                + "\nValor pendente: R$ " + valorPendente;
        JOptionPane.showMessageDialog(null, mensagem);
    }

    static void pesquisarConta() {

        if (quantidade == 0) {
            JOptionPane.showMessageDialog(null, "Nenhuma despesa cadastrada!");
            return;
        }

        String opcaoPesquisa = JOptionPane.showInputDialog(
                "=== PESQUISAR DESPESA ===\n\n"
                + "1. Pesquisar por documento\n"
                + "2. Pesquisar por beneficiário\n"
                + "Digite a opção:"
        );

        if (opcaoPesquisa == null) {
            return;
        }

        switch (opcaoPesquisa) {
            case "1":
                pesquisarPorDocumento();
                break;

            case "2":
                pesquisarPorBeneficiario();
                break;

            default:
                JOptionPane.showMessageDialog(null, "Opção inválida!");

        }
    }

    static void pesquisarPorDocumento() {

        String documento = JOptionPane.showInputDialog(
                "Digite o documento da despesa:"
        );

        if (documento == null) {
            return;
        }

        int posicao = localizarConta(documento);

        if (posicao == -1) {
            JOptionPane.showMessageDialog(null,
                    "Despesa não encontrada!");
            return;
        }

        mostrarDespesa(posicao);
    }

    static void pesquisarPorBeneficiario() {

        String nome = JOptionPane.showInputDialog(
                "Digite o nome do beneficiário:"
        );

        if (nome == null) {
            return;
        }

        String mensagem = "";
        int encontrados = 0;

        for (int i = 0; i < quantidade; i++) {

            if (beneficiarios[i].equalsIgnoreCase(nome.trim())) {

                String situacao = pagas[i] ? "Paga"
                        : (vencimentos[i].isBefore(LocalDate.now())
                        ? "Vencida" : "Pendente");

                mensagem += "\nDocumento: " + documentos[i]
                        + "\nBeneficiário: " + beneficiarios[i]
                        + "\nDescrição: " + descricoes[i]
                        + "\nValor: R$ " + valores[i]
                        + "\nVencimento: " + vencimentos[i]
                        + "\nSituação: " + situacao
                        + "\n-------------------------\n";

                encontrados++;
            }
        }

        if (encontrados == 0) {
            JOptionPane.showMessageDialog(null,
                    "Nenhuma despesa encontrada!");
        } else {
            JOptionPane.showMessageDialog(null,
                    "Despesas encontradas: " + encontrados
                    + "\n" + mensagem);
        }
    }

    static void mostrarDespesa(int posicao) {

        String situacao = pagas[posicao] ? "Paga"
                : (vencimentos[posicao].isBefore(LocalDate.now())
                ? "Vencida" : "Pendente");

        String mensagem = "Documento: " + documentos[posicao]
                + "\nBeneficiário: " + beneficiarios[posicao]
                + "\nDescrição: " + descricoes[posicao]
                + "\nValor: R$ " + valores[posicao]
                + "\nVencimento: " + vencimentos[posicao]
                + "\nSituação: " + situacao;

        JOptionPane.showMessageDialog(null, mensagem);
    }

    public static void main(String[] args) {

        int opcao = 0;

        do {
            String input = JOptionPane.showInputDialog(
                    "=== CONTAS A PAGAR ===\n\n"
                    + "1. Cadastrar Despesa\n"
                    + "2. Listar Despesas\n"
                    + "3. Sair\n\n"
                    + "4. Dar baixa em despesa\n"
                    + "5. Excluir despesa\n"
                    + "6. Resumo financeiro\n"
                    + "7. Pesquisar despesa\n"
                    + "Digite a opção desejada:"
            );

            if (input == null) {
                break;
            }

            opcao = Integer.parseInt(input);

            switch (opcao) {
                case 1:
                    cadastrarConta();
                    break;
                case 2:
                    listarContas();
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "Encerrando o sistema...");
                    break;
                case 4:
                    darBaixa();
                    break;
                case 5:
                    excluirConta();
                    break;
                case 6:
                    resumoFinanceiro();
                    break;
                case 7:
                    pesquisarConta();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
            }

        } while (opcao != 3);
    }

    static void darBaixa() {

        String documento = JOptionPane.showInputDialog(
                "Digite o documento da despesa que deseja pagar:"
        );

        if (documento == null) {
            return;
        }

        int posicao = localizarConta(documento);

        if (posicao == -1) {
            JOptionPane.showMessageDialog(null, "Despesa não encontrada!");
            return;
        }

        if (pagas[posicao]) {
            JOptionPane.showMessageDialog(null, "Essa despesa já está paga!");
            return;
        }

        pagas[posicao] = true;

        JOptionPane.showMessageDialog(null, "Despesa paga com sucesso!");
    }

    static void listarContas() {
        if (quantidade == 0) {
            JOptionPane.showMessageDialog(null, "Nenhuma despesa cadastrada!");
            return;
        }

        String mensagem = "";

        for (int i = 0; i < quantidade; i++) {
            mensagem += "Documento: " + documentos[i]
                    + "\nBeneficiário: " + beneficiarios[i]
                    + "\nDescrição: " + descricoes[i]
                    + "\nValor: R$ " + valores[i]
                    + "\nVencimento: " + vencimentos[i]
                    + "\nSituação: " + (pagas[i] ? "Paga"
                            : (vencimentos[i].isBefore(LocalDate.now()) ? "Vencida" : "Pendente"))
                    + "\n-------------------------\n";
        }

        JOptionPane.showMessageDialog(null, mensagem);
    }

    static int localizarConta(String documento) {
        for (int i = 0; i < quantidade; i++) {
            if (documentos[i].equals(documento)) {
                return i;
            }
        }
        return -1;
    }

    static void excluirConta() {
        String documento = JOptionPane.showInputDialog("Digite o documento que deseja excluir");
        if (documento == null) {
            return;
        }
        int posicao = localizarConta(documento);
        if (posicao == -1) {
            JOptionPane.showMessageDialog(null, "Conta não encontrada");
            return;
        }
        JOptionPane.showMessageDialog(null,
                "Documentos: " + documentos[posicao]
                + "\nDescrição: " + descricoes[posicao]
                + "\nBeneficiários: " + beneficiarios[posicao]
                + "\nValor: " + valores[posicao]
                + "\nVencimento: " + vencimentos[posicao]
        );
        int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir esta conta?", "Confirmação", JOptionPane.YES_NO_CANCEL_OPTION);
        if (resposta != JOptionPane.YES_OPTION) {
            return;
        }
        for (int i = posicao; i < quantidade - 1; i++) {
            documentos[i] = documentos[i + 1];
            descricoes[i] = descricoes[i + 1];
            beneficiarios[i] = beneficiarios[i + 1];
            valores[i] = valores[i + 1];
            vencimentos[i] = vencimentos[i + 1];
            saldoDevedor[i] = saldoDevedor[i + 1];
            pagas[i] = pagas[i + 1];
        }
        quantidade--;
        JOptionPane.showMessageDialog(null, "Conta excluída com sucesso");
    }

    static void cadastrarConta() {
        if (quantidade == TAM) {
            JOptionPane.showMessageDialog(null, "limite de contas atingido");
            return;
        }
        int posicao = quantidade;
        String documento = JOptionPane.showInputDialog("Digite o documento da despesa");
        if (documento == null) {
            return;
        }
        int posicaoExistente = localizarConta(documento);

        if (posicaoExistente != -1) {
            JOptionPane.showMessageDialog(null, "Documento já cadastrado!");
            return;
        }
        documentos[posicao] = documento;

        String beneficiario = JOptionPane.showInputDialog("Digite o nome do beneficiário");

        if (beneficiario == null) {
            return;
        }

        beneficiarios[posicao] = beneficiario;
        String descricao = JOptionPane.showInputDialog("Digite a descrição da despesa");

        if (descricao == null) {
            return;
        }

        descricoes[posicao] = descricao;
        String entrada = JOptionPane.showInputDialog("Digite o valor da despesa");

        if (entrada == null) {
            return;
        }

        double valor;

        try {
            valor = Double.parseDouble(entrada);

            if (valor <= 0) {
                JOptionPane.showMessageDialog(null, "Digite um valor maior que zero!");
                return;
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Valor inválido! Digite apenas números.");
            return;
        }

        valores[posicao] = valor;

        String data = JOptionPane.showInputDialog("Digite a data de vencimento (AAAA-MM-DD)");

        if (data == null) {
            return;
        }
        try {
            vencimentos[posicao] = LocalDate.parse(data);

        } catch (java.time.format.DateTimeParseException e) {
            JOptionPane.showMessageDialog(null,
                    "Data inválida! Use o formato AAAA-MM-DD.");
            return;
        }
        quantidade++;

        JOptionPane.showMessageDialog(null, "Despesa cadastrada com sucesso!");
    }
}
