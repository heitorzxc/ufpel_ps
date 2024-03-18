package src.Montador;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import src.Instrucoes.Instrucoes;
import src.Instrucoes.Instrucao;
import src.Utils.Conversao;

public class Montador {
    private HashMap<String, Integer> SYMTAB = new HashMap<>();
    private ArrayList<String> input;
    private ArrayList<String> output;

    public Montador() {
        input = new ArrayList<>();
        output = new ArrayList<>();
    }

    public void executar(String inputPath, String outputPath) {
        lerArquivo(inputPath);
        primeiroPasso();
        printSYMTAB();
        segundoPasso();

        System.err.println(outputPath);
        gerarArquivoOutput(outputPath);
    }

    public void reset() {
        input = new ArrayList<>();
        output = new ArrayList<>();
        SYMTAB = new HashMap<>();
    }

    public void lerArquivo(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                if (!linha.trim().isEmpty()) { // pulando linhas vazias
                    input.add(linha.split(";")[0].trim()); // adicionando no input sem os comentários
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void primeiroPasso() {
        int endereco = 0;

        for (String linha : input) {
            String label = getLabel(linha);

            if (label != null) {
                if (SYMTAB.containsKey(label)) {
                    System.err.println("Erro: label ja definido");
                }
                SYMTAB.put(label, endereco);
            }

            endereco++;
        }
    }

    private void segundoPasso() {
        int endereco = 0;

        for (String linha : input) {
            String operacao = getOperacao(linha);

            if (operacao == null) {
                System.err.println("operacao null");
                continue;
            }

            String[] operandos = getOperandos(linha);
            String codigoBinario = "";

            codigoBinario = traduzirBinario(operacao, operandos);

            if (!codigoBinario.isEmpty()) {
                System.err.println(" ");
                System.err.println(" ");
                System.err.println("Operacao " + operacao);
                System.err.println("Código Binario: " + codigoBinario);
                System.err.println("Tamanho do Binario: " + codigoBinario.length());
                System.err.println(" ");
                System.err.println(" ");
                output.add(codigoBinario);
            }

            endereco++;
        }

    }

    public void gerarArquivoOutput(String caminhoArquivo) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(caminhoArquivo))) {
            for (String linha : output) {
                pw.println(linha);
            }
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }

    private String traduzirBinario(String operacao, String[] operandos) {
        StringBuilder codigo = new StringBuilder();

        System.err.println("Operandos => " + operandos);

        Instrucao instrucao;

        String nixbpe = "110000";
        Integer endereco = 0;

        boolean isFormatoEstendido = operacao.startsWith("+");
        if (isFormatoEstendido) {
            instrucao = Instrucoes.getInstrucaoPorNome(operacao.substring(1));
        } else {
            instrucao = Instrucoes.getInstrucaoPorNome(operacao);
        }

        boolean isFormat34 = instrucao.isFormat34();

        System.err.println("Instrucao => " + instrucao);

        if (isFormat34) {
            for (String operando : operandos) {
                if (operando.startsWith("#")) {
                    // IMEDIATOS
                    nixbpe = isFormatoEstendido ? "010001" : "010000";
                    operando = operando.substring(1); // Remove o '#'
                    endereco = Integer.parseInt(operando); // Converte o valor imediato para inteiro
                } else if (operando.startsWith("@")) {
                    // INDIRETO
                    nixbpe = isFormatoEstendido ? "100001" : "100000";
                    operando = operando.substring(1); // Remove o '@'
                    if (SYMTAB.containsKey(operando)) {
                        endereco = SYMTAB.get(operando);
                    }
                } else if (SYMTAB.containsKey(operando)) {
                    // DIRETO
                    endereco = SYMTAB.get(operando);
                }

                break;
            }

            String opcodeBinario = Conversao.hexToBinary(instrucao.getOpcode(), 8); // opcode 6 bits

            codigo.append(opcodeBinario.substring(0, 6));
            codigo.append(nixbpe);

            if (isFormatoEstendido) {
                codigo.append(Conversao.intToBin(endereco.toString(), 20));
            } else {
                codigo.append(Conversao.intToBin(endereco.toString(), 12));
            }

            return codigo.toString();
        }

        // FORMATO 2
        codigo.append(Conversao.hexToBinary(instrucao.getOpcode(), 8)); // Opcode 8 bits

        // Operandos vão ser registradores
        for (String operando : operandos) {
            System.err.println(operando);
        }
        if (operandos.length == 1) {
            codigo.append(registradorEmBinario(operandos[0]));
            codigo.append("0000");
        } else if (operandos.length == 2) {
            for (String operando : operandos) {
                codigo.append(registradorEmBinario(operando));
            }
        } else {
            System.err.println("Erro: mais de 2 operandos");
        }

        return codigo.toString();
    }

    public void printSYMTAB() {
        System.out.println("Tabela de Símbolos (SYMTAB):");
        for (String label : SYMTAB.keySet()) {
            Integer endereco = SYMTAB.get(label);
            System.out.println(label + ": " + endereco);
        }
    }

    public String getLabel(String linha) {
        String[] parts = linha.trim().split("\\s+");
        String label = parts[0].endsWith(":") ? parts[0].substring(0, parts[0].length() - 1) : null;
        return label;
    }

    public String[] getOperandos(String linha) {
        String[] parts = linha.trim().split("\\s+");

        int startIndex = parts[0].endsWith(":") ? 2 : 1;

        if (parts.length > startIndex) {
            return Arrays.copyOfRange(parts, startIndex, parts.length);
        }

        return new String[0];
    }

    private String registradorEmBinario(String registrador) {
        switch (registrador) {
            case "A":
                return "0000";
            case "X":
                return "0001";
            case "L":
                return "0010";
            case "B":
                return "0011";
            case "S":
                return "0100";
            case "T":
                return "0101";
            case "PC":
                return "0110";
            case "SW":
                return "0111";
            default:
                System.err.println("ERRO -> " + registrador + " Registrador nao identificado");
                return null; // deu errado
        }
    }

    public static String getOperacao(String linha) {
        String[] partes = linha.split("\\s+");
        String primeiraParte = partes[0];

        if (primeiraParte.endsWith(":")) {
            if (partes.length > 1) {
                return partes[1];
            }
        } else {
            // System.err.println("Operação sem rótulo: " + primeiraParte);
        }

        return partes.length > 0 ? primeiraParte : null;
    }
}
