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

    public void executar(Endereco instrucao, BancoRegistradores registradores, Memoria memoria) throws Exception {
        Integer valorRegL = registradores.getValor("L");
        registradores.setValor("PC", valorRegL);
    }
}
