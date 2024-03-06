package src;

import src.Maquina.Maquina;

public class Main_Simulador {

    public static void main(String[] args) throws Exception {

        Maquina maquina = new Maquina("./resources/binarios/ex.txt");
        maquina.executarPrograma();
    }
}
