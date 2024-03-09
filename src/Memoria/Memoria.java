package src.Memoria;

import src.Exceptions.SpaceExceededError;
import src.Exceptions.ValueOutOfBoundError;
import src.Utils.Conversao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener;

public class Memoria {

    private ObservableList<Endereco> memoria;

    private static Memoria instance = null;

    private final Integer TAMANHOMEMORIA = 250;

    private Memoria() {
        memoria = FXCollections.observableArrayList();
    }

    public static Memoria getInstance() {
        if (instance == null) {
            instance = new Memoria();
        }

        return instance;
    }

    //// interface deve setar um listener para monitoriar as mudancas na memoria
    public void setListener(ListChangeListener<? super Endereco> listener) {
        memoria.addListener(listener);
    }

    private void limpaMemoria() {
        memoria.clear();
    }

    private Boolean enderecoValido(Integer endereco) {
        return (endereco >= 0) && (endereco < TAMANHOMEMORIA);
    }

    public void setMemoria(String[] codigo) throws SpaceExceededError, ValueOutOfBoundError {
        this.limpaMemoria();

        if (codigo.length > TAMANHOMEMORIA) {
            throw new SpaceExceededError("O código fornecido excede o tamanho da memória!");
        }

        for (int index = 0; index < codigo.length; ++index) {
            setValor(index, codigo[index]);
        }
    }

    public void setValor(Integer endereco, String valor) throws ValueOutOfBoundError {
        if (!enderecoValido(endereco)) {
            throw new ValueOutOfBoundError(endereco + " esta fora dos limites da memoria!");
        }

        if (valor.length() < 8) {
            Conversao.expandeBinario(valor, 8);
        }

        memoria.add(new Endereco(valor));
    }

    public Endereco getValor(Integer endereco) {
        return memoria.get(endereco);
    }

    public ObservableList<Endereco> getMemoria() {
        return memoria;
    }

    // public Boolean addInstrucao(String inst) {
    // try {
    // this.setValor(posicaoAdicionar, inst);
    // } catch (Exception e) {
    // return false;
    // }

    // return true;
    // }

    // // Modificar o conteudo para 'Dados' ou 'conteudo'
    // public void setValor(Integer index, String valor) throws ValueOutOfBoundError
    // {
    // if (!enderecoValido(index)) {
    // throw new ValueOutOfBoundError(index + " esta fora dos limites da memoria!");
    // }
    // memoria.set(index, new Endereco(valor)); // Seta endereco na posição ind ex
    // }

    // public Endereco getValor(Integer endereco) throws ValueOutOfBoundError {
    // if (!enderecoValido(endereco)) {
    // throw new ValueOutOfBoundError(endereco + " esta fora dos limites da
    // memoria!");
    // }

    // return memoria.get(endereco);
    // }

    // @Override
    // public String toString() {
    // System.err.println("============= MEMORIA ==========");
    // for (Endereco endereco : memoria) {
    // Instrucao palavra = endereco.getPalavra();

    // if (palavra == null) {
    // continue;
    // }
    // System.out.println("");
    // System.out.println("");
    // System.out.println("Intrucao com endereço: " + endereco.getEndDeci());
    // System.out.println(palavra);
    // System.out.println("");
    // System.out.println("");

    // }
    // System.err.println("========== FIM MEMORIA ========");

    // return "";
    // }

}
