package src.Instrucoes;

import src.Memoria.Endereco;
import src.Memoria.Memoria;
import src.Registradores.BancoRegistradores;

/*******
 * ADD *
 *******/
public class RMO extends Instrucao {
  public RMO() {
    super("RMO", "AC", 2);
  }

  public void executar(Endereco instrucao, BancoRegistradores registradores, Memoria memoria) throws Exception {
    String[] operandos = instrucao.getOperandos();
    Integer valorRegOrigem = registradores.getValor(operandos[0]);

    registradores.setValor(operandos[1], valorRegOrigem);
  }
}
