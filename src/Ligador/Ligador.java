package src.Ligador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import src.Instrucoes.Instrucoes;
import src.Macros.ProcessadorDeMacros2;
import src.Montador.Montador;

public class Ligador {
	private ArrayList<String[]> programas = new ArrayList<>();
	private ProcessadorDeMacros2 processadorMacros = new ProcessadorDeMacros2();
	private Montador montador = new Montador();

	public Ligador(String[] pathsProgramas) {
		Instrucoes.inicializaInstrucoes();
		inicializaProgramas(pathsProgramas);
		montaProgramas();
	}

	public void montaProgramas(){
		int indexPrograma = 0; 

		for(String[] programa : programas){
			processadorMacros.reset();
			processadorMacros.processa(programa, "./saida_macro" + indexPrograma + ".txt");
			montador.reset();
			montador.montagem("./saida_macro" + indexPrograma + ".txt", "./saida_montador" + indexPrograma + ".txt");

			indexPrograma++;
		}
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