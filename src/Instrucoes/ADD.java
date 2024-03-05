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

    if (nixbpe.startsWith("11")) { // DIRETO
      enderecoDestino = calculaEnderecoDireto(enderecoDestino, nixbpe, registradores);
    }

    Integer valorMem = Conversao.stringToInt(memoria.getValor(enderecoDestino).getEndereco());

    registradores.setValor("A", valorReg + valorMem);
  }

}
