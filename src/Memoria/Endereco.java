package src.Memoria;

import src.Instrucoes.Instrucoes;
import src.Utils.Conversao;

public class Endereco {
    private String instrucaoBinario;
    private String opcode;
    private String nixbpe;
    private String insHexa;

    private String enderecoBinario;
    private String operando1, operando2;

    public Endereco(String instrucao) {
        this.instrucaoBinario = instrucao.toUpperCase();

        interpretarInstrucao();
    }

    private void interpretarInstrucao() {
        int tamanho = instrucaoBinario.length();

        if (tamanho == 8) {
            return;
        }

        if (tamanho == 16) {
            // FORMATO 2
            // 8 bits 4 bits 4 bits
            // OPCODE + REGISTRADOR 1 + REGISTRADOR 2

            opcode = Integer.toHexString(Integer.parseInt(instrucaoBinario.substring(0, 8), 2)).toUpperCase();

            System.out.println("Tamanho 16 =>" + opcode);
            System.out.println("INSTRUÇÃO IDENTIFICADA => " + Instrucoes.getInstrucaoPorOpcode(opcode).getNome());
            System.err.println(" ");
            operando1 = instrucaoBinario.substring(8, 12); // Primeiro registrador
            operando2 = instrucaoBinario.substring(12, 16); // Segundo registrador
        } else if (tamanho == 24) {
            // Formato 3
            // 6 bits 6 bits 12 bits
            // OPCODE + NIXBPE + DISP

            opcode = Integer.toHexString(Integer.parseInt(instrucaoBinario.substring(0, 6), 2)).toUpperCase();
            System.out.println("Tamanho 24 =>" + opcode);
            System.out.println("INSTRUÇÃO IDENTIFICADA => " + Instrucoes.getInstrucaoPorOpcode(opcode).getNome());
            System.err.println(" ");
            nixbpe = instrucaoBinario.substring(6, 12);
            enderecoBinario = instrucaoBinario.substring(12);

        } else if (tamanho == 32) {
            // FORMATO 4
            // 6 bits 6 bits 20 bits
            // OPCODE + NIXBPE + ADDRES EXTENDIDO
            opcode = Integer.toHexString(Integer.parseInt(instrucaoBinario.substring(0, 6) + "00", 2)).toUpperCase();
            System.out.println("Tamanho 32 =>" + opcode);
            System.out.println("INSTRUÇÃO IDENTIFICADA => " + Instrucoes.getInstrucaoPorOpcode(opcode).getNome());
            System.err.println(" ");
            nixbpe = instrucaoBinario.substring(6, 12);
            enderecoBinario = instrucaoBinario.substring(12);
            // A diferença para o formato 3 está na interpretação do endereço, que é mais
            // extenso.
        }
    }

    public String getOpcode() {
        return this.opcode;
    }

    public String getNIXBPE() {
        return this.nixbpe;
    }

    public String getInstrucaoHexa() {
        return this.insHexa;
    }

    public String getInstrucaoBinario() {
        return this.instrucaoBinario;
    }

    public String getEndereco() {
        return this.enderecoBinario;
    }

    public String[] getOperandos() {
        String[] t = { operando1, operando2 };
        return t;
    }

    public void setInstrucao(String instrucao) {
        this.enderecoBinario = instrucao;
        interpretarInstrucao();
    }

    // public String getValor() {
    // return Conversao.hexToBin(insHexa);
    // }
}
