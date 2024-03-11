
import java.io.File;

// import java.io.File;

//montador toda vez que encontra macro x troca x por {a, b, c, d, e, ...}
//então no final da montagem temos um assembly totalmente expandido
//então joga pro ligador esse assembly

// Tabelinha da aula:
// label (#0) | operação | operando (#1)
// <vazio>    | operação | operando (#2) | operando (#3)
// label (#4) | operação | operando (#5) | imediato (@32)

public class Ligador {

  private File saida;

  Ligador() {
  }

  public void executar(String entrada) {
    Object saida = step2(step1(entrada));

    // return saida;
  }

  public String step1(String entrada) {
    File saida;

    return "saida";
  }

  public String step2(String entrada) {
    File saida;

    return "";
  }

}