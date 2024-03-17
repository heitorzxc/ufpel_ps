package src;

// import java.io.FileNotFoundException;
// import java.io.IOException;

// import src.Exceptions.IvalidInstructionFormatError;
// import src.Exceptions.RegisterIdenfierError;
import src.Instrucoes.Instrucoes;
// import src.Ligador.Ligador;
import src.Macros.ProcessadorDeMacros2;
// import src.Maquina.Maquina;
import src.Montador.Montador;

public class Main_Montador {

    public static void main(String[] args) throws Exception {
        Instrucoes.inicializaInstrucoes();

        ProcessadorDeMacros2 processadorMacros = new ProcessadorDeMacros2();
        processadorMacros.reset();
        processadorMacros.processa("./resources/macros/programa1.txt", "./testes/saida1.txt");
        Montador montador = new Montador();
        montador.executar("./testes/saida1.txt", "./testes/saida_montador_prog_2.txt");

        // String[] paths = {"./resources/macros/programa1.txt", "./resources/macros/programa2.txt"};

        // Ligador ligador = new Ligador();
        // ligador.executar(paths);
        // Maquina maquina = Maquina.getInstance();
        // maquina.setAquivo("./testez.txt");
        // maquina.executarPrograma();
    }
}
