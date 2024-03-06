package src.Instrucoes;

import src.Memoria.Endereco;
import src.Memoria.Memoria;
import src.Registradores.*;
import src.Utils.Conversao;

/*******
 * STS *
 *******/
public class STS extends Instrucao {

    public STS() {
        super("STS", "7C", 2);
    }

    public void executar(Endereco instrucao, BancoRegistradores registradores, Memoria memoria) throws Exception {
        String nixbpe = instrucao.getNIXBPE();
        Integer valorRegS = registradores.getValor("S");

        Integer enderecoDestino = Conversao.StrNumBinC2(instrucao.getEndereco());

        if (nixbpe.startsWith("11")) { // Direto
            enderecoDestino = calculaEnderecoDireto(enderecoDestino, nixbpe, registradores);
        }

        memoria.setValor(enderecoDestino, String.valueOf(valorRegS));
    }

}
