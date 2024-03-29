package src.Instrucoes;

import src.Memoria.Endereco;
import src.Memoria.Memoria;
import src.Registradores.BancoRegistradores;
import src.Utils.Conversao;

public class JEQ extends Instrucao {
    public JEQ() {
        super("JEQ", "30", 3, true);
    }

    public void executar(Endereco instrucao, BancoRegistradores registradores, Memoria memoria) throws Exception {
        String nixbpe = instrucao.getNIXBPE();

        System.err.println(registradores.getValor("SW"));

        if (registradores.getValor("SW") == 0) {
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
