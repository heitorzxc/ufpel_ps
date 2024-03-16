package src.Macros;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ProcessadorDeMacros2 {
    private ArrayList<String> input;
    private HashMap<String, Macro> macros;
    private ArrayList<String> outputExpandido = new ArrayList<>();

    public ProcessadorDeMacros2(String inputFile) {
        input = new ArrayList<>();
        outputExpandido = new ArrayList<>();
        macros = new HashMap<>();

        lerArquivo(inputFile);
        processar();
        gerarArquivoOutput("./saida_macro.txt");
    }

     public void lerArquivo(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                if (!linha.trim().isEmpty()) { // pulando linhas vázias
                    input.add(linha.trim());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

     public void gerarArquivoOutput(String caminhoArquivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            for (String linha : outputExpandido) {
                writer.write(linha);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Não considerando macros aninhadas, 
    public void processar() {
        boolean definindoMacro = false; // Se achar uma definição de macro esse aqui vai pra true até achar o fim
        Macro macroAtual = null;
        String nomeMacro = "";
        ArrayList<String> parametrosMacro = new ArrayList<>();
        ArrayList<String> corpoMacro = new ArrayList<>();

        for(String linha : input) {
            System.err.println("LINHA => " + linha);
            if(linha.startsWith("MACRO")) { // definição de macro
                System.err.println("== Achou macro ===");
                definindoMacro = true;
                String[] partes = linha.split("\\s+");
                nomeMacro = partes[1]; // pega o nome da macro
                System.err.println("nome macro => " + nomeMacro);

                String[] params = partes[2].split(",");
                
                for (String param : params) {
                    System.err.println("PARAMETRO => " + param);
                    parametrosMacro.add(param.trim());
                }

                continue; // Definiu o nome da macro e os parametros, continua pra proxima linha
            }

            if(linha.startsWith("MEND")) { // fim de definição da macro
                definindoMacro = false;

                // de fato define a macro, passando o nome os parametros e o corpo
                macroAtual = new Macro(nomeMacro, parametrosMacro, corpoMacro); 
                macros.put(nomeMacro, macroAtual);

                nomeMacro = "";
                parametrosMacro = new ArrayList<>(); 
                corpoMacro = new ArrayList<>();
                continue;
            }

            if(definindoMacro) { // se chegou aqui e esta definindo macro, vai fazer parte do corpo
                corpoMacro.add(linha);
            } else {  // se chegou aqui vai estar fora de definicao de macro
                processarLinha(linha);
            }
            
        }
    }

    public void processarLinha(String linha) {
        String[] partes = linha.split("\\s+"); // linha dividida


        if(macros.containsKey(partes[0])) { // verifica se eh uma chamda de macro
            String nomeMacro = partes[0];
            Macro macro = macros.get(nomeMacro);


            ArrayList<String> parametrosReais = new ArrayList<>();

            if (partes.length > 1) { // se tem parametros pra ser substituido
                String[] paramsReais = partes[1].split(",");

                for (String param : paramsReais) {
                    parametrosReais.add(param.trim());
                }
            } 

            ArrayList<String> macroExpandida = macro.expandir(parametrosReais);

            for (String linhaExpandida : macroExpandida) {
                // Substitui vírgulas por espaços em cada linha expandida da macro antes de adicionar ao output
                String linhaModificada = linhaExpandida.replace(",", " ");
                outputExpandido.add(linhaModificada);
            }
        } else { // se n eh uma chmada de macro
            outputExpandido.add(linha);
        }

    }
}