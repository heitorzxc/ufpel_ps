package src.Instrucoes;

import src.Maquina.Endereco;
import src.Maquina.Memoria;
import src.Registradores.BancoRegistradores;

public interface InstrucaoExecutavel {
  void executar(Instrucao instrucao, Endereco endereco, BancoRegistradores registradores, Memoria memoria);
}
