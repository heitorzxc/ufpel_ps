package src.Instrucoes;

import src.Memoria.Endereco;
import src.Memoria.Memoria;
import src.Registradores.BancoRegistradores;

/*******
 * ADD *
 *******/
public class SUB extends Instrucao {
  public SUB() {
    super("SUB", "1C", 2);
  }

  public void executar(Endereco instrucao, BancoRegistradores registradores, Memoria memoria) {
    // Registrador regA = registradores.getRegistrador("A");

    // String nixbpq = instrucao.getNixbpq();

    // if (nixbpq.equals("010001")) {
    // System.out.println("A intruçao é SUB imediato, que soma o valor binário "
    // + endereco.getPalavra().getEnderecoBinario() + " no acumulador (Registrador
    // A) como se fosse um dado.");

    // String pegaDado = endereco.getPalavra().getEnderecoBinario();// copiou o
    // endereço binário daquela palavra, que na
    // // verdade é um dado.
    // int dadoConvertido = Integer.parseInt(pegaDado, 2); // Converte para inteiro
    // regA.setNumeroInteiro((regA.getNumeroInteiro() - dadoConvertido));// subtrai
    // o valor
    // regA.setNumeroInteiro((dadoConvertido + regA.getNumeroInteiro()));
    // } else if (nixbpq.equals("110001")) {
    // System.out.println("A intruçao é SUB direto, que subtrai o valor positivo no
    // endereço "
    // + endereco.getPalavra().getEnderecoBinario() + " do acumulador (Registrador
    // A).");

    // String enderecoDesejado = endereco.getPalavra().getEnderecoBinario();
    // int enderecoDesejadoInt = Integer.parseInt(enderecoDesejado, 2); // Converte
    // para inteiro
    // // Percorre a lista de endereços na memória
    // for (Endereco enderecoComparado : memoria.getMemoria()) {
    // if (enderecoComparado.getEndDeci() == enderecoDesejadoInt) {
    // // Quando encontra o endereço desejado, acessa o endereço e a instrução
    // // associada
    // String numeroBinario = enderecoComparado.getPalavra().getNumBin();
    // int numeroConvertido = Integer.parseInt(numeroBinario, 2);
    // System.out.println("O valor no endereço é = " + numeroConvertido);
    // regA.setNumeroInteiro((regA.getNumeroInteiro() - numeroConvertido));
    // break; // Sai do loop quando encontra o endereço
    // }
    // }
    // }
  }
}
