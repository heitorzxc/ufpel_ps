package src.Maquina;
import java.math.BigInteger;

public class Instrucao {
    protected String numBin;
    protected int tamanho;
    protected String opcode;
    protected String insHexa;
    protected String enderecoBinario;
    protected String nixbpq; // Adicionando o atributo nixbpq

    public Instrucao(String numBin) {
        this.numBin = numBin.toUpperCase();

        // Verifica se o número binário tem um tamanho aceitável
        if (numBin.length() == 8 || numBin.length() == 16 || numBin.length() == 24 || numBin.length() == 32) {
            interpretarInstrucao();
        } else {
            System.out.println("Número binário inválido. Deve ter 8, 16, 24 ou 32 bits.");
        }
    }

    protected void interpretarInstrucao() {
        tamanho = numBin.length();

        // Lógica para interpretar os diferentes tamanhos de instrução
        if (tamanho == 8) {
            int numeroDecimal = Integer.parseInt(numBin, 2);
            opcode = Integer.toHexString(numeroDecimal).toUpperCase();
            insHexa = opcode;
        } else if (tamanho == 16) {
            int numeroDecimal = Integer.parseInt(numBin.substring(0, 8), 2);
            opcode = Integer.toHexString(numeroDecimal).toUpperCase();
            insHexa = Integer.toHexString(Integer.parseInt(numBin, 2)).toUpperCase();
            enderecoBinario = numBin.substring(8, 16);
        } else if (tamanho == 24) {
            int numeroDecimal = Integer.parseInt(numBin.substring(0, 6) + "00", 2);
            opcode = Integer.toHexString(numeroDecimal).toUpperCase();
            nixbpq = numBin.substring(6, 12);
            enderecoBinario = numBin.substring(12, 24);
            insHexa = Integer.toHexString(Integer.parseInt(numBin, 2)).toUpperCase();
        } else if (tamanho == 32) {
            BigInteger numeroDecimal = new BigInteger(numBin.substring(0, 6) + "00", 2);
            if (numeroDecimal.equals(BigInteger.ZERO)) {
                opcode = "00";
            } else {
                opcode = numeroDecimal.toString(16).toUpperCase();
            }
            enderecoBinario = numBin.substring(12, 32);
            nixbpq = numBin.substring(6, 12);
            insHexa = new BigInteger(numBin, 2).toString(16).toUpperCase();
        }
    }

     // Getters e Setters para numBin
     public String getNumBin() {
        return numBin;
    }

    public void setNumBin(String numBin) {
        this.numBin = numBin;
        interpretarInstrucao(); // Chama a interpretação toda vez que o numBin é atualizado
    }

    // Getters para insHexa e opcode (sem setters porque são derivados)
    public String getInsHexa() {
        return insHexa;
    }

    public String getOpcode() {
        return opcode;
    }

    // Getters e Setters para enderecoBinario
    public String getEnderecoBinario() {
        return enderecoBinario;
    }

    public void setEnderecoBinario(String enderecoBinario) {
        this.enderecoBinario = enderecoBinario;
    }

    // Getters e Setters para nixbpq
    public String getNixbpq() {
        return nixbpq;
    }

    public void setNixbpq(String nixbpq) {
        this.nixbpq = nixbpq;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void imprimirDetalhesInstrucao() {
        System.out.println("--------");
        System.out.println("Detalhes da Instrucao:");
        System.out.println("Número binário da instruçao: " + numBin);
        System.out.println("Número hexadecimal da instruçao: " + insHexa);
        System.out.println("Opcode: " + opcode);
        if (enderecoBinario != null) {
            System.out.println("Endereço em binário: " + enderecoBinario);
        }
        if (nixbpq != null) {
            System.out.println("nixbpq: " + nixbpq);
        }
    }
}
