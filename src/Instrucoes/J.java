package src.Instrucoes;

import src.Memoria.Endereco;
import src.Memoria.Memoria;
import src.Registradores.BancoRegistradores;
import src.Utils.Conversao;

public class J extends Instrucao {
  public J() {
    super("J", "3C", 1, true);
  }

  public void executar(Endereco instrucao, BancoRegistradores registradores, Memoria memoria) throws Exception {
    String nixbpe = instrucao.getNIXBPE();

    Integer enderecoDestino = Conversao.StrNumBinC2(instrucao.getEndereco());

    if (nixbpe.startsWith("11")) { // Direto
      enderecoDestino = calculaEnderecoDireto(enderecoDestino, nixbpe, registradores);
    }

    if (nixbpe.startsWith("10")) { // INDIRETO
      Endereco enderecoMemoria = memoria.getValor(enderecoDestino);
      enderecoDestino = Conversao.StrNumBinC2(enderecoMemoria.getInstrucaoBinario());
    }

    System.err.println("Endereco ->" + enderecoDestino);
    registradores.setValor("PC", enderecoDestino); // Imediato
  }
}
