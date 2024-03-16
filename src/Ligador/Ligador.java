package src.Ligador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import src.Instrucoes.Instrucoes;
import src.Montador.Montador;

public class Ligador {
	public ArrayList<String[]> programas = new ArrayList<>();

	public Ligador(String[] pathsProgramas) {
		Instrucoes.inicializaInstrucoes();

		inicializaProgramas(pathsProgramas);
	}

	public void inicializaProgramas(String[] paths) {
		for(String path : paths) {
			programas.add(lePrograma(path));
		}
	}

	public String[] lePrograma(String path) {
		ArrayList<String> inputList = new ArrayList<>();
	
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String linha;
	
			while ((linha = br.readLine()) != null) {
				if (!linha.trim().isEmpty()) {
					inputList.add(linha.trim());
				}
			}
	
			return inputList.toArray(new String[0]);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("ERRO LENDO PATH");
			return new String[0];
		}
	}
	

}