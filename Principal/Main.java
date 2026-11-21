package Principal;

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

    public static void main(String[] args) {

        int opcao = 0;

        do {
            String input = JOptionPane.showInputDialog(
                    "=== CONTAS A PAGAR ===\n\n"
                    + "1. Cadastrar Despesa\n"
                    + "2. Listar Despesas\n"
                    + "3. Sair\n\n"
                    + "Digite a opção desejada:"
            );

            if (input == null) {
                break;
            }
            if (validarInteiro(input) == false) {
                JOptionPane.showMessageDialog(null, "Opção inválida!");
                continue;
            }
            opcao = Integer.parseInt(input);

            switch (opcao) {
                case 1:
                    cadastrarConta();
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "Módulo de listagem em construção.");
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "Encerrando o sistema...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
            }

        } while (opcao != 3);
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
        }
        quantidade--;
        JOptionPane.showMessageDialog(null, "Conta excluída com sucesso");
    }

    static void cadastrarConta() {

        if (quantidade == TAM) {
            JOptionPane.showMessageDialog(null, "Limite de contas atingido!");
            return;
        }

        String documento = JOptionPane.showInputDialog("Digite o documento da conta:");
        if (documento == null) {
            return;
        }
        if (validarTexto(documento) == false) {
            JOptionPane.showMessageDialog(null, "Documento inválido!");
            return;
        }
        if (localizarConta(documento) != -1) {
            JOptionPane.showMessageDialog(null, "Documento já cadastrado!");
            return;
        }
        String descricao = JOptionPane.showInputDialog("Digite a descrição da conta:");
        if (descricao == null) {
            return;
        }
        if (validarTexto(descricao) == false) {
            JOptionPane.showMessageDialog(null, "Descrição inválida!");
            return;
        }
        String beneficiario = JOptionPane.showInputDialog("Digite o beneficiário:");
        if (beneficiario == null) {
            return;
        }
        if (validarTexto(beneficiario) == false) {
            JOptionPane.showMessageDialog(null, "Beneficiário inválido!");
            return;
        }
        String entradaValor = JOptionPane.showInputDialog("Digite o valor da conta:");

        if (entradaValor == null) {
            return;
        }
        if (validarValor(entradaValor) == false) {
            JOptionPane.showMessageDialog(null, "Valor inválido!");
            return;
        }
        double valor = Double.parseDouble(entradaValor);

        if (valor <= 0) {
            JOptionPane.showMessageDialog(null, "Valor inválido!");
            return;
        }
        String entradaData = JOptionPane.showInputDialog("Digite o vencimento (AAAA-MM-DD):");
        if (entradaData == null) {
            return;
        }
        if (validarData(entradaData) == false) {
            JOptionPane.showMessageDialog(null, "Data inválida!");
            return;
        }

        LocalDate vencimento = LocalDate.parse(entradaData);
        documentos[quantidade] = documento;
        descricoes[quantidade] = descricao;
        beneficiarios[quantidade] = beneficiario;
        valores[quantidade] = valor;
        vencimentos[quantidade] = vencimento;
        saldoDevedor[quantidade] = valor;
        quantidade++;
        JOptionPane.showMessageDialog(null, "Conta cadastrada com sucesso!");
    }

    static boolean validarValor(String entrada) {
        if (entrada == null) {
            return false;
        }

        if (entrada.equals("")) {
            return false;
        }
        int pontos = 0;
        int numeros = 0;
        for (int i = 0; i < entrada.length(); i++) {
            char c = entrada.charAt(i);
            if (c >= '0' && c <= '9') {
                numeros++;
            } else if (c == '.') {
                pontos++;
            } else {
                return false;
            }
        }
        if (pontos > 1 || numeros == 0) {
            return false;
        }
        double valor = Double.parseDouble(entrada);

        if (valor <= 0 || valor > Double.MAX_VALUE) {
            return false;
        }
        return true;
    }

    static boolean validarData(String entrada) {
        if (entrada == null) {
            return false;
        }

        if (entrada.equals("")) {
            return false;
        }
        if (entrada.length() != 10) {
            return false;
        }
        if (entrada.charAt(4) != '-' || entrada.charAt(7) != '-') {
            return false;
        }
        for (int i = 0; i < entrada.length(); i++) {
            char c = entrada.charAt(i);

            if (i != 4 && i != 7) {
                if (c < '0' || c > '9') {
                    return false;
                }
            }
        }
        int ano = Integer.parseInt(entrada.substring(0, 4));
        int mes = Integer.parseInt(entrada.substring(5, 7));
        int dia = Integer.parseInt(entrada.substring(8, 10));
        if (mes < 1 || mes > 12) {
            return false;
        }

        if (dia < 1 || dia > 31) {
            return false;
        }
        if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
            if (dia > 30) {
                return false;
            }
        }
        if (mes == 2) {
            if (ano % 400 == 0 || (ano % 4 == 0 && ano % 100 != 0)) {
                if (dia > 29) {
                    return false;
                }
            } else {
                if (dia > 28) {
                    return false;
                }
            }
        }
        return true;

    }

    static boolean validarInteiro(String entrada) {
        if (entrada == null) {
            return false;
        }

        if (entrada.equals("")) {
            return false;
        }
        for (int i = 0; i < entrada.length(); i++) {
            char c = entrada.charAt(i);

            if (c < '0' || c > '9') {
                return false;
            }
        }
        double numero = Double.parseDouble(entrada);

        if (numero > Integer.MAX_VALUE) {
            return false;
        }
        return true;
    }

    static boolean validarSN(String entrada) {
        if (entrada == null) {
            return false;
        }

        if (entrada.equals("")) {
            return false;
        }
        if (entrada.equals("S") || entrada.equals("s")
                || entrada.equals("N") || entrada.equals("n")) {
            return true;
        }
        return false;
    }

    static boolean validarTexto(String entrada) {
        if (entrada == null) {
            return false;
        }

        if (entrada.equals("")) {
            return false;
        }
        if (entrada.trim().equals("")) {
            return false;
        }
        return true;
    }
}
