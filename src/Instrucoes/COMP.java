package src.Instrucoes;

import src.Memoria.Endereco;
import src.Memoria.Memoria;
import src.Registradores.BancoRegistradores;
import src.Utils.Conversao;

public class COMP extends Instrucao {
    public COMP() {
        super("COMP", "28", 1, true);
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
  
      if (valorReg < valorMem) {
        registradores.setValor("SW", -1); // Indica que A < memória
      } else if (valorReg.equals(valorMem)) {
          registradores.setValor("SW", 0); // Indica que A == memória
      } else {
          registradores.setValor("SW", 1); // Indica que A > memória
      }
    }
}
