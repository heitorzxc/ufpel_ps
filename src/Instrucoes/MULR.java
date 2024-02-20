package src.Instrucoes;

import src.Maquina.Endereco;
import src.Maquina.Memoria;
import src.Registradores.BancoRegistradores;
import src.Registradores.Registrador;

/*******
 * ADD *
 *******/
public class MULR implements InstrucaoExecutavel {
    public   void executar(Instrucao instrucao, Endereco endereco, BancoRegistradores registradores, Memoria memoria){
        Registrador regA = registradores.getRegistrador("A");
        Registrador regS = registradores.getRegistrador("S");

        String enderecoBinario = instrucao.getEnderecoBinario();
        
        if(enderecoBinario.equals("00000100")){
          System.out.println("A <- (A) * (S)");
          System.out.println("O registrador S será multiplicado com o Registrador A, o acumulador.");
          System.out.println("O registrador S tem valor = " +regS.getNumeroInteiro());
          System.out.println("O registrador A tem valor = " +regA.getNumeroInteiro());
          regA.setNumeroInteiro(regA.getNumeroInteiro()*regS.getNumeroInteiro());
          System.out.println("O valor no Registrador A = "+regA.getNumeroInteiro()+"\n");

        }    
    }
}
