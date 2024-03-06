package src.Instrucoes;

import src.Memoria.Endereco;
import src.Memoria.Memoria;
import src.Registradores.BancoRegistradores;

/*******
 * ADD *
 *******/
public class CLEAR extends Instrucao {
    public CLEAR() {
        super("CLEAR", "B4", 1);
    }

    public void executar(Endereco instrucao, BancoRegistradores registradores, Memoria memoria) throws Exception {
        String[] operandos = instrucao.getOperandos();

        registradores.setValor(operandos[0], 0);
    }
}
