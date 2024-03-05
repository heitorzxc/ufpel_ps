package src.Instrucoes;

import src.Exceptions.RegisterIdenfierError;
import src.Memoria.Endereco;
import src.Memoria.Memoria;
import src.Registradores.BancoRegistradores;

public abstract class Instrucao {
  private String nome = "";
  private String opcode = "";
  private Integer numeroDeOperandos = 0;

  Instrucao(String nome, String opcode, Integer numeroDeOperandos) {
    this.nome = nome;
    this.opcode = opcode;
    this.numeroDeOperandos = numeroDeOperandos;
  }

  public String getNome() {
    return this.nome;
  }

  public String getOpcode() {
    return this.opcode;
  }

  public Integer getNumeroDeOperandos() {
    return this.numeroDeOperandos;
  }

  public Integer calculaEnderecoDireto(Integer end, String nix, BancoRegistradores registradores)
      throws RegisterIdenfierError {
    Integer endereco = end;

    // 110010
    if (nix.charAt(4) == '1') {
      return registradores.getValor("PC") + endereco;
    }
    // 110100
    if (nix.charAt(3) == '1') {
      return registradores.getValor("B") + endereco;
    }

    // 111XXX
    if (nix.charAt(2) == '1') {
      return registradores.getValor("X") + endereco;
    }

    return endereco;
  }

  public abstract void executar(Endereco instrucao, BancoRegistradores registradores, Memoria memoria) throws Exception;
  // 🔥F🔥I 🔥R🔥E🔥!🔥!🔥
}
