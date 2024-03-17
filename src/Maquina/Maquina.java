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

    public void restart() throws RegisterIdenfierError, ValueOutOfBoundError {
        registradores.setValor("PC", 0);
    }

    public void setListenerStatus(ChangeListener<Boolean> listener) {
        this.status.addListener(listener);
    }

    public Boolean executarPrograma() throws Exception  {
        System.out.println("ENTROU EXECUTAR");
        memoria.printMemoria();
        while (true) {
            if (!step()) 
                return false;
        }
    }

    public Boolean step() throws Exception {
        Integer valorPc = registradores.getValor("PC");

        Endereco instrucao = memoria.getValor(valorPc);


        if (instrucao.getInstrucaoBinario().length() == 8){
            // Skipando posições vazias de memória (Tamanho 8 é usado como pos vazia)
            return true;
        }

        System.out.println("--- Instrução Atual ---");
        System.out.println("VALOR PC  => " + valorPc);
        System.out.println("Instrução => " + Instrucoes.getInstrucaoPorOpcode(instrucao.getOpcode()).getNome());
        System.out.println("Binario Instrucao Completa: " + instrucao.getInstrucaoBinario());
        System.out.println("Tamanho da Instrução: " + instrucao.getInstrucaoBinario().length());
        System.out.println("Opcode: " + instrucao.getOpcode());
        System.out.println("Nixbpe: " + instrucao.getNIXBPE());
        System.err.println(" ");

        String []operandos = instrucao.getOperandos();

        for (int i = 0; i < operandos.length; i++) {
            if(operandos[i] != null) {
                System.out.println("Operando " + (i + 1) + ": " + Conversao.StrNumBinC2(operandos[i]));
            } else {
                System.out.println("Operando " + (i + 1) + ": " + operandos[i]);
            }
        }
       
        
        if (instrucao.getOpcode().equals("F4")) { // encontr0️⃣ou um "valorPc"
            return false;
        }

        Instrucao operacao = Instrucoes.getInstrucaoPorOpcode(instrucao.getOpcode());
        operacao.executar(instrucao, registradores, memoria);

        registradores.setValor("PC", valorPc + 1);

        return true;
    }
}
