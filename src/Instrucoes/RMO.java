package src.Instrucoes;

import src.Maquina.Endereco;
import src.Maquina.Memoria;
import src.Registradores.BancoRegistradores;
import src.Registradores.Registrador;
import src.Interface.Controller;

/*******
 * ADD *
 *******/
public class RMO {
    public static void executar(String enderecoBinario, Endereco endereco, BancoRegistradores registradores, Memoria memoria, Controller controller){
        Registrador regA = registradores.getRegistrador("A");
        Registrador regB = registradores.getRegistrador("S");
        
        
        if (enderecoBinario.equals("00110000")){// vê quais são os dois registradores, nesse caso S e T
          controller.handleTERMINAL("B <- (A)");
          controller.handleTERMINAL("O valor no registrador B será o valor inteiro no Registrador T.");
          controller.handleTERMINAL("O registrador B tem valor = " +regB.getNumeroInteiro());
          controller.handleTERMINAL("O registrador A tem valor = " +regA.getNumeroInteiro());
          regB.setNumeroInteiro(regA.getNumeroInteiro());
      }
    }
}
