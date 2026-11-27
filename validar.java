package validar;

import javax.swing.JOptionPane;


public class validar {
  public static boolean lerSimNao(String mensagem) {
    while (true) {
        String resposta = JOptionPane.showInputDialog(mensagem);

        
        if (resposta == null) {
            return false;
        }

        resposta = resposta.trim().toUpperCase();

        if (resposta.equals("S")) {
            return true;
        } else if (resposta.equals("N")) {
            return false;
        } else {
            JOptionPane.showMessageDialog(
                null,
                "Entrada inválida! Digite apenas S para Sim ou N para Não."
            );
        }
    }
}  
}
//use validar.lerSimNao pra chamar a função validar