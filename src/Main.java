package src;

import java.io.FileNotFoundException;
import java.io.IOException;

import src.Maquina.Maquina;


public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Maquina maquina = new Maquina();
        maquina.carregarInstrucoes("./resources/exemplo2.txt");
        // maquina.memoria.imprimirMemoria();
        maquina.executarPrograma();
        // maquina.lerArquivo();
        // maquina.print();
        // maquina.lerInput();
    }
}
