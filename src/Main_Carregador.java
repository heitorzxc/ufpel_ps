package src;

import src.Carregador.Carregador;
import src.Instrucoes.Instrucoes;
import src.Ligador.Ligador;
import src.Maquina.Maquina;

public class Main_Carregador {
   public static void main(String[] args) throws Exception {
        Instrucoes.inicializaInstrucoes();

        String[] paths = {"./resources/macros/programa1.txt", "./resources/macros/programa2.txt"};
        Ligador ligador = new Ligador();

        ligador.executar(paths);

        Carregador carregador = new Carregador();
        carregador.executar("./entrada_maquina.txt");

    }
}
