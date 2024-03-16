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
import java.io.FileWriter;
import java.io.IOException;

import src.Exceptions.IvalidInstructionFormatError;
import src.Exceptions.RegisterIdenfierError;
import src.Instrucoes.Instrucoes;
import src.Instrucoes.Instrucao;
import src.Registradores.BancoRegistradores;
import src.Utils.Arquivos;
import src.Utils.Conversao;

public class Montador {
    private HashMap<String, Integer> SYMTAB = new HashMap<>();
    private ArrayList<String> input;
    private ArrayList<String> output;

    private static final char INDIRETO = '@';
    private static final char IMEDIATO = '#';

    public Montador() {
        input = new ArrayList<>();
        output = new ArrayList<>();
    }
    
    public void montagem(String inputPath, String outputPath) {
        lerArquivo(inputPath);
        primeiroPasso();
        printSYMTAB();
        segundoPasso();
        gerarArquivoOutput(outputPath);
    }

    public void reset(){
        input = new ArrayList<>();
        output = new ArrayList<>();
        SYMTAB = new HashMap<>();
    }


    public void lerArquivo(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                if (!linha.trim().isEmpty()) { // pulando linhas vázias
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
            // System.err.println(label);

            if (label != null) {
                if (SYMTAB.containsKey(label)) {
                    // throw new Exception("Rótulo duplicado: " + label);
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


            Instrucao instrucao = Instrucoes.getInstrucaoPorNome(operacao);


            if (instrucao == null) {
                System.err.println("Instrução invalida!! => " + operacao);
                continue;
            }

            String[] operandos = getOperandos(linha);
            String codigoBinario = "";

            codigoBinario = traduzirBinario(instrucao, operandos, instrucao.isFormat34());

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

    

    private String traduzirBinario(Instrucao instrucao, String[] operandos, boolean isFormat34) {
        StringBuilder codigo = new StringBuilder();
        
        String nixbpe = "110000";
        Integer endereco = 0; 
    
        if (isFormat34) {
            for (String operando : operandos) {
                if (operando.startsWith("#")) {
                    // IMEDIATOS
                    nixbpe = "010000"; 
                    operando = operando.substring(1); // Remove o '#'
                    endereco = Integer.parseInt(operando); // Converte o valor imediato para inteiro
                } else if (operando.startsWith("@")) {
                    // INDIRETO
                    nixbpe = "100000"; 
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
            
            // // Sim, por enquanto estou ignorando instruções de tamanho 4.
            codigo.append(opcodeBinario.substring(0, 6)); 
            codigo.append(nixbpe);
            codigo.append(Conversao.intToBin(endereco.toString(), 12)); 

            // // PRECISA SER 6 BITS
            // // System.err.println("OPCODE" + instrucao.getOpcode() + "  " +  "OPCODE APPENDADO = " + Conversao.converterHexParaBinarioNBits(instrucao.getOpcode(), 6));

            return codigo.toString();
        } 

        // FORMATO 2
        codigo.append(Conversao.hexToBinary(instrucao.getOpcode(), 8));   // Opcode 8 bits

        // Operandos vão ser registradores
        if(operandos.length == 1){
            codigo.append(registradorEmBinario(operandos[0]));
            codigo.append("0000");
        } else if(operandos.length == 2) {
            for (String operando : operandos) {
                codigo.append(registradorEmBinario(operando));
            }
        } else {
            System.err.println("MAIS DE 2 OPERANDOOOOOOOOOOS");
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
    
        if (parts.length == 2) {
            return new String[] { parts[1] };
        }
        else if (parts.length > 2) {
            return Arrays.copyOfRange(parts, 1, parts.length);
        }
    
        return new String[0];
    }


    private String registradorEmBinario (String registrador) {
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
                return "777777777"; // deu errado
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
