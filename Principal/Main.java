package Principal;
 
import javax.swing.JOptionPane;
 import java.time.LocalDate;

public class Main {
 
 static final int TAM = 100;
 static String[] beneficiarios = new String[TAM];
 static String[] documentos = new String[TAM];
 static String[] descricoes = new String[TAM];
 static double[] valores = new 
 double[TAM];
 static LocalDate[] vencimentos = new LocalDate[TAM];
 static int quantidade = 0;
 static double[] saldoDevedor = new double[TAM];
 
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
 static int localizarConta(String documento) {
   for(int i= 0; i < quantidade; i++){
    if(documentos[i].equals(documento)){
     return i;
        }
     }
 return -1;
 }
 static int localizarConta(String documento) {
   for(int i= 0; i < quantidade; i++){
    if(documentos[i].equals(documento)){
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
     if(posicao == -1) {
      JOptionPane.showMessageDialog(null,"Conta não encontrada");
       return;
     }
         JOptionPane.showMessageDialog(null, 
        "Documentos: " + documentos[posicao] +
        "\nDescrição: " + descricoes[posicao] +
        "\nBeneficiários: " + beneficiarios[posicao] +
        "\nValor: " + valores[posicao] +
        "\nVencimento: "  + vencimentos[posicao] 
       );
   int resposta = JOptionPane.showConfirmDialog(null,"Deseja realmente excluir esta conta?","Confirmação",JOptionPane.YES_NO_CANCEL_OPTION);
    if(resposta != JOptionPane.YES_OPTION){
     return;
    }
     for (int i = posicao;i < quantidade -1;i++){
      documentos[i] = documentos[i + 1];
      descricoes[i] = descricoes[i + 1];
      beneficiarios[i] = beneficiarios[i + 1];
      valores[i] = valores[i + 1];
      vencimentos[i] = vencimentos[i + 1];
      saldoDevedor[i] = saldoDevedor[i + 1];
}
      quantidade--;
  JOptionPane.showMessageDialog(null,"Conta excluída com sucesso");
}
