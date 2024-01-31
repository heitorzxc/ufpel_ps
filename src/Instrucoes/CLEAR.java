package src.Instrucoes;

import src.Maquina.Endereco;
import src.Maquina.Memoria;
import src.Registradores.BancoRegistradores;
import src.Registradores.Registrador;
import src.Interface.Controller;

/*******
 * ADD *
 *******/
public class CLEAR {
    public static void executar(String nixbpq, Endereco endereco, BancoRegistradores registradores, Memoria memoria, Controller controller){
        Registrador regA = registradores.getRegistrador("A");
        
        controller.handleTERMINAL("Essa instrucao CLEAR torna um registrador 0.");
        controller.handleTERMINAL("O acumulador será zerado.");
        controller.handleTERMINAL("O acumulador é = " +regA.getNumeroInteiro());
        regA.setNumeroInteiro(0);
        controller.handleTERMINAL("O valor no acumulador (Registrador A) = "+regA.getNumeroInteiro()+"\n");
        controller.handleTERMINAL("--------");
    }
}
