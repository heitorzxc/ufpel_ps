package src.Instrucoes;

import src.Memoria.Endereco;
import src.Memoria.Memoria;
import src.Registradores.*;
import src.Utils.Conversao;

/*******
 * LDA *
 *******/
public class LDA extends Instrucao {
    public LDA() {
        super("LDA", "0", 1);
    }

  
  public void executar(Endereco instrucao, BancoRegistradores registradores, Memoria memoria) throws Exception {
    String nixbpe = instrucao.getNIXBPE();
    Integer enderecoDestino = Conversao.StrNumBinC2(instrucao.getEndereco());

    if (nixbpe.startsWith("11")) { // DIRETO
        enderecoDestino = calculaEnderecoDireto(enderecoDestino, nixbpe, registradores);
    }

    Integer valorMem = Conversao.stringToInt(memoria.getValor(enderecoDestino).getEndereco());

    registradores.setValor("A", valorMem);
  }
}
