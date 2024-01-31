package src.Instrucoes;

import src.Maquina.*;
import src.Registradores.*;


/*******
 * RSUB *
 *******/
public class RSUB {
    public static void executar(String nixbpq, Endereco endereco, BancoRegistradores registradores, Memoria memoria){
        Registrador regPC = registradores.getRegistrador("PC");
        Registrador regL = registradores.getRegistrador("L");
        
        System.out.println("Instrução RSUB: Registrador PC recebe o valor armazenado no registrador L");
        System.out.println("Valor armazenado em L:"+regL.getNumeroInteiro());

        regPC.setNumeroInteiro(regL.getNumeroInteiro());
    }
}
