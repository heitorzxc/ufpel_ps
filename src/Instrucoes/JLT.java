package src.Instrucoes;

import src.Memoria.Endereco;
import src.Memoria.Memoria;
import src.Registradores.BancoRegistradores;
import src.Utils.Conversao;

public class JLT extends Instrucao {
    public JLT() {
        super("JLT", "38", 1, true);
    }

    public void executar(Endereco instrucao, BancoRegistradores registradores, Memoria memoria) throws Exception {
        String nixbpe = instrucao.getNIXBPE();

        if (registradores.getValor("SW") < 0) {
            Integer enderecoDestino = Conversao.StrNumBinC2(instrucao.getEndereco());

            if (nixbpe.startsWith("11")) { // Direto
                enderecoDestino = calculaEnderecoDireto(enderecoDestino, nixbpe, registradores);
            }

            if (nixbpe.startsWith("10")) { // INDIRETO
                Endereco enderecoMemoria = memoria.getValor(enderecoDestino);
                enderecoDestino = Conversao.StrNumBinC2(enderecoMemoria.getEndereco());
            }

            registradores.setValor("PC", enderecoDestino);
        } else {
            registradores.setValor("PC", registradores.getValor("PC") + 1);
        }
    }
}
