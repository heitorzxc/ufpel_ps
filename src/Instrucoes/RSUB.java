package src.Instrucoes;

import src.Memoria.Endereco;
import src.Memoria.Memoria;
import src.Registradores.*;

/*******
 * RSUB *
 *******/
public class RSUB extends Instrucao {
    public RSUB() {
        super("RSUB", "AC", 0);
    }

    public void executar(Endereco instrucao, BancoRegistradores registradores, Memoria memoria) {
        // Registrador regPC = registradores.getRegistrador("PC");
        // Registrador regL = registradores.getRegistrador("L");

        // System.out.println("Instrução RSUB: Registrador PC recebe o valor armazenado
        // no registrador L");
        // System.out.println("Valor armazenado em L:" + regL.getNumeroInteiro());

        // regPC.setNumeroInteiro(regL.getNumeroInteiro());
    }
}
