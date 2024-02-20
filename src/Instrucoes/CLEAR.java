package src.Instrucoes;

import src.Maquina.Endereco;
import src.Maquina.Memoria;
import src.Registradores.BancoRegistradores;
import src.Registradores.Registrador;

/*******
 * ADD *
 *******/
public class CLEAR implements InstrucaoExecutavel {
    public void executar(Instrucao instrucao, Endereco endereco, BancoRegistradores registradores, Memoria memoria){
        Registrador regA = registradores.getRegistrador("A");
        
        System.out.println("Essa instrucao CLEAR torna um registrador 0.");
        System.out.println("O acumulador será zerado.");
        System.out.println("O acumulador é = " +regA.getNumeroInteiro());
        regA.setNumeroInteiro(0);
        System.out.println("O valor no acumulador (Registrador A) = "+regA.getNumeroInteiro()+"\n");
        System.out.println("--------");
    }
}
