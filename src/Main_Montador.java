package src;

import java.io.FileNotFoundException;
import java.io.IOException;

import src.Exceptions.IvalidInstructionFormatError;
import src.Exceptions.RegisterIdenfierError;
import src.Instrucoes.Instrucoes;
import src.Montador.MontadorAntigo;

public class Main_Montador {

    public static void main(String[] args)
            throws FileNotFoundException, IOException, RegisterIdenfierError, IvalidInstructionFormatError {
        // Montador montador = new MontadorAntigo("./resources/codigo/exemplo2.txt",
        // "./resources/resultados/saida.txt");

        MontadorAntigo montador = new MontadorAntigo("./resources/codigo/exemplo2.txt");

        Instrucoes.inicializaInstrucoes();
        montador.executar();
    }
}
