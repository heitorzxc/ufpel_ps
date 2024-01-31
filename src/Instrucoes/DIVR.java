package src.Instrucoes;

import src.Maquina.Endereco;
import src.Maquina.Memoria;
import src.Registradores.BancoRegistradores;
import src.Registradores.Registrador;
import src.Interface.Controller;
/*******
 * ADD *
 *******/
public class DIVR {
    public static void executar(String endercoBinario, Endereco endereco, BancoRegistradores registradores, Memoria memoria, Controller controller){
        Registrador regA = registradores.getRegistrador("A");
        Registrador regT = registradores.getRegistrador("T");
        
        
        if(endercoBinario.equals("00000101")){
          controller.handleTERMINAL("A <- (A) / (T)");
          controller.handleTERMINAL("O registrador A será divido com o valor no Registrador T.");
          controller.handleTERMINAL("O registrador T tem valor = " +regT.getNumeroInteiro());
          controller.handleTERMINAL("O registrador A tem valor = " +regA.getNumeroInteiro());
          regA.setNumeroInteiro(regA.getNumeroInteiro()/regT.getNumeroInteiro());
          controller.handleTERMINAL("O valor no acumulador (Registrador A) = "+regA.getNumeroInteiro()+"\n");

        }    
    }
}
