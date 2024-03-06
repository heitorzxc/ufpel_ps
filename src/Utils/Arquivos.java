package src.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

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
                    code.add(content); // Adiciona a linha sem o comentário
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Arquivo não encontrado!");
        }

        return code;
    }
}
