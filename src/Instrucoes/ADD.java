package src.Instrucoes;

import src.Memoria.Endereco;
import src.Memoria.Memoria;
import src.Registradores.BancoRegistradores;
import src.Utils.Conversao;

/*******
 * ADD *
 *******/
public class ADD extends Instrucao {
  public ADD() {
    super("ADD", "18", 1);
  }

  public void executar(Endereco instrucao, BancoRegistradores registradores, Memoria memoria) throws Exception {
    String nixbpe = instrucao.getNIXBPE();
    Integer valorReg = registradores.getValor("A");

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
        enderecoDestino = Conversao.StrNumBinC2(enderecoMemoria.getInstrucaoBinario());
      }

      valorMem = Conversao.StrNumBinC2(memoria.getValor(enderecoDestino).getInstrucaoBinario());
    }

    System.err.println("valormem" + valorMem);
    System.err.println("valorreg" + valorReg);

    registradores.setValor("A", valorReg + valorMem);
    System.err.println(registradores.getValor("A"));
  }

}
