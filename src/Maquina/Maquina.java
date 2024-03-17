package src.Maquina;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import src.Exceptions.RegisterIdenfierError;
import src.Exceptions.ValueOutOfBoundError;
import src.Instrucoes.Instrucao;
import src.Instrucoes.Instrucoes;
import src.Memoria.Endereco;
import src.Memoria.Memoria;
import src.Registradores.BancoRegistradores;
import src.Utils.Conversao;

public class Maquina {
    public BancoRegistradores registradores;
    public Memoria memoria;
    private SimpleBooleanProperty status = new SimpleBooleanProperty(true);

    private static Maquina instance = null;

    public static Maquina getInstance() {
        if (instance == null)
            instance = new Maquina();
        return instance;
    }

    private Maquina() {
        this.memoria = Memoria.getInstance();
        this.registradores = BancoRegistradores.getInstance();
    }

    // public void setAquivo(String caminho_arquivo) throws RegisterIdenfierError, ValueOutOfBoundError {
    //     this.arquivo = caminho_arquivo;

    //     carregarCodigo();
    // }

    public void restart() throws RegisterIdenfierError, ValueOutOfBoundError {
        registradores.setValor("PC", 0);
    }

    // private void carregarCodigo() {
    //     try (BufferedReader br = new BufferedReader(new FileReader(this.arquivo))) {
    //         String linha;
    //         Integer endereco = 0;

    //         while ((linha = br.readLine()) != null) {
    //             if (linha.equals(""))
    //                 continue;

    //             verificaCodigo(linha);
    //             memoria.setValor(endereco, linha.trim());
    //             // System.out.println(memoria.getValor(endereco));
    //             ++endereco;
    //         }
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         status.set(false);
    //     }
    // }

    public void setListenerStatus(ChangeListener<Boolean> listener) {
        this.status.addListener(listener);
    }

    

    public Boolean executarPrograma() throws Exception  {
        System.out.println("ENTROU EXECUTAR");
        memoria.printMemoria();
        while (true) {
            if (!step()) // programa parou
                return false;
        }
    }

    public Boolean step() throws Exception {
        Integer valorPc = registradores.getValor("PC");

        Endereco instrucao = memoria.getValor(valorPc);

        if (instrucao.getInstrucaoBinario().length() == 8){
            // Skipando posições vazias de memória (Tamanho 8 é usado como pos vazia)
            return false;
        }

        System.out.println("--- Instrução Atual ---");
        System.out.println("VALOR PC  => " + valorPc);
        System.out.println("Instrução Completa: " + instrucao.getInstrucaoBinario());
        System.out.println("Tamanho da Instrução: " + instrucao.getInstrucaoBinario().length());
        System.out.println("Opcode: " + instrucao.getOpcode());
        System.out.println("Nixbpe: " + instrucao.getNIXBPE());
        System.out.println("Instrução => " + Instrucoes.getInstrucaoPorOpcode(instrucao.getOpcode()).getNome());

        String []operandos = instrucao.getOperandos();

        for (int i = 0; i < operandos.length; i++) {
            if(operandos[i] != null) {
                System.out.println("Operando " + (i + 1) + ": " + Conversao.StrNumBinC2(operandos[i]));
            } else {
                System.out.println("Operando " + (i + 1) + ": " + operandos[i]);
            }
        }
       
        
        if (instrucao.getOpcode().equals("F4")) { 
            return false;
        }

        Instrucao operacao = Instrucoes.getInstrucaoPorOpcode(instrucao.getOpcode());
        operacao.executar(instrucao, registradores, memoria);

        registradores.setValor("PC", valorPc + 1);

        return true;
    }
}
