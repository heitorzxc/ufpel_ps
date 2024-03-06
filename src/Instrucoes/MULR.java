package src.Instrucoes;

import src.Memoria.Endereco;
import src.Memoria.Memoria;
import src.Registradores.BancoRegistradores;

/*******
 * ADD *
 *******/
public class MULR extends Instrucao {
  public MULR() {
    super("MULR", "98", 2);
  }

  public void executar(Endereco instrucao, BancoRegistradores registradores, Memoria memoria) throws Exception {
    String[] operandos = instrucao.getOperandos();

    Integer valorReg1 = registradores.getValor(operandos[0]);
    Integer valorReg2 = registradores.getValor(operandos[1]);

    registradores.setValor(operandos[1], valorReg1 * valorReg2);
  }
}
