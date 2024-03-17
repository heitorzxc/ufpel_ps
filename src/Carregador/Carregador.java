package src.Carregador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

import src.Exceptions.IvalidInstructionFormatError;
import src.Maquina.Maquina;
import src.Memoria.Memoria;

public class Carregador {
	Maquina maquina;
	public Memoria memoria;

	public Carregador(){
		maquina = Maquina.getInstance();
		memoria = Memoria.getInstance();
	}

	public void executar(String path){
		carregarCodigo(path);

		try {
			maquina.executarPrograma();
		} catch (Exception exception) {
			System.err.println("Exception na execucao do Montador!");
		}
  	}

	private void carregarCodigo(String path) {
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String linha;
			Integer endereco = 0;

			while ((linha = br.readLine()) != null) {
				if (linha.equals(""))
					continue;

				verificaCodigo(linha);
				memoria.setValor(endereco, linha.trim());
				++endereco;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Erro no carregamento");
		}
	}

	private void verificaCodigo(String linha) throws IvalidInstructionFormatError {
		if (!Arrays.asList(8, 16, 24, 32).contains(linha.length())) {
			throw new IvalidInstructionFormatError(linha + " é uma instrucção inválida!");
		}
	}
    
}
