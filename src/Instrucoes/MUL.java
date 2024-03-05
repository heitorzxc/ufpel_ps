package src.Instrucoes;

import src.Memoria.Endereco;
import src.Memoria.Memoria;
import src.Registradores.BancoRegistradores;

/*******
 * ADD *
 *******/
public class MUL extends Instrucao {
  public MUL() {
    super("MUL", "20", 1);
  }

  public void executar(Endereco instrucao, BancoRegistradores registradores, Memoria memoria) {
    // Registrador regA = registradores.getRegistrador("A");

    // String nixbpq = instrucao.getNixbpq();

    // if (nixbpq.equals("010000")) {
    // System.out.println("A intruçao é MUL imediato, que multiplica o acumulador
    // com o valor inteiro do binário "
    // + endereco.getPalavra().getEnderecoBinario());
    // String pegaDado = endereco.getPalavra().getEnderecoBinario();// copiou o
    // endereço binário daquela palavra, que na
    // // verdade é um dado.
    // int dadoConvertido = Integer.parseInt(pegaDado, 2); // Converte para inteiro
    // regA.setNumeroInteiro((regA.getNumeroInteiro() * dadoConvertido));//
    // multiplica pelo valor
    // System.out.println("O valor no Registrador A = " + regA.getNumeroInteiro() +
    // "\n");
    // }
  }
}
