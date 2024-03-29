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

    private final Integer TAMANHO_MEMORIA = 5000;

    private Memoria() {
        memoria = FXCollections.observableArrayList();
        inicializaMemoria();
    }

    private void inicializaMemoria(){
        for (int i = 0; i < TAMANHO_MEMORIA; i++) {
            memoria.add(new Endereco("00000000")); // Valor padrão,
        }
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
        return (endereco >= 0) && (endereco < TAMANHO_MEMORIA);
    }

    public void setMemoria(String[] codigo) throws SpaceExceededError, ValueOutOfBoundError {
        this.limpaMemoria();

        if (codigo.length > TAMANHO_MEMORIA) {
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

        String valorExpandido = valor.length() < 8 ? Conversao.expandeBinario(valor, 8) : valor;

        System.err.println(" ");
        System.err.println(" ");
        System.err.println(" ");
        System.err.println(" ");
        System.err.println(" ");
        System.err.println(" ");

        memoria.set(endereco, new Endereco(valorExpandido));
    }

    public void printMemoria() {
        System.out.println("============= Conteúdo da Memória =============");
        for (int i = 0; i < memoria.size(); i++) {
            Endereco endereco = memoria.get(i);
            String valor = endereco.getInstrucaoBinario(); 
            
            if(valor != "00000000")
                System.out.println("Endereço " + i + ": " + valor);
        }
        System.out.println("=========== Fim do Conteúdo da Memória ==========");
    }
    

    public Endereco getValor(Integer endereco) {
        return memoria.get(endereco);
    }

    public ObservableList<Endereco> getMemoria() {
        return memoria;
    }


}
