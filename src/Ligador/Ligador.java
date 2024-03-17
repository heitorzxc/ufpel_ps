package src.Ligador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import src.Macros.ProcessadorDeMacros2;
import src.Montador.Montador;

public class Ligador {
	private ArrayList<String[]> programas;
	private ArrayList<String> programasMontadosPaths;
	private ProcessadorDeMacros2 processadorMacros;
	private Montador montador;

	public Ligador() {
		programas = new ArrayList<>();
		programasMontadosPaths = new ArrayList<>();
		processadorMacros = new ProcessadorDeMacros2();
		montador = new Montador();
	}

	public void executar(String[] pathsProgramas){
		inicializaProgramas(pathsProgramas);
		montaProgramas();
		unificaProgramas();
	}

	public void montaProgramas(){
		int indexPrograma = 0; 

		String programaMontadoPath = "";

		for(String[] programa : programas){
			processadorMacros.reset();
			processadorMacros.processa(programa, "./resources/saidas/saida_macro" + indexPrograma + ".txt");
			montador.reset();

			programaMontadoPath = "./resources/saidas/saida_montador" + indexPrograma + ".txt";
			montador.executar("./resources/saidas/saida_macro" + indexPrograma + ".txt", programaMontadoPath);
			programasMontadosPaths.add(programaMontadoPath);

			indexPrograma++;
		}
	}

	public void unificaProgramas() {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("./resources/saidas/entrada_maquina.txt"))) {
        for (String pathProgramaMontado : programasMontadosPaths) {
            ArrayList<String> conteudoPrograma = lerConteudoArquivoFinal(pathProgramaMontado);
            
            for (String linha : conteudoPrograma) {
                writer.write(linha);
                writer.newLine();
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
        System.err.println("Erro ao escrever o arquivo final de programas mergeados.");
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
	

	public ArrayList<String> lerConteudoArquivoFinal(String path) {
    ArrayList<String> conteudo = new ArrayList<>();
    
    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
        String linha;
        
        while ((linha = br.readLine()) != null) {
            if (!linha.trim().isEmpty()) {
                conteudo.add(linha.trim());
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
        System.err.println("Erro ao ler o arquivo: " + path);
    }
    
    return conteudo;
}


}