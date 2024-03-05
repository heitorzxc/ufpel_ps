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

  public void executar(Endereco instrucao, BancoRegistradores registradores, Memoria memoria) {

  }
}
