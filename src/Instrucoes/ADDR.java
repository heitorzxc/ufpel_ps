package src.Instrucoes;

import src.Memoria.Endereco;
import src.Memoria.Memoria;
import src.Registradores.BancoRegistradores;

/*******
 * ADD *
 *******/
public class ADDR extends Instrucao {
  public ADDR() {
    super("ADDR", "90", 2);
  }

  public void executar(Endereco instrucao, BancoRegistradores registradores, Memoria memoria) throws Exception {
    String[] operandos = instrucao.getOperandos();

    Integer reg1 = registradores.getValor(operandos[0]);
    Integer reg2 = registradores.getValor(operandos[1]);

    registradores.setValor(operandos[1], reg1 + reg2);
  }
}
