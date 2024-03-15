package src;

import java.io.FileNotFoundException;
import java.io.IOException;

import src.Exceptions.IvalidInstructionFormatError;
import src.Exceptions.RegisterIdenfierError;
import src.Instrucoes.Instrucoes;
import src.Maquina.Maquina;
import src.Montador.Montador;
import src.Montador.MontadorAntigo;

public class Main_Montador {

    public static void main(String[] args) throws Exception {
        // Montador montador = new MontadorAntigo("./resources/codigo/exemplo2.txt",
        // "./resources/resultados/saida.txt");

        // MontadorAntigo montador = new MontadorAntigo("./resources/codigo/exemplo4.txt");
        Instrucoes.inicializaInstrucoes();
        Montador montador = new Montador("./resources/codigo/exemplo4.txt");

        Maquina maquina = Maquina.getInstance();
        maquina.setAquivo("./testez.txt");
        // maquina.executarPrograma();
    }
}
