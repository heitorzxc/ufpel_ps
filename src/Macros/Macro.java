package src.Macros;
import java.util.ArrayList;
public class Macro {
    private String nome;
    private ArrayList<String> parametros;
    private ArrayList<String> corpo;
    
    private int chamadas = 0;

    public Macro(String nome, ArrayList<String> parametros, ArrayList<String> corpo) {
        this.nome = nome;
        this.parametros = parametros;
        this.corpo = corpo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<String> getParametros() {
        return parametros;
    }

    public void setParametros(ArrayList<String> parametros) {
        this.parametros = parametros;
    }

    public ArrayList<String> getCorpo() {
        return corpo;
    }

    public void setCorpo(ArrayList<String> corpo) {
        this.corpo = corpo;
    }

    public ArrayList<String> expandir(ArrayList<String> parametrosReais) {
        ArrayList<String> corpoExpandido = new ArrayList<>();

        for (String linha : corpo) {
            String linhaExpandida = new String(linha);

            System.err.println("LINHA EXPANDIDA => " + linhaExpandida);
            
            for (int i = 0; i < parametros.size(); i++) {
                System.err.println(parametros.get(i));
                // System.err.println(parametrosReais.get(i));
                linhaExpandida = linhaExpandida.replace(parametros.get(i), parametrosReais.get(i));
            }
            corpoExpandido.add(linhaExpandida);
        }
        return corpoExpandido;
    }
}
