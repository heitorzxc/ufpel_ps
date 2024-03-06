package src.Instrucoes;

import src.Memoria.Endereco;
import src.Memoria.Memoria;
import src.Registradores.*;
import src.Utils.Conversao;

/*******
 * LDB *
 *******/
public class LDL extends Instrucao {
	public LDL() {
		super("LDL", "8", 1);
	}

	public void executar(Endereco instrucao, BancoRegistradores registradores, Memoria memoria) throws Exception {
		String nixbpe = instrucao.getNIXBPE();
		Integer enderecoDestino = Conversao.StrNumBinC2(instrucao.getEndereco());

		Integer valorMem = 0;

		if (nixbpe.startsWith("01")) { // IMEDIATO
			valorMem = enderecoDestino;
		} else {
			if (nixbpe.startsWith("11")) { // DIRETO
				enderecoDestino = calculaEnderecoDireto(enderecoDestino, nixbpe, registradores);
			}

			if (nixbpe.startsWith("10")) { // INDIRETO
				Endereco enderecoMemoria = memoria.getValor(enderecoDestino);
				enderecoDestino = Conversao.StrNumBinC2(enderecoMemoria.getEndereco());
			}

			valorMem = Conversao.stringToInt(memoria.getValor(enderecoDestino).getEndereco());
		}

		registradores.setValor("L", valorMem);
	}

}

// Registrador regL = registradores.getRegistrador("L");

// String nixbpq = instrucao.getNixbpq();
// System.out.println("A instrucao é: " + endereco.getPalavra().getInsHexa() +
// ".");
// System.out.println("Já que o opcode é = " + endereco.getPalavra().getOpcode()
// + ".");
// System.out.println("Essa instrucao tem indice: " + endereco.getIndice() +
// ".");

// if (nixbpq.equals("010000")) {
// System.out.println("A intruçao é LDL imediato, que insere o valor inteiro do
// binário "
// + endereco.getPalavra().getEnderecoBinario() + " no Registrador L.");

// String pegaDado = endereco.getPalavra().getEnderecoBinario();// copiou o
// endereço binário daquela palavra,
// // que na verdade é um dado.
// int dadoConvertido = Integer.parseInt(pegaDado, 2); // Converte para inteiro
// System.out.println("O valor no Registrador L será = " + dadoConvertido);
// regL.setNumeroInteiro(dadoConvertido);

// } else if (nixbpq.equals("110000")) {
// System.out.println("A intruçao é LDL direto, que insere o valor que está no
// endereço "
// + endereco.getPalavra().getEnderecoBinario() + " no Registrador L.");

// String enderecoDesejado = endereco.getPalavra().getEnderecoBinario();
// int enderecoDesejadoInt = Integer.parseInt(enderecoDesejado, 2);

// for (Endereco enderecoComparado : memoria.getMemoria()) {
// if (enderecoComparado.getEndDeci() == enderecoDesejadoInt) {
// // Quando encontra o endereço desejado, acessa o endereço e a instrução
// // associada
// String numeroBinario = enderecoComparado.getPalavra().getNumBin();
// int numeroConvertido = Integer.parseInt(numeroBinario, 2);
// System.out.println("O valor no endereço é = " + numeroConvertido);
// regL.setNumeroInteiro(numeroConvertido);
// break; // Sai do loop quando encontra o endereço
// }
// }
// }

// System.out.println("O valor no Registrador L = " + regL.getNumeroInteiro() +
// "\n");
