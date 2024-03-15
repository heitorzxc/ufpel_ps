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

            System.err.println("OPERACAO =>>>" + operacao);

            if (instrucao == null) {
                System.err.println("Instrução invalida!! => " + operacao);
                continue;
            }

            String[] operandos = getOperandos(linha);
            System.err.println("INSTRUÇÃO" + instrucao.getNome());
            for (String operando : operandos) {
                System.err.println("OPERANDO AAAA ->" + operando);
                // output.add();
            }

            String codigoBinario = "";

            if (instrucao.isFormat34()) {
                codigoBinario = traduzirBinario(instrucao, operandos);
                output.add(codigoBinario);
            } else {
                System.err.println(" ");
                System.err.println(" ");
                System.err.println(" ");
                codigoBinario = Conversao.hexToBin(instrucao.getOpcode()) + "00";

                System.err.println("Entrou aqui");
                output.add(codigoBinario);
                for (String operando : operandos) {
                    System.err.println("OPERANDO AAAA ->" + Conversao.hexToBin(operando));
                    output.add(Conversao.hexToBin(operando));
                }

                System.err.println(" ");

            }

            if (!codigoBinario.isEmpty()) {
                System.err.println(" ");
                System.err.println(" ");
                System.err.println("Código Binario: " + codigoBinario);
                output.add(codigoBinario);
                System.out.println("Endereço: " + endereco + ", Código Binário: " + codigoBinario);
            }

            endereco++;
        }

    }

    private String traduzirBinario(Instrucao instrucao, String[] operandos) {

        StringBuilder codigo = new StringBuilder();

        String nixbpe = "110000";
        codigo.append(Conversao.hexToBin(instrucao.getOpcode()));
        Integer endereco = 0;

        for (String operando : operandos) {
            // System.err.println("operando ----------- >" + operando);

            if (operando.startsWith("#")) {
                // IMEDIATOS
                nixbpe = "010000";
                operando = operando.substring(1); // remove o #
                endereco = Integer.parseInt(operando); // garantindo que vai ser int
            } else if (operando.startsWith("@")) {
                // INDIRETO
                nixbpe = "100000";
                operando = operando.substring(1); // Remove o @

                if (SYMTAB.containsKey(operando)) {
                    endereco = SYMTAB.get(operando);
                }
            } else if (SYMTAB.containsKey(operando)) {
                // o endereço do label vai ta na tabeal de simbolos
                endereco = SYMTAB.get(operando);
            }

        }

        codigo.append(nixbpe);
        // System.err.println("ENDERECO =>>>>" + endereco);
        // System.err.println("CODIGO +>>>>" + codigo.toString());
        // System.err.println("ENDERECO CONVERTIDO =>>>>" +
        // Conversao.intToBin(endereco));

        

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

        // Se houver apenas duas partes, pode ser uma instrução de Formato 2 com um
        // registrador como operando.
        if (parts.length == 2) {
            // Retorna o segundo elemento como um array de um único operando.
            return new String[] { parts[1] };
        } else if (parts.length > 2) {
            // Se existirem mais partes, assumimos que os operandos começam na terceira
            // posição.
            return Arrays.copyOfRange(parts, 2, parts.length);
        }
        // Retorna um array vazio caso não haja operandos além do opcode.
        return new String[0];
    }

    public static String getOperacao(String linha) {
        String[] partes = linha.split("\\s+");
        String primeiraParte = partes[0];

        if (primeiraParte.endsWith(":")) {
            if (partes.length > 1) {
                // System.err.println("Rótulo encontrado: " + primeiraParte + " | Operação: " +
                // partes[1]);
                return partes[1];
            }
        } else {
            // System.err.println("Operação sem rótulo: " + primeiraParte);
        }

        return partes.length > 0 ? primeiraParte : null;
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
