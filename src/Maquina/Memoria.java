package src.Maquina;
import java.util.ArrayList;
import java.util.List;

import src.Instrucoes.Instrucao;
import src.Interface.Controller;

public class Memoria {
    private ArrayList<Endereco> memoria;
    private Integer tamanhoMemoria;
    Controller controller;
    
    public void setController(Controller controller){
        this.controller = controller;
    }

    public Memoria() {
        memoria = new ArrayList<>(); // Inicializa a lista usando ArrayList
        tamanhoMemoria = 250; // 250 posições de memória (número temporário, da pra alterar livremente)
        inicializaMemoria();
    }
    
    public void inicializaMemoria(){ // inicializando 'tamanhoMemória' posições de memoria, incialmente preenchidas com 0000
        for (int i = 0; i < tamanhoMemoria; i++) {
            Endereco endereco = new Endereco(0);
            this.memoria.add(endereco);
        }
    }

    public List<Endereco> getMemoria() {
        return memoria;
    }
    
    public List<Endereco> getMemoriaComInstrucoes() {
        List<Endereco> memoriaComInstrucoes = new ArrayList<>();

        for (Endereco endereco : memoria) {
            Instrucao palavra = endereco.getPalavra();

            if (palavra != null) {
                memoriaComInstrucoes.add(endereco);
            }
        }

        return memoriaComInstrucoes;
    }
    
    public void setPosicaoMemoria(int index, Endereco endereco){
        memoria.set(index, endereco); // Seta endereco na posição ind ex
    }

    public void imprimirMemoria() {
        //alteração
        controller.handleTERMINAL("============= MEMORIA ==========");
        for (Endereco endereco : memoria) {
            Instrucao palavra = endereco.getPalavra();
            
            
            if(palavra != null){
            //alteração
                palavra.setController(controller); 
                controller.handleTERMINAL(" \n \n Intrucao com endereço: " + endereco.getEndDeci() + " \n");
                palavra.imprimirDetalhesInstrucao();
            }
        }
        controller.handleTERMINAL("========== FIM MEMORIA ========");
    }

}
