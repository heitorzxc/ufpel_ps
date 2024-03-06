package src.Instrucoes;

import src.Memoria.Endereco;
import src.Memoria.Memoria;
import src.Registradores.*;
import src.Utils.Conversao;

/*******
 * OR *
 *******/
public class OR extends Instrucao {
  public OR() {
    super("OR", "44", 2);
  }

  public void executar(Endereco instrucao, BancoRegistradores registradores, Memoria memoria) throws Exception {
    String nixbpe = instrucao.getNIXBPE();
    Integer valorRegA = registradores.getValor("A");

    Integer enderecoDestino = Conversao.StrNumBinC2(instrucao.getEndereco());

    Integer valorMem = 0;

    if (nixbpe.startsWith("01")) { // IMEDIATO
      valorMem = enderecoDestino;
    } else {
      if (nixbpe.startsWith("11")) { // DIRETO
        enderecoDestino = calculaEnderecoDireto(enderecoDestino, nixbpe, registradores);
      }

      if (nixbpe.startsWith("10")) { // INDIRETO
        Endereco enderecoMemoria = memoria.getValor(enderecoDestino);
        enderecoDestino = Conversao.StrNumBinC2(enderecoMemoria.getEndereco());
      }

      valorMem = Conversao.stringToInt(memoria.getValor(enderecoDestino).getEndereco());
    }

    registradores.setValor("A", valorRegA | valorMem);
  }
}
