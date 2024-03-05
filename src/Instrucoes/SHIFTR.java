package src.Instrucoes;

import src.Memoria.Endereco;
import src.Memoria.Memoria;
import src.Registradores.*;

/*******
 * SHIFTR *
 *******/
public class SHIFTR extends Instrucao {
  public SHIFTR() {
    super("SHIFTR", "A8", 0);
  }

  public void executar(Endereco instrucao, BancoRegistradores registradores, Memoria memoria) {

    // Registrador regA = registradores.getRegistrador("A");

    // if (enderecoBinario.equals("00000100")) {
    // System.out.println("A <- (A) >>2");
    // System.out.println("Realiza o deslocamento a direita de n bits .");

    // System.out.println("Valor do Registrador A = " + regA.getNumeroInteiro());

    // regA.setNumeroInteiro(regA.getNumeroInteiro() >> 2);
    // System.out.println("Valor deslocado a direita = " + regA.getNumeroInteiro() +
    // "\n");

    // }
  }
}
