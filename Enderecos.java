public class Enderecos {
    private int endDeci;
    private String endHexa;
    private String endBina;
    private Instrucao palavra;
    private int indice;

    public Enderecos(String endereco) {
        setEndereco(endereco);
    }

    public Enderecos(int enderecoInteiro) {
        this.endDeci = enderecoInteiro;
        this.endHexa = Integer.toHexString(enderecoInteiro);
        this.endBina = Integer.toBinaryString(enderecoInteiro);
    }

    public int getEndDeci() {
        return endDeci;
    }

    public String getEndHexa() {
        return endHexa;
    }

    public String getEndBina() {
        return endBina;
    }

    public void setEndereco(String endereco) {
        if (endereco.startsWith("0x")) {
            this.endHexa = endereco.substring(2);
            this.endDeci = Integer.parseInt(endereco.substring(2), 16);
            this.endBina = Integer.toBinaryString(this.endDeci);
        } else if (endereco.matches("[01]+")) {
            this.endBina = endereco;
            this.endDeci = Integer.parseInt(endereco, 2);
            this.endHexa = Integer.toHexString(this.endDeci);
        } else {
            this.endDeci = Integer.parseInt(endereco);
            this.endHexa = Integer.toHexString(this.endDeci);
            this.endBina = Integer.toBinaryString(this.endDeci);
        }
    }

    public void setValor(String valor) {
        setEndereco(valor);
    }

    public void setValor(int valor) {
        setEndereco(Integer.toString(valor));
    }

    public void setValor(Object valor) {
        if (valor instanceof Integer) {
            setEndereco(Integer.toString((int) valor));
        } else if (valor instanceof String) {
            setEndereco((String) valor);
        } else {
            throw new IllegalArgumentException("Tipo de valor não suportado. Apenas int e string são aceitos.");
        }
    }

    // Getter para o atributo 'palavra'
    public Instrucao getPalavra() {
        return palavra;
    }

    // Setter para o atributo 'palavra'
    public void setPalavra(Instrucao palavra) {
        this.palavra = palavra;
    }
    public void setIndice(int index){
        this.indice = index;
    }
    public int getIndice() {
        return indice;
    }

    @Override
    public String toString() {
        return "Enderecos{" +
                "endDeci=" + endDeci +
                ", endHexa='" + endHexa + '\'' +
                ", endBina='" + endBina + '\'' +
                '}';
    }
}