package src.Instrucoes;

import src.Maquina.Endereco;
import src.Maquina.Memoria;
import src.Registradores.BancoRegistradores;
import src.Registradores.Registrador;
import src.Interface.Controller;

/*******
 * ADD *
 *******/
public class ADDR {
    public static void executar(String endercoBinario, Endereco endereco, BancoRegistradores registradores, Memoria memoria, Controller controller){
        Registrador regT = registradores.getRegistrador("T");
        Registrador regS = registradores.getRegistrador("S");
        
        
        if (endercoBinario.equals("00010011")){// vê quais são os dois registradores, nesse caso S e T
          controller.handleTERMINAL("S <- (S) + (T)");
          controller.handleTERMINAL("O valor no registrador S será somado com o valor no Registrador T.");
          controller.handleTERMINAL("O registrador S tem valor = " +regS.getNumeroInteiro());
          controller.handleTERMINAL("O registrador T tem valor = " +regT.getNumeroInteiro());
          regS.setNumeroInteiro(regS.getNumeroInteiro()+regT.getNumeroInteiro());
          controller.handleTERMINAL("O valor no Registrador S = "+regS.getNumeroInteiro()+"\n");
      }
    }
}
