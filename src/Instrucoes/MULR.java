package src.Instrucoes;

import src.Maquina.Endereco;
import src.Maquina.Memoria;
import src.Registradores.BancoRegistradores;
import src.Registradores.Registrador;
import src.Interface.Controller;

/*******
 * ADD *
 *******/
public class MULR {
    public static void executar(String enderecoBinario, Endereco endereco, BancoRegistradores registradores, Memoria memoria, Controller controller){
        Registrador regA = registradores.getRegistrador("A");
        Registrador regS = registradores.getRegistrador("S");
        
        if(enderecoBinario.equals("00000100")){
          controller.handleTERMINAL("A <- (A) * (S)");
          controller.handleTERMINAL("O registrador S será multiplicado com o Registrador A, o acumulador.");
          controller.handleTERMINAL("O registrador S tem valor = " +regS.getNumeroInteiro());
          controller.handleTERMINAL("O registrador A tem valor = " +regA.getNumeroInteiro());
          regA.setNumeroInteiro(regA.getNumeroInteiro()*regS.getNumeroInteiro());
          controller.handleTERMINAL("O valor no Registrador A = "+regA.getNumeroInteiro()+"\n");

        }    
    }
}
