public class Registrador {
    private String numeroBinario;
    private int numeroInteiro;

    public void setNumeroBinario(String numBin) {
        if (numBin.length() <= 24) {
            this.numeroBinario = numBin.toUpperCase();
            this.numeroInteiro = Integer.parseInt(numBin, 2);
        } else {
            System.out.println("O número binário excede o limite de 24 bits.");
        }
    }

    public void setNumeroInteiro(int numInteiro) {
        if (numInteiro >= 0 && numInteiro <= 16777215) { // Garante que o número esteja dentro do limite de 24 bits
            String binarioFormatado = String.format("%24s", Integer.toBinaryString(numInteiro)).replace(' ', '0');
            this.numeroBinario = binarioFormatado;
            this.numeroInteiro = numInteiro;
        } else {
            System.out.println("O número inteiro está fora do limite permitido para 24 bits.");
        }
    }

    public String getNumeroBinario() {
        return numeroBinario;
    }

    public int getNumeroInteiro() {
        return numeroInteiro;
    }

    public void imprimirValorRegistrador() {
        if (numeroBinario != null) {
            System.out.println("Valor do Registrador (Binário): " + numeroBinario);
            System.out.println("Valor do Registrador (Inteiro): " + numeroInteiro);
        } else {
            System.out.println("Registrador vazio.");
        }
    }
}