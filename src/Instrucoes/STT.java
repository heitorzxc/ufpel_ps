package src.Instrucoes;

import src.Memoria.Endereco;
import src.Memoria.Memoria;
import src.Registradores.*;
import src.Utils.Conversao;

/*******
 * STT *
 *******/
public class STT extends Instrucao {
    public STT() {
        super("STT", "84", 2);
    }

    public void executar(Endereco instrucao, BancoRegistradores registradores, Memoria memoria) throws Exception {
        String nixbpe = instrucao.getNIXBPE();
        Integer valorRegT = registradores.getValor("T");

        Integer enderecoDestino = Conversao.StrNumBinC2(instrucao.getEndereco());

        if (nixbpe.startsWith("11")) { // Direto
            enderecoDestino = calculaEnderecoDireto(enderecoDestino, nixbpe, registradores);
        }

        if (nixbpe.startsWith("10")) { // INDIRETO
            Endereco enderecoMemoria = memoria.getValor(enderecoDestino);
            enderecoDestino = Conversao.StrNumBinC2(enderecoMemoria.getEndereco());
        }

        memoria.setValor(enderecoDestino, String.valueOf(valorRegT));
    }

}
