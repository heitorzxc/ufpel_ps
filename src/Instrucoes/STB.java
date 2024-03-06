package src.Instrucoes;

import src.Memoria.Endereco;
import src.Memoria.Memoria;
import src.Registradores.*;
import src.Utils.Conversao;

/*******
 * STB *
 *******/
public class STB extends Instrucao {

    public STB() {
        super("STB", "78", 2);
    }

    public void executar(Endereco instrucao, BancoRegistradores registradores, Memoria memoria) throws Exception {
        String nixbpe = instrucao.getNIXBPE();
        Integer valorRegB = registradores.getValor("B");

        Integer enderecoDestino = Conversao.StrNumBinC2(instrucao.getEndereco());

        if (nixbpe.startsWith("11")) { // Direto
            enderecoDestino = calculaEnderecoDireto(enderecoDestino, nixbpe, registradores);
        }

        if (nixbpe.startsWith("10")) { // INDIRETO
            Endereco enderecoMemoria = memoria.getValor(enderecoDestino);
            enderecoDestino = Conversao.StrNumBinC2(enderecoMemoria.getEndereco());
        }

        memoria.setValor(enderecoDestino, String.valueOf(valorRegB));
    }
}
