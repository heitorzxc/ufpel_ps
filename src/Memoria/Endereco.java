package src.Memoria;

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
        insHexa = Integer.toHexString(Integer.parseInt(instrucaoBinario, 2)).toUpperCase();

        // interpreta os diferentes tamanhos de instrução
        if (tamanho <= 16) {
            Integer numeroDecimal = Integer.parseInt(instrucaoBinario.substring(0, 8), 2);
            opcode = Integer.toHexString(numeroDecimal).toUpperCase();

            if (tamanho == 16) {
                operando1 = instrucaoBinario.substring(8, 12);
                operando2 = instrucaoBinario.substring(13, 16);
            }

        } else {
            Integer numeroDecimal = Integer.parseInt(instrucaoBinario.substring(0, 6) + "00", 2);
            opcode = Integer.toHexString(numeroDecimal).toUpperCase();
            nixbpe = instrucaoBinario.substring(6, 12);
            enderecoBinario = instrucaoBinario.substring(12, instrucaoBinario.length());

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
}
