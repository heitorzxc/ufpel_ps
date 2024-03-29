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
    super("DIV", "24", 1, true);
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
        enderecoDestino = Conversao.StrNumBinC2(enderecoMemoria.getInstrucaoBinario());
      }

      valorMem = Conversao.StrNumBinC2(memoria.getValor(enderecoDestino).getInstrucaoBinario());
    }

    if (valorMem == 0) {
      System.err.println("Não existe divisão por 0 infelizmente");
      return;
    }

    registradores.setValor("A", valorReg / valorMem);
  }
}
