package src.Instrucoes;

import src.Memoria.Endereco;
import src.Memoria.Memoria;
import src.Registradores.*;

/*******
 * LDB *
 *******/
public class LDT extends Instrucao {
	public LDT() {
		super("LDT", "74", 1);
	}

	public void executar(Endereco instrucao, BancoRegistradores registradores, Memoria memoria) {
		// Registrador regT = registradores.getRegistrador("T");

		// String nixbpq = instrucao.getNixbpq();

		// if (nixbpq.equals("010000")) {
		// System.out.println("A intruçao é LDT imediato, que insere o valor inteiro do
		// binário "
		// + endereco.getPalavra().getEnderecoBinario() + " no Registrador T.");
		// String pegaDado = endereco.getPalavra().getEnderecoBinario();// copiou o
		// endereço binário daquela palavra,
		// // que na verdade é um dado.

		// int dadoConvertido = Integer.parseInt(pegaDado, 2); // Converte para inteiro

		// System.out.println("O valor no Registrador T será = " + dadoConvertido);

		// regT.setNumeroInteiro(dadoConvertido);

		// } else if (nixbpq.equals("110000")) {
		// System.out.println("A intruçao é LDT direto, que insere o valor que está no
		// endereço "
		// + endereco.getPalavra().getEnderecoBinario() + " no Registrador T.");
		// String enderecoDesejado = endereco.getPalavra().getEnderecoBinario();
		// int enderecoDesejadoInt = Integer.parseInt(enderecoDesejado, 2);
		// for (Endereco enderecoComparado : memoria.getMemoria()) {
		// if (enderecoComparado.getEndDeci() == enderecoDesejadoInt) {
		// // Quando encontra o endereço desejado, acessa o endereço e a instrução
		// // associada
		// String numeroBinario = enderecoComparado.getPalavra().getNumBin();
		// int numeroConvertido = Integer.parseInt(numeroBinario, 2);
		// System.out.println("O valor no endereço é = " + numeroConvertido);
		// regT.setNumeroInteiro(numeroConvertido);
		// break; // Sai do loop quando encontra o endereço
		// }
		// }
		// }
	}
}
