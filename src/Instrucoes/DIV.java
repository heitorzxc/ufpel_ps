package src.Instrucoes;

import src.Memoria.Endereco;
import src.Memoria.Memoria;
import src.Registradores.BancoRegistradores;
import src.Utils.Conversao;

/*******
 * ADD *
 *******/
public class DIV extends Instrucao {
  public DIV() {
    super("DIV", "24", 1);
  }

  public void executar(Endereco instrucao, BancoRegistradores registradores, Memoria memoria) throws Exception {
    String nixbpe = instrucao.getNIXBPE();
    Integer valorReg = registradores.getValor("A");

    Integer valorMem = 0;

    Integer enderecoDestino = Conversao.StrNumBinC2(instrucao.getEndereco());

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

    registradores.setValor("A", valorReg / valorMem);
  }
}
