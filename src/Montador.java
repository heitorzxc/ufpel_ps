package src;

import java.io.FileNotFoundException;
import java.io.IOException;

import src.Montador.Montador;


public class Montador {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Montador montador = new Montador("./resources/codigo/exemplo2.txt", "./resources/resultados/saida.txt");
    }
}
