package src.Instrucoes;

import src.Exceptions.RegisterIdenfierError;
import src.Exceptions.ValueOutOfBoundError;
import src.Memoria.Endereco;
import src.Memoria.Memoria;
import src.Registradores.*;
import src.Utils.Conversao;

/*******
 * AND *
 *******/
public class AND extends Instrucao {
  public AND() {
    super("AND", "40", 1);
  }

  public void executar(Endereco instrucao, BancoRegistradores registradores, Memoria memoria)
      throws RegisterIdenfierError, ValueOutOfBoundError {
    Integer endereco = Conversao.StrNumBinC2(instrucao.getEndereco());

    Integer regA = registradores.getValor("A");

    Integer valor = Conversao.stringToInt(memoria.getValor(endereco).getEndereco());

    registradores.setValor("A", regA & valor);
  }
}
