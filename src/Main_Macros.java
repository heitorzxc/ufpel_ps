package src;

import java.io.FileNotFoundException;
import java.io.IOException;
import src.Macros.ProcessadorDeMacros;

public class Main_Macros {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        ProcessadorDeMacros Macro = new ProcessadorDeMacros("resources/macros/exemplo2.txt",
                "./testemacroo.txt");
    }
}