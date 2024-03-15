package src.Instrucoes;

import src.Memoria.Endereco;
import src.Memoria.Memoria;
import src.Registradores.*;
import src.Utils.Conversao;

/*******
 * LDB *
 *******/
public class LDT extends Instrucao {
	public LDT() {
		super("LDT", "74", 1, true);
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

			valorMem = Conversao.StrNumBinC2(memoria.getValor(enderecoDestino).getInstrucaoBinario());
		}

		registradores.setValor("T", valorMem);
	}
}
