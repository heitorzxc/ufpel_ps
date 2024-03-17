package src;

import src.Carregador.Carregador;
import src.Instrucoes.Instrucoes;
import src.Ligador.Ligador;

public class Main_Carregador {
   public static void main(String[] args) throws Exception {
        Instrucoes.inicializaInstrucoes();

        String[] paths = {"./resources/entradas/carregador/programa1.txt", "./resources/entradas/carregador/programa2.txt"};
        Ligador ligador = new Ligador();

        ligador.executar(paths);

        Carregador carregador = new Carregador();
        carregador.executar("./resources/saidas/entrada_maquina.txt");

    }
}
