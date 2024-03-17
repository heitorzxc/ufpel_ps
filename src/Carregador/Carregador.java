package src.Carregador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

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
        System.err.println("exception na execucao!");
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
        // System.err.println(linha.length());
        if (!FORMATOSVALIDOS.contains(linha.length())) {
            // System.err.println(linha.length());
            throw new IvalidInstructionFormatError(linha + " é uma instrucção inválida!");
        }

    }

     private List<Integer> FORMATOSVALIDOS = Arrays.asList(8, 16, 24, 32);
}
