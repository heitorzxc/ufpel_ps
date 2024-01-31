package src.Instrucoes;

import src.Maquina.*;
import src.Registradores.*;


/*******
 * LDB *
 *******/
public class LDL {
	public static void executar(String nixbpq, Endereco endereco, BancoRegistradores registradores, Memoria memoria){
		Registrador regL = registradores.getRegistrador("L");
		
		System.out.println("A instrucao é: "+endereco.getPalavra().getInsHexa()+".");
		System.out.println("Já que o opcode é = "+endereco.getPalavra().getOpcode()+".");
		System.out.println("Essa instrucao tem indice: "+endereco.getIndice()+".");
		
		if(nixbpq.equals("010000")){
			System.out.println("A intruçao é LDL imediato, que insere o valor inteiro do binário "+endereco.getPalavra().getEnderecoBinario()+" no Registrador L.");
			
			String pegaDado = endereco.getPalavra().getEnderecoBinario();//copiou o endereço binário daquela palavra, que na verdade é um dado. 
			int dadoConvertido = Integer.parseInt(pegaDado, 2); // Converte para inteiro
			System.out.println("O valor no Registrador L será = "+dadoConvertido);
			regL.setNumeroInteiro(dadoConvertido);
			
		} else if (nixbpq.equals("110000")){
			System.out.println("A intruçao é LDL direto, que insere o valor que está no endereço "+endereco.getPalavra().getEnderecoBinario()+" no Registrador L.");
		
			String enderecoDesejado = endereco.getPalavra().getEnderecoBinario();
			int enderecoDesejadoInt = Integer.parseInt(enderecoDesejado, 2);
			
			for (Endereco enderecoComparado : memoria.getMemoria()) {
				if (enderecoComparado.getEndDeci() == enderecoDesejadoInt) {
					// Quando encontra o endereço desejado, acessa o endereço e a instrução associada
					String numeroBinario = enderecoComparado.getPalavra().getNumBin();
					int numeroConvertido = Integer.parseInt(numeroBinario, 2);
					System.out.println("O valor no endereço é = "+numeroConvertido);
					regL.setNumeroInteiro(numeroConvertido);
					break; // Sai do loop quando encontra o endereço
				}
			} 
		}
		
		System.out.println("O valor no Registrador L = "+regL.getNumeroInteiro()+"\n");
		
	}
}
