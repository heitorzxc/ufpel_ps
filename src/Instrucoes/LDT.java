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
		super("LDT", "74", 1);
	}

	public void executar(Endereco instrucao, BancoRegistradores registradores, Memoria memoria) throws Exception {
		String nixbpe = instrucao.getNIXBPE();
		Integer enderecoDestino = Conversao.StrNumBinC2(instrucao.getEndereco());

		if (nixbpe.startsWith("11")) { // Direto
				enderecoDestino = calculaEnderecoDireto(enderecoDestino, nixbpe, registradores);
		}

		Integer valorMem = Conversao.stringToInt(memoria.getValor(enderecoDestino).getEndereco());

		registradores.setValor("T", valorMem);
	}
}
