package src.Instrucoes;

import src.Maquina.Endereco;
import src.Maquina.Memoria;
import src.Registradores.BancoRegistradores;
import src.Registradores.Registrador;

/*******
 * ADD *
 *******/
public class RMO implements InstrucaoExecutavel {
    public  void executar(Instrucao instrucao, Endereco endereco, BancoRegistradores registradores, Memoria memoria){
        Registrador regA = registradores.getRegistrador("A");
        Registrador regB = registradores.getRegistrador("S");
        
        String enderecoBinario = instrucao.getEnderecoBinario();
        
        if (enderecoBinario.equals("00110000")){// vê quais são os dois registradores, nesse caso S e T
          System.out.println("B <- (A)");
          System.out.println("O valor no registrador B será o valor inteiro no Registrador T.");
          System.out.println("O registrador B tem valor = " +regB.getNumeroInteiro());
          System.out.println("O registrador A tem valor = " +regA.getNumeroInteiro());
          regB.setNumeroInteiro(regA.getNumeroInteiro());
      }
    }
}
