package Principal;
 
import javax.swing.JOptionPane;
 
public class Main {
 
static final int TAM = 100;
static String[] beneficiarios = new String[TAM];
static double[] valores = new 
double[TAM];
static int[] anos = new int[TAM];
 
public static void main (String[] args) {
 
int opcao = 0;
 
        do {
            String input = JOptionPane.showInputDialog(
                "=== CONTAS A PAGAR ===\n\n" +
                "1. Cadastrar Despesa\n" +
                "2. Listar Despesas\n" +
                "3. Sair\n\n" +
                "Digite a opção desejada:"
            );
 
            if (input == null) {
                break;
            }
 
            opcao = Integer.parseInt(input);
 
            switch (opcao) {
                case 1:
                    JOptionPane.showMessageDialog(null, "Módulo de cadastro em construção.");
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
}
