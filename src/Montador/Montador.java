package src.Montador;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;

import src.Exceptions.IvalidInstructionFormatError;
import src.Exceptions.RegisterIdenfierError;
import src.Instrucoes.Instrucoes;
import src.Instrucoes.Instrucao;
import src.Registradores.BancoRegistradores;
import src.Utils.Arquivos;
import src.Utils.Conversao;

// START -> Specify name and starting address for the program.
// END -> Indicate the end of the source program and (optionally) specify the
// first executable instruction in the program.
// BYTE -> Generate character or hexadecimal constant, occupying as many
// bytes as needed to represent the constant.
// WORD -> Generate one-word integer constant.
// RESB -> Reserve the indicated number of bytes for a data area.
// RESW -> Reserve the indicated number of words for a data area.

// LABEL // OPERATION // OPERANDO 1 // OPERANDO 2

public class Montador {
    private HashMap<String, Integer> SYMTAB = new HashMap<>(); // TABELA DE
    // TABELA DE INSTRUCOES é obtida com o uso de Instrucoes.getInstrucaoPorNome
    private ArrayList<String> input;
    private ArrayList<String> intermediario;
    private ArrayList<String> finalCode;

    private static final char INDIRETO = '@';
    private static final char IMEDIATO = '#';

    public Montador() throws Exception {
        input = new ArrayList<>();
        intermediario = new ArrayList<>();
        try {
            primeiroPasso();
            segundoPasso();
        } catch (Exception exception) {
            throw new Exception("Erro durante a montagem: " + exception.getMessage(), exception);
        }
    }

    public void lerArquivo(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                if (!linha.trim().isEmpty()) { // pulando linhas vázias
                    input.add(linha.split(";")[0]); // adicionando no input sem os comentários
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void primeiroPasso() throws Exception {
        int LOCCTR = 0;

        String linha = input.get(0);
        String label;
        String opcode = getOpcode(linha);
        String[] operandos;

        int contadorLinha = 0;

        if (opcode != null && opcode.equals("START")) {
            LOCCTR = Conversao.binToInt(getOperandos(linha)[0]);
            contadorLinha++;
        }

        while (opcode != "END") {
            linha = input.get(contadorLinha);
            label = getLabel(linha);
            opcode = getOpcode(linha);
            operandos = getOperandos(linha);

            if (opcode.equals("END")) {
                break;
            }

            if (label != null) {
                if (SYMTAB.containsKey(label)) {
                    // se já ta na tabela de símbolos da erro
                    throw new Exception("Erro - Label já definido");
                } else {
                    SYMTAB.put(label, LOCCTR);
                }
            }

            LOCCTR += 1;
            intermediario.add(linha);
        }
    }

    private void segundoPasso() throws Exception {
        int index = 0;
        String linha = intermediario.get(index);

        String opcode = getOpcode(linha);
        int end;

        if (opcode.equals("START")) {
            end = Conversao.binToInt(getOperandos(linha)[0]);
            index++;
        }
        while (opcode != "END") {
            Instrucao instrucao = Instrucoes.getInstrucaoPorNome(opcode);
            String[] operandos = getOperandos(linha);

            if (instrucao != null) {
                StringBuilder linhaOutput = new StringBuilder(Conversao.hexToBin(instrucao.getOpcode()));

                if (operandos.length == 1) {
                    // formato 3/4
                }

                else if (operandos.length == 2) {

                }

                if (operandos.length > 0 && SYMTAB.containsKey(operandos[0])) {
                    linhaOutput.append(SYMTAB.get(operandos[0]));
                }
                finalCode.add(linhaOutput.toString());
            }

            index++;
        }
    }

    public String getLabel(String linha) {
        String[] parts = linha.trim().split("\\s+");
        String label = parts[0].endsWith(":") ? parts[0].substring(0,
                parts[0].length() - 1) : null;
        return label;
    }

    public String[] getOperandos(String linha) {
        String[] parts = linha.trim().split("\\s+");
        if (parts.length > 2) {
            return Arrays.copyOfRange(parts, 2, parts.length);
        }
        return new String[0]; // Retorna um array vazio caso não haja operandos
    }

    public static String getOpcode(String linha) {
        String[] parts = linha.trim().split("\\s+");
        return parts.length >= 2 ? parts[1] : null;
    }

    public String getNixbpe(String opcode, String[] operandos) {
        Integer nix;

        String operando = operandos[0];

        switch (operando.charAt(0)) { // tipos de enderecamento
            case IMEDIATO:
                nix = 16;
                break;

            case INDIRETO:
                nix = 32;
                break;

            default: // direto
                nix = 48;
                break;
        }

        if (operando.endsWith("X")) // usa o registrador X para o endereçamento
            nix = nix | 8;

        if (operando.endsWith("B")) // usa o registrador B para o endereçamento
            nix = nix | 4;

        if (opcode.startsWith("+")) // opernado é 20 bits
            return Conversao.intToBin(nix | 1);

        return Conversao.intToBin(nix.toString(), 6);
    }

}
