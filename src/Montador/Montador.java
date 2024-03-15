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

    public Montador(String path) {
        input = new ArrayList<>();
        output = new ArrayList<>();

        lerArquivo(path);
        primeiroPasso();
        printSYMTAB();
        segundoPasso();
        gerarArquivoOutput("./testez.txt");
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
                System.out.println("Opcode do código binário ->  " + converterBinarioParaHex(codigoBinario));
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

            String opcodeBinario = Conversao.converterHexParaBinarioNBits(instrucao.getOpcode(), 8);
            codigo.append(opcodeBinario); 

            for (String operando : operandos) {
                String codigoRegistrador = registradorEmBinario(operando); 
                codigo.append(codigoRegistrador);
            }

            // // PRECISA SER 6 BITS
            // // System.err.println("OPCODE" + instrucao.getOpcode() + "  " +  "OPCODE APPENDADO = " + Conversao.converterHexParaBinarioNBits(instrucao.getOpcode(), 6));
            // codigo.append(Conversao.converterHexParaBinarioNBits(instrucao.getOpcode(), 6)); // opcode appendado

            // codigo.append(nixbpe);

            // // Sim, por enquanto estou ignorando instruções de tamanho 4.
            // codigo.append(Conversao.intToBin(endereco.toString(), 12)); 

            return codigo.toString();
        } 

        // Se chegou aqui é formato 2

        // Integer enderecoInteger = Conversao.hexToInt(instrucao.getOpcode());
        codigo.append(Conversao.converterHexParaBinarioNBits(instrucao.getOpcode(), 6) + "00");   // Opcode 8 bits

        // Operandos vão ser registradores
        for (String operando : operandos) {
            codigo.append(Conversao.converterHexParaBinarioNBits(operando, 4));
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
    
        // Caso a linha contenha apenas o opcode e um operando (ou nenhum operando).
        if (parts.length == 2) {
            return new String[] { parts[1] };
        }
        // Caso a linha contenha o opcode seguido por dois ou mais operandos.
        else if (parts.length > 2) {
            // Retorna todos os elementos após o opcode.
            return Arrays.copyOfRange(parts, 1, parts.length);
        }
    
        // Retorna um array vazio caso não haja operandos.
        return new String[0];
    }
    
    // public String[] getOperandos(String linha) {
    //     String[] parts = linha.trim().split("\\s+");

    //     if (parts.length == 2) {
    //         return new String[] { parts[1] };
    //     } else if (parts.length > 2) {
    //         return Arrays.copyOfRange(parts, 2, parts.length);
    //     }

    //     return new String[0];
    // }

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

    public static String converterBinarioParaHex(String binario) {
        // Pegar os primeiros 6 bits da string de entrada
        String primeiros6Bits = binario.substring(0, 6);
        // Adicionar 2 bits '00' no final
        String bitsModificados = primeiros6Bits;
        // Converter o valor modificado para hexadecimal
        int valorDecimal = Integer.parseInt(bitsModificados, 2);
        String valorHexadecimal = Integer.toHexString(valorDecimal).toUpperCase();

        // Logar informações
        // System.out.println("Binário original: " + binario);
        // System.out.println("Binário modificado: " + bitsModificados);
        // System.out.println("Hexadecimal: " + valorHexadecimal);

        return valorHexadecimal;
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
