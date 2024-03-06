package src.Maquina;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import src.Exceptions.IvalidInstructionFormatError;
import src.Exceptions.RegisterIdenfierError;
import src.Exceptions.ValueOutOfBoundError;
import src.Instrucoes.Instrucao;
import src.Instrucoes.Instrucoes;
import src.Memoria.Endereco;
import src.Memoria.Memoria;
import src.Registradores.BancoRegistradores;

public class Maquina {
    public BancoRegistradores registradores;
    public Memoria memoria;
    private String arquivo;
    private SimpleBooleanProperty status = new SimpleBooleanProperty(true);

    private List<Integer> FORMATOSVALIDOS = Arrays.asList(8, 16, 24, 32);

    public Maquina(String caminho_arquivo) throws Exception {
        this.memoria = new Memoria();
        this.registradores = new BancoRegistradores();
        Instrucoes.inicializaInstrucoes(); // seta as instrucoes disponiveis

        this.arquivo = caminho_arquivo;

        carregarCodigo();
    }

    public void restart() throws RegisterIdenfierError, ValueOutOfBoundError {
        registradores.setValor("PC", 0);
    }

    private void carregarCodigo() {
        try (BufferedReader br = new BufferedReader(new FileReader(this.arquivo))) {
            String linha;
            Integer endereco = 0;

            while ((linha = br.readLine()) != null) {
                if (linha.equals(""))
                    continue;

                verificaCodigo(linha);
                memoria.setValor(endereco, linha.trim());
                ++endereco;
            }
        } catch (Exception e) {
            e.printStackTrace();
            status.set(false);
        }
    }

    public void setListenerStatus(ChangeListener<Boolean> listener) {
        this.status.addListener(listener);
    }

    private void verificaCodigo(String linha) throws IvalidInstructionFormatError {
        if (!FORMATOSVALIDOS.contains(linha.length())) {
            throw new IvalidInstructionFormatError(linha + " é uma instrucção inválida!");
        }

    }

    public void executarPrograma() throws Exception {
        while (true) {
            if (!step()) // programa parou
                break;
        }
    }

    public Boolean step() throws Exception {
        Integer end = registradores.getValor("PC");

        Endereco instrucao = memoria.getValor(end);

        System.out.println("Opcode: " + instrucao.getOpcode());
        if (instrucao.getOpcode().equals("F4")) {
            // registradores.setValor("SW", 0);
            return false;
        }

        Instrucao operacao = Instrucoes.getInstrucaoPorOpcode(instrucao.getOpcode());
        operacao.executar(instrucao, registradores, memoria);

        System.out.println(registradores);
        registradores.setValor("PC", end + 1);

        return true;
    }
}
