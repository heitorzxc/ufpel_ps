package src.Macros;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class ProcessadorDeMacros {

    private HashMap<String, String> tabelaMacro = new HashMap<>(); // armazena a definição/expansão de cada macro
    private ArrayList<String> content = new ArrayList<>(); // armazena o código com as chamadas de macro
    private HashMap<String, ArrayList<String>> argumentosMacro = new HashMap<>(); // armazena os argumentos das macros

    public ProcessadorDeMacros(String inputFile, String outputFile) throws IOException {

        leArquivo(inputFile);
        ArrayList<String> finalCode = montaCodigoFinal();
          // defineMacro(code);
        // buscaParametros(code);
        // substituiParametro();

        System.out.println("------------- código final --------------");
        for (int i = 0; i < finalCode.size(); i++) {
            System.out.println(finalCode.get(i));
        }
    }

    public void leArquivo(String file) throws FileNotFoundException {
        ArrayList<String> code = new ArrayList<>();

        try {
            File obj = new File(file);
            Scanner reader = new Scanner(obj);
            System.out.println("Arquivo de entrada:\n");
            while (reader.hasNextLine()) {
                String data = reader.nextLine().trim();
                code.add(data);
                System.out.println(data);
            }
            System.out.println("\nFim do arquivo\n");
            reader.close();

        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Arquivo não encontrado");
        }

        // defineMacro(code);
        // buscaParametros(code);
        // substituiParametro();
    }

    public void defineMacro(ArrayList<String> code) {
        boolean chamaMacro = false;
        boolean encontrouMacro = false;

        Integer counter = 0;
        Stack<String> macroStack = new Stack<>();
        StringBuilder contentMacro = new StringBuilder();

        // Percorre linha por linha do código de entrada
        for (int i = 0; i < code.size(); i++) {
            String linha = code.get(i);

            // Identifica as palavras-chave de definição de macro

            // Palavra-chave de início de definição de Macro
            if (linha.contains("MCDEFN")) {
                String[] proximaPalavra = code.get(i + 1).split(" ");

                // Verifica se não é uma chamada de macro dentro de outra
                if (!chamaMacro) {
                    linha = removeChamada(linha, "MCDEFN");
                    macroStack.push(proximaPalavra[0]);
                    chamaMacro = true;
                } else {
                    encontrouMacro = true;
                }
                counter++;
            }

            // Palavra-chave de fim de definição de Macro
            if (linha.contains("MCEND")) {

                tabelaMacro.put(macroStack.firstElement(), contentMacro.toString());
                if (!content.contains(macroStack.firstElement())) {
                    content.add(macroStack.firstElement());
                }
                counter++;
                if (counter % 2 == 0) {
                    chamaMacro = false;
                }
            }

            // Vê se está ou não dentro de uma chamada de macro
            if (chamaMacro) {
                contentMacro.append(linha).append("\n");
            } else if (!linha.contains("MCEND")) {
                content.add(linha);
            }

        }
        content.add("FIM");
        if (encontrouMacro) {
            expandeMacro();
        }
        imprimeTabela();
    }

    public void expandeMacro() {
        boolean achou = false;
        for (String keyMacro : tabelaMacro.keySet()) {

            String contentMacro = tabelaMacro.get(keyMacro);
            ArrayList<String> novaChamada = new ArrayList<>();
            String[] linhas = contentMacro.split("\\n");

            for (String linha : linhas) {
                if (linha.contains("MCDEFN") || achou) {
                    novaChamada.add(linha);
                    achou = true;
                }
            }
            defineMacro(novaChamada);
        }
    }

    public ArrayList<String> montaCodigoFinal() {
        ArrayList<String> callList = new ArrayList<>();

        for (int i = 0; i < content.size(); i++) {
            String palavra = content.get(i);
            if (!palavra.equals("FIM")) {
                callList.add(palavra);
            } else {
                callList.add(palavra);
                break;
            }
        }
        int size = callList.size();

        for (int i = 0; i < size; i++) {
            String keyMacro = callList.get(i);

            if (tabelaMacro.containsKey(keyMacro)) {
                String[] newContent = tabelaMacro.get(keyMacro).split("\\n");
                callList.set(i, newContent[1]);
            }
        }
        return callList;
    }

    public void buscaParametros(ArrayList<String> codigo) {

        ArrayList<String> macroEncontrada = new ArrayList<String>();
        Set<String> macroNames = tabelaMacro.keySet();

        // Percorre o código já com as chamadas de Macros
        for (String line : codigo) {

            // Percorre as macros definidas
            for (String key : macroNames) {

                // Identifica os argumentos da Macro (nome_macro arg1=x,arg2=y, ...)
                if (line.contains(key) && line.contains("=")) {

                    ArrayList<String> variaveisEncontradas = new ArrayList<>();
                    if (!macroEncontrada.contains(key)) {
                        macroEncontrada.add(key);
                    } else {

                        // Separa o nome da macro dos argumentos p/ identific=a-los
                        String[] parts = line.split("\\s+");
                        for (String part : parts) {

                            if (!part.contains(key)) {

                                // Remove a vírgula caso tenha mais de um argumento
                                if (part.contains(",")) {
                                    part = part.replaceAll("[,]", "");
                                }

                                variaveisEncontradas.add(part);
                            }
                        }
                    }
                    argumentosMacro.put(key, variaveisEncontradas);
                }
            }
        }
        System.out.println("Variáveis encontradas:");
        for (String var : argumentosMacro.keySet()) {
            System.out.println("Macro: " + var + "\n\s Variáveis: " + argumentosMacro.get(var) + "\n");
        }
    }

    public void substituiParametro() {

        // Percorre cada macro definida
        for (String key : tabelaMacro.keySet()) {
            String contents = tabelaMacro.get(key);

            String[] line = contents.split("\\n");

            // Percorre cada linha da definição de uma macro
            for (String contentLine : line) {

                // Encontra na macro a linha que contém: "nome_macro arg1=,arg2=" para
                // substituir os parâmetros
                if (contentLine.contains(key)) {

                    String[] parts = contentLine.split("\\s+");

                    for (String part : parts) {

                        if (!part.contains(key)) {

                        }
                    }

                }
                /*
                 * Set<String> macroArgs = argumentosMacro.keySet();
                 * 
                 * // Percorre os argumentos definidos
                 * for(String nomeMacrosArgs : macroArgs){
                 * ArrayList<String> contentArgs = argumentosMacro.get(nomeMacrosArgs);
                 * 
                 * // Verifica se o nome da macro percorrida é igual á macro que contém
                 * argumentos
                 * if(key.equals(nomeMacrosArgs)){
                 * 
                 * // Percorre os argumentos armazenados
                 * for(String argumento : contentArgs){
                 * 
                 * String[] part = argumento.split("=");
                 * 
                 * // Substiui
                 * 
                 * }
                 * }
                 * }
                 */
            }

        }

    }

    public String removeChamada(String linha, String palavra) {
        String[] palavras = linha.split("\\s+");
        StringBuilder novaLinha = new StringBuilder();

        for (String palavraAtual : palavras) {
            if (!palavraAtual.equals(palavra)) {
                novaLinha.append(palavraAtual).append(" ");
            }
        }
        return novaLinha.toString().trim();
    }

    public void imprimeTabela() {
        System.out.println("\n\n########## Tabela definição de  Macros: ##########\n");
        for (String chave : tabelaMacro.keySet()) {
            System.out.println("Macro:" + chave + "\n " + tabelaMacro.get(chave) + "\n\n");
        }
    }

}