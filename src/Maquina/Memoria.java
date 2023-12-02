package src.Maquina;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Memoria {
    private ArrayList<Endereco> memoria;
    private Integer tamanhoMemoria;

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

    // private void carregarInstrucoes(String arquivo) {
    //     try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
    //         String linha;
    //         int endereco = 0;

    //         while ((linha = br.readLine()) != null) {
    //             Instrucao instrucao = new Instrucao(linha.trim());

    //             Endereco enderecoAtual = new Endereco(Integer.toString(endereco));
    //             enderecoAtual.setPalavra(instrucao);

    //             memoria.add(enderecoAtual);

    //             // Aumenta o endereço conforme o tamanho da instrução em bits
    //             endereco += instrucao.getNumBin().length();
    //         }
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }

    public List<Endereco> getMemoria() {
        return memoria;
    }
    
    public void setPosicaoMemoria(int index, Endereco endereco){
        memoria.set(index, endereco); // Seta endereco na posição ind ex
    }

    public void imprimirMemoria() {
        for (Endereco endereco : memoria) {
            System.out.println("Intrucao com endereço: " + endereco.getEndDeci());
            Instrucao palavra = endereco.getPalavra();
            
            
            if(palavra != null){
                palavra.imprimirDetalhesInstrucao();
            }
            System.out.println("--------");
        }
    }

}
