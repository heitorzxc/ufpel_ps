package src.Instrucoes;

import src.Maquina.*;
import src.Registradores.*;


/*******
 * SHIFTL *
 *******/
public class SHIFTL {

    public static void executar(String enderecoBinario, Endereco endereco, BancoRegistradores registradores, Memoria memoria){

        Registrador regA = registradores.getRegistrador("A");
        
        if(enderecoBinario.equals("00000100")){         
          System.out.println("A <- (A) << 2");
          System.out.println("Realiza o deslocamento a esquerda de n bits .");

          System.out.println("Valor do Registrador A = " +regA.getNumeroInteiro());

          regA.setNumeroInteiro(regA.getNumeroInteiro() << 2);
          System.out.println("Valor deslocado a esquerda = "+regA.getNumeroInteiro()+"\n");

        }    
    }
}