package src.Utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;

public class Arquivos {
    public static ArrayList<String> lerArquivo(String caminho) throws FileNotFoundException {
        ArrayList<String> code = new ArrayList<>();
        try {
            File obj = new File(caminho);
            Scanner reader = new Scanner(obj);

            while (reader.hasNextLine()) {
                String data = reader.nextLine();

                if (!data.isEmpty()) { // Não adiciona linha vazias
                    String content = data.split(";")[0].replaceAll("\\s+", " ").trim();

                    if (content.length() == 0)
                        continue;

                    code.add(content); // Adiciona a linha sem o comentário
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Arquivo não encontrado!");
        }

        return code;
    }

    public static void salvaArquivo(ArrayList<String> binaryCode) {
        // Usa try-with-resources para garantir que o writer seja fechado corretamente
        String nomeArquivo = "./teste.asm";

        try (PrintWriter writer = new PrintWriter(nomeArquivo)) {
            for (String linha : binaryCode) {
                writer.println(linha);
            }
            // Não é necessário chamar writer.close() explicitamente aqui, pois o
            // try-with-resources cuidará disso
        } catch (FileNotFoundException e) {
            // Esse erro pode ocorrer se o arquivo não puder ser criado ou aberto para
            // escrita
            System.err.println("Não foi possível criar ou abrir o arquivo: " + nomeArquivo);
            e.printStackTrace();
        }
    }

}
