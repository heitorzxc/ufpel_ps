package src.Instrucoes;

import src.Memoria.Endereco;
import src.Memoria.Memoria;
import src.Registradores.*;

/*******
 * AND *
 *******/
public class AND extends Instrucao {
  public AND() {
    super("AND", "40", 1);
  }

  public void executar(Endereco instrucao, BancoRegistradores registradores, Memoria memoria) {
    String nixbpe = instrucao.getNIXBPE();
    instrucao.getNIXBPE().substring(0, 0);
    // Registrador regA = registradores.getRegistrador("A");
    // String pegaDado = endereco.getPalavra().getEnderecoBinario();
    // int dadoConvertido = Integer.parseInt(pegaDado, 2);

    // if (nixbpq.equals("010001")) {
    // System.out.println("A intruçao é AND imediato, que armazena o valor binário "
    // + endereco.getPalavra().getEnderecoBinario() + " no acumulador (Registrador
    // A).");
    // } else if (nixbpq.equals("110001")) {

    // System.out.println("A intruçao é AND direto, que armazena o valor no endereço
    // "
    // + endereco.getPalavra().getEnderecoBinario() + " no acumulador (Registrador
    // A).");

    // for (Endereco enderecoComparado : memoria.getMemoria()) {
    // if (enderecoComparado.getEndDeci() == dadoConvertido) {

    // String numeroBinario = enderecoComparado.getPalavra().getNumBin();
    // dadoConvertido = Integer.parseInt(numeroBinario, 2);
    // System.out.println("O valor no endereço é = " + dadoConvertido);
    // break;
    // }
    // }
    // }
    // regA.setNumeroInteiro((dadoConvertido & regA.getNumeroInteiro()));
  }
}
