package src.Instrucoes;

import src.Memoria.Endereco;
import src.Memoria.Memoria;
import src.Registradores.*;
import src.Utils.Conversao;

/*******
 * STL *
 *******/
public class STL extends Instrucao {

    public STL() {
        super("STL", "14", 2);
    }

    public void executar(Endereco instrucao, BancoRegistradores registradores, Memoria memoria) throws Exception {
        String nixbpe = instrucao.getNIXBPE();
        Integer valorRegL = registradores.getValor("L");

        Integer enderecoDestino = Conversao.StrNumBinC2(instrucao.getEndereco());

        if (nixbpe.startsWith("11")) { // Direto
            enderecoDestino = calculaEnderecoDireto(enderecoDestino, nixbpe, registradores);
        }

        memoria.setValor(enderecoDestino, String.valueOf(valorRegL));
    }

}
