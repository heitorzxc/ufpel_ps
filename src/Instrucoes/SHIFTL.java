package src.Instrucoes;

import src.Memoria.Endereco;
import src.Memoria.Memoria;
import src.Registradores.*;

/*******
 * SHIFTL *
 *******/
public class SHIFTL extends Instrucao {
  public SHIFTL() {
    super("SHIFTL", "A4", 2);
  }

  public void executar(Endereco instrucao, BancoRegistradores registradores, Memoria memoria) throws Exception {
    String[] operandos = instrucao.getOperandos();
    Integer valorReg = registradores.getValor(operandos[0]);
    int n = Integer.parseInt(operandos[1]);

    registradores.setValor(operandos[0], valorReg << n);
  }
}
