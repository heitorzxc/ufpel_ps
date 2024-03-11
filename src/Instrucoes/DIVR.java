package src.Instrucoes;

import src.Memoria.Endereco;
import src.Memoria.Memoria;
import src.Registradores.BancoRegistradores;

/*******
 * ADD *
 *******/
public class DIVR extends Instrucao {
  public DIVR() {
    super("DIVR", "9C", 2);
  }

  public void executar(Endereco instrucao, BancoRegistradores registradores, Memoria memoria) throws Exception {
    String[] operandos = instrucao.getOperandos();
    Integer reg1 = registradores.getValor(operandos[0]);
    Integer reg2 = registradores.getValor(operandos[1]);

    if (reg2 == 0) {
      System.err.println("Não existe divisão por 0 infelizmente");
      return;
    }

    registradores.setValor(operandos[0], reg1 / reg2);
  }
}
