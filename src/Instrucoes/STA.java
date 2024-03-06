package src.Instrucoes;

import src.Memoria.Endereco;
import src.Memoria.Memoria;
import src.Registradores.BancoRegistradores;
import src.Utils.Conversao;

/*******
 * ADD *
 *******/
public class STA extends Instrucao {
    public STA() {
        super("STA", "0C", 2);
    }

    public void executar(Endereco instrucao, BancoRegistradores registradores, Memoria memoria) throws Exception {

    }
}
