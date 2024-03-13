package src;

import src.Maquina.Maquina;

public class Main_Simulador {

    public static void main(String[] args) throws Exception {
        Maquina maquina = Maquina.getInstance();
        // maquina.setAquivo("./resources/binarios/exemplo2.txt");
        maquina.setAquivo("./teste.asm");
        maquina.executarPrograma();
    }
}
