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
        System.out.println(enderecoMemoria.getEndereco());
        // enderecoDestino = enderecoMemoria.getEndereco();
      }

      System.out.println(memoria.getValor(enderecoDestino).getEndereco());
      //
      // valorMem = Conversao.stringToInt();
      valorMem = 0;
    }

    registradores.setValor("A", valorReg + valorMem);
  }

}
