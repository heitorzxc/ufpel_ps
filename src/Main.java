package src;

import java.io.FileNotFoundException;
import java.io.IOException;

import src.Maquina.Maquina;
import src.Montador.Montador;


public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Montador montador = new Montador("./resources/codigo/exemplo2.txt", "./resources/resultados/saida.txt");
        // Maquina maquina = new Maquina();
        // maquina.carregarInstrucoes("./resources/exemplo2.txt");
        // maquina.executarPrograma();
    }
}
