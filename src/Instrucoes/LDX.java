package src.Instrucoes;

import src.Maquina.*;
import src.Registradores.*;

/*******
 * LDX *
 *******/
public class LDX {
	public   void executar(String nixbpq, Endereco endereco, BancoRegistradores registradores, Memoria memoria){
		
		System.out.println("A instrucao é: "+endereco.getPalavra().getInsHexa()+".");
        System.out.println("Já que o opcode é = "+endereco.getPalavra().getOpcode()+".");
        System.out.println("Essa instrucao tem indice: "+endereco.getIndice()+".");

		Registrador regX = registradores.getRegistrador("X");

		String pegaDado = endereco.getPalavra().getEnderecoBinario();
		int dadoConvertido = Integer.parseInt(pegaDado, 2);

		if(nixbpq.equals("010000")){
			System.out.println("A intruçao é LDX imediato, que insere o valor inteiro do binário "+endereco.getPalavra().getEnderecoBinario()+" no Registrador T.");
			System.out.println("O valor no Registrador X será = "+dadoConvertido);
		
		} else if (nixbpq.equals("110000")){
			System.out.println("A intruçao é LDX direto, que insere o valor que está no endereço "+endereco.getPalavra().getEnderecoBinario()+" no Registrador T.");
			for (Endereco enderecoComparado : memoria.getMemoria()) {
				if (enderecoComparado.getEndDeci() == dadoConvertido) {
					// Quando encontra o endereço desejado, acessa o endereço e a instrução associada
					String numeroBinario = enderecoComparado.getPalavra().getNumBin();
					dadoConvertido = Integer.parseInt(numeroBinario, 2);
					System.out.println("O valor no endereço é = "+dadoConvertido);
					break; // Sai do loop quando encontra o endereço
				}
			} 
		}
        regX.setNumeroInteiro(dadoConvertido);
	}
}