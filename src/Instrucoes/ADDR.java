package src.Instrucoes;

import src.Maquina.Endereco;
import src.Maquina.Memoria;
import src.Registradores.BancoRegistradores;
import src.Registradores.Registrador;

/*******
 * ADD *
 *******/
public class ADDR implements InstrucaoExecutavel{
    public   void executar(Instrucao instrucao, Endereco endereco, BancoRegistradores registradores, Memoria memoria){
        Registrador regT = registradores.getRegistrador("T");
        Registrador regS = registradores.getRegistrador("S");
      
        String enderecoBinario = instrucao.getEnderecoBinario();
        
        if (enderecoBinario.equals("00010011")){// vê quais são os dois registradores, nesse caso S e T
          System.out.println("S <- (S) + (T)");
          System.out.println("O valor no registrador S será somado com o valor no Registrador T.");
          System.out.println("O registrador S tem valor = " +regS.getNumeroInteiro());
          System.out.println("O registrador T tem valor = " +regT.getNumeroInteiro());
          regS.setNumeroInteiro(regS.getNumeroInteiro()+regT.getNumeroInteiro());
          System.out.println("O valor no Registrador S = "+regS.getNumeroInteiro()+"\n");
      }
    }
}
