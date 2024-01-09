package src.Instrucoes;

import src.Maquina.Endereco;
import src.Maquina.Memoria;
import src.Registradores.BancoRegistradores;
import src.Registradores.Registrador;
import src.Interface.Controller;

/*******
 * ADD *
 *******/
public class SUBR {
    public static void executar(String nixbpq, Endereco endereco, BancoRegistradores registradores, Memoria memoria, Controller controller){
        Registrador regT = registradores.getRegistrador("T");
        Registrador regS = registradores.getRegistrador("S");
        
        
        if (nixbpq.equals("00110001")){// vê quais são os dois registradores, nesse caso S e T
          controller.handleTERMINAL("T <- (T) - (S)");
          controller.handleTERMINAL("O valor no registrador S será subtraido do valor no Registrador T.");
          controller.handleTERMINAL("O registrador S tem valor = " +regS.getNumeroInteiro());
          controller.handleTERMINAL("O registrador T tem valor = " +regT.getNumeroInteiro());
          regT.setNumeroInteiro(regT.getNumeroInteiro()-regS.getNumeroInteiro());
          controller.handleTERMINAL("O valor no Registrador T = "+regT.getNumeroInteiro()+"\n");
      }
    }
}
