package src.Instrucoes;

import src.Maquina.Endereco;
import src.Maquina.Memoria;
import src.Registradores.BancoRegistradores;

public class Instrucoes {
  public Instrucoes(){

  }
  
  public void executaInstrucao(Instrucao instrucao, Endereco endereco, BancoRegistradores registradores, Memoria memoria){
    switch(instrucao.getOpcode()){
      case "0":
          LDA.executar(instrucao.getNixbpq(), endereco, registradores, memoria);
          break;
      case "68":
          LDB.executar(instrucao.getNixbpq(), endereco, registradores, memoria);
          break;
      case "8":
          LDL.executar(instrucao.getNixbpq(), endereco, registradores, memoria);
          break;
      case "6C":
          LDS.executar(instrucao.getNixbpq(), endereco, registradores, memoria);
          break;
      case "74":
          LDT.executar(instrucao.getNixbpq(), endereco, registradores, memoria);
          break;
      case "18":
          ADD.executar(instrucao.getNixbpq(), endereco, registradores, memoria);
          break;
      case "1C":
          SUB.executar(instrucao.getNixbpq(), endereco, registradores, memoria);
          break;
      case "24":
          DIV.executar(instrucao.getNixbpq(), endereco, registradores, memoria);
          break;
      case "20":
          MUL.executar(instrucao.getNixbpq(), endereco, registradores, memoria);
          break;
      case "98":
          MULR.executar(instrucao.getEnderecoBinario(), endereco, registradores, memoria);
          break;
      case "9C":
          DIVR.executar(instrucao.getEnderecoBinario(), endereco, registradores, memoria);
          break;
      case "90":
          ADDR.executar(instrucao.getEnderecoBinario(), endereco, registradores, memoria);
          break;
      case "94":
          SUBR.executar(instrucao.getEnderecoBinario(), endereco, registradores, memoria);
          break;
      case "AC":
          RMO.executar(instrucao.getEnderecoBinario(), endereco, registradores, memoria);
          break;
      case "0C":
          STA.executar(instrucao.getNixbpq(), endereco, registradores, memoria);
          break;
      case "4":
          CLEAR.executar(instrucao.getNixbpq(), endereco, registradores, memoria);
          break;
      }
  }
}
