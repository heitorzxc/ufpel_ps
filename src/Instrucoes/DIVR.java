package src.Instrucoes;

import src.Maquina.Endereco;
import src.Maquina.Memoria;
import src.Registradores.BancoRegistradores;
import src.Registradores.Registrador;

/*******
 * ADD *
 *******/
public class DIVR {
    public static void executar(String endercoBinario, Endereco endereco, BancoRegistradores registradores, Memoria memoria){
        Registrador regA = registradores.getRegistrador("A");
        Registrador regT = registradores.getRegistrador("T");
        
        
        if(endercoBinario.equals("00000101")){
          System.out.println("A <- (A) / (T)");
          System.out.println("O registrador A será divido com o valor no Registrador T.");
          System.out.println("O registrador T tem valor = " +regT.getNumeroInteiro());
          System.out.println("O registrador A tem valor = " +regA.getNumeroInteiro());
          regA.setNumeroInteiro(regA.getNumeroInteiro()/regT.getNumeroInteiro());
          System.out.println("O valor no acumulador (Registrador A) = "+regA.getNumeroInteiro()+"\n");

        }    
    }
}
