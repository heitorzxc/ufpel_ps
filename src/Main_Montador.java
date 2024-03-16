package src;

import java.io.FileNotFoundException;
import java.io.IOException;

import src.Exceptions.IvalidInstructionFormatError;
import src.Exceptions.RegisterIdenfierError;
import src.Instrucoes.Instrucoes;
import src.Macros.ProcessadorDeMacros2;
import src.Maquina.Maquina;
import src.Montador.Montador;
import src.Montador.MontadorAntigo;

public class Main_Montador {

    public static void main(String[] args) throws Exception {
        Instrucoes.inicializaInstrucoes();

        ProcessadorDeMacros2 processador = new ProcessadorDeMacros2("./entrada_macro.txt");
        // processador.processar();
        Montador montador = new Montador("./saida_macro.txt");
        // Montador montador = new Montador("./resources/codigo/exemplo4.txt");

        Maquina maquina = Maquina.getInstance();
        maquina.setAquivo("./testez.txt");
        maquina.executarPrograma();
    }
}
