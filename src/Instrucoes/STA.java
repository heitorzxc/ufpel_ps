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
        String nixbpe = instrucao.getNIXBPE();
        Integer valorRegA = registradores.getValor("A");

        Integer enderecoDestino = Conversao.StrNumBinC2(instrucao.getEndereco());

        if (nixbpe.startsWith("11")) { // Direto
            enderecoDestino = calculaEnderecoDireto(enderecoDestino, nixbpe, registradores);
        }

        memoria.setValor(enderecoDestino, String.valueOf(valorRegA));
    }
}
