package src.Maquina;

import java.util.ArrayList;
import java.util.Arrays;

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
    private ArrayList<String> JUMPS = new ArrayList<>();

    private static Maquina instance = null;

    // Isso é o formato de projeto singleton
    // Essa linha só permite que a máquina seja instanciada apenas 1x
    public static Maquina getInstance() {
        if (instance == null)
            instance = new Maquina();
        return instance;
    }

    // Aqui é feito o tratamento dos jumps
    // Jumpar para posição i faz com que PC receba a posição i (linha)
    // Memória recebe a nova instância corrente da memória
    // Registradores recebe a nova instância corrente do banco
    private Maquina() {
        JUMPS = new ArrayList<>(Arrays.asList("J", "JEQ", "JGT", "JLT", "JSUB"));
        this.memoria = Memoria.getInstance();
        this.registradores = BancoRegistradores.getInstance();
    }

    // Reinicia a máquina, por efeito, PC = 0.
    public void restart() throws RegisterIdenfierError, ValueOutOfBoundError {
        registradores.setValor("PC", 0);
    }

    // Listeners para comunicação com o FrontEnd
    public void setListenerStatus(ChangeListener<Boolean> listener) {
        this.status.addListener(listener);
    }

    public Boolean executarPrograma() throws Exception {
        System.out.println("ENTROU EXECUTAR");
        memoria.printMemoria();
        while (true) {
            if (!step())
                return false;
        }
    }

    public Boolean step() throws Exception {

        // Aqui tem o valor da execução corrente
        Integer valorPc = registradores.getValor("PC");

        Endereco instrucao = memoria.getValor(valorPc);

        // Inicializamos a memoria com enderecos vazios de tamanho 8
        if (instrucao.getInstrucaoBinario().length() == 8) {
            // Skipando posições vazias de memória (Tamanho 8 é usado como pos vazia)
            if (instrucao.getOpcode().equals("F4")) { // encontr0️⃣ou um "valorPc"
                return false;
            }
            return true;
        }

        System.out.println("VALOR PC  => " + valorPc);
        System.out.println("Instrução => " + Instrucoes.getInstrucaoPorOpcode(instrucao.getOpcode()).getNome());
        System.out.println("Binario Instrucao Completa: " + instrucao.getInstrucaoBinario());
        System.out.println("Tamanho da Instrução: " + instrucao.getInstrucaoBinario().length());
        System.out.println("Opcode: " + instrucao.getOpcode());
        System.out.println("Nixbpe: " + instrucao.getNIXBPE());
        System.err.println(" ");
        System.out.println("--- Instrução Atual ---");

        String[] operandos = instrucao.getOperandos();

        for (int i = 0; i < operandos.length; i++) {
            if (operandos[i] != null) {
                System.out.println("Operando " + (i + 1) + ": " + Conversao.StrNumBinC2(operandos[i]));
            } else {
                System.out.println("Operando " + (i + 1) + ": " + operandos[i]);
            }
        }

        // Identificando a instrução (se é uma instrução válida)
        Instrucao operacao = Instrucoes.getInstrucaoPorOpcode(instrucao.getOpcode());

        // Executando a instrução
        operacao.executar(instrucao, registradores, memoria);

        // Verificando se é uma instrução de jump, se for não atualizamos PC + 1 (pro PC
        // ir pro endereco do jump),a atualizacao do PC (ou não) é feita pelas
        // instrucoes de jump
        if (JUMPS.contains(instrucao.getNomeInstrucao())) {
            return true;
        }

        // Jump de PC + 1 pois o simulador faz jump de linha a linha
        registradores.setValor("PC", valorPc + 1);
        return true;

    }
}
