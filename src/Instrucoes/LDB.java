package src.Instrucoes;

import src.Memoria.Endereco;
import src.Memoria.Memoria;
import src.Registradores.*;

/*******
 * LDB *
 *******/
public class LDB extends Instrucao {
  public LDB() {
    super("LDB", "68", 1);
  }

  public void executar(Endereco instrucao, BancoRegistradores registradores, Memoria memoria) {
    // Registrador regB = registradores.getRegistrador("B");

    // String nixbpq = instrucao.getNixbpq();

    // System.out.println("A instrucao é: " + endereco.getPalavra().getInsHexa() +
    // ".");
    // System.out.println("Já que o opcode é = " + endereco.getPalavra().getOpcode()
    // + ".");
    // System.out.println("Essa instrucao tem indice: " + endereco.getIndice() +
    // ".");

    // if (nixbpq.equals("010000")) {
    // System.out.println("A intruçao é LDB imediato, que insere o valor inteiro do
    // binário "
    // + endereco.getPalavra().getEnderecoBinario() + " no Registrador B.");

    // String pegaDado = endereco.getPalavra().getEnderecoBinario();// copiou o
    // endereço binário daquela palavra, que na
    // // verdade é um dado.
    // int dadoConvertido = Integer.parseInt(pegaDado, 2); // Converte para inteiro
    // System.out.println("O valor no Registrador B será = " + dadoConvertido);

    // regB.setNumeroInteiro(dadoConvertido);
    // } else if (nixbpq.equals("110000")) {
    // System.out.println("A intruçao é LDB direto, que insere o valor que está no
    // endereço "
    // + endereco.getPalavra().getEnderecoBinario() + " no Registrador B.");

    // String enderecoDesejado = endereco.getPalavra().getEnderecoBinario();
    // int enderecoDesejadoInt = Integer.parseInt(enderecoDesejado, 2);

    // for (Endereco enderecoComparado : memoria.getMemoria()) {
    // if (enderecoComparado.getEndDeci() == enderecoDesejadoInt) {
    // // Quando encontra o endereço desejado, acessa o endereço e a instrução
    // // associada
    // String numeroBinario = enderecoComparado.getPalavra().getNumBin();
    // int numeroConvertido = Integer.parseInt(numeroBinario, 2);

    // System.out.println("O valor no endereço é = " + numeroConvertido);
    // regB.setNumeroInteiro(numeroConvertido);
    // break; // Sai do loop quando encontra o endereço
    // }
    // }
    // }

    // System.out.println("O valor no Registrador B = " + regB.getNumeroInteiro() +
    // "\n");
    // System.out.println("--------");
  }
}
