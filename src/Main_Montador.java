package src;

import java.io.FileNotFoundException;
import java.io.IOException;

import src.Montador.*;

public class Main_Montador {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Montador montador = new Montador("./resources/codigo/exemplo2.txt", "./resources/resultados/saida.txt");
    }
}
