package src;

import java.io.FileNotFoundException;
import java.io.IOException;

import src.Maquina.Maquina;

public class Main_Simulador {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Maquina maquina = new Maquina("./resources/binarios/exemplo2.txt");
        maquina.executarPrograma();
    }
}
