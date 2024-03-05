package src.Instrucoes;

import src.Memoria.Endereco;
import src.Memoria.Memoria;
import src.Registradores.BancoRegistradores;

/*******
 * ADD *
 *******/
public class SUBR extends Instrucao {
  public SUBR() {
    super("SUBR", "94", 2);
  }

  public void executar(Endereco endereco, BancoRegistradores registradores, Memoria memoria) {
    // Registrador regT = registradores.getRegistrador("T");
    // Registrador regS = registradores.getRegistrador("S");

    // String enderecoBinario = instrucao.getEnderecoBinario();

    // if (enderecoBinario.equals("00110001")) {// vê quais são os dois
    // registradores, nesse caso S e T
    // System.out.println("T <- (T) - (S)");
    // System.out.println("O valor no registrador S será subtraido do valor no
    // Registrador T.");
    // System.out.println("O registrador S tem valor = " + regS.getNumeroInteiro());
    // System.out.println("O registrador T tem valor = " + regT.getNumeroInteiro());
    // regT.setNumeroInteiro(regT.getNumeroInteiro() - regS.getNumeroInteiro());
    // System.out.println("O valor no Registrador T = " + regT.getNumeroInteiro() +
    // "\n");
    // }
  }
}
