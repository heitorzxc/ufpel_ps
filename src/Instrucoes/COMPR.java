package src.Instrucoes;

import src.Memoria.Endereco;
import src.Memoria.Memoria;
import src.Registradores.BancoRegistradores;
import src.Utils.Conversao;

public class COMPR extends Instrucao {
    public COMPR() {
        super("COMPR", "A0", 1, false);
    }

    public void executar(Endereco instrucao, BancoRegistradores registradores, Memoria memoria) throws Exception {
      String[] operandos = instrucao.getOperandos();
  
      Integer reg1 = registradores.getValor(operandos[0]);
      Integer reg2 = registradores.getValor(operandos[1]);

      if (reg1 < reg2) {
        registradores.setValor("SW", -1);
      } else if (reg1.equals(reg2)) {
          registradores.setValor("SW", 0); 
      } else {
          registradores.setValor("SW", 1); 
      }
  
    }
}
