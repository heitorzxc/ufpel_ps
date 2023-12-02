package src.Maquina;
public class Registrador {
    private String numeroBinario;
    private int numeroInteiro;
		
    private String nome;
    private String identificador; 
    private String descricao;
		private int tamanho; // Em bits

    
	Registrador(String nome, String identificador, String descricao){
		this.tamanho = 24; // Como não vai ter o registrador de float que é 48 bits, já seto todos como tamanho 24 bits
		this.descricao = descricao; 
		this.numeroBinario = "000000000000000000000000";
		this.nome = nome;
		this.identificador = identificador;
	}
	
	Registrador(String nome, String identificador, String descricao, String numeroBinario){ 
		this.tamanho = 24;
		this.descricao = descricao;
		this.nome = nome;
		this.identificador = identificador;
		
		if (numeroBinario != null) {
			setNumeroBinario(numeroBinario);
		}
		
		// if (numeroInteiro != Integer.MIN_VALUE) {
		// 		setNumeroInteiro(numeroInteiro);
		// }
	}
    
	// Getters
	public String getNumeroBinario() {  
			return numeroBinario;
	}

	public int getNumeroInteiro() {
			return numeroInteiro;
	}
    
	public int getTamanho() {
		return tamanho;
	}

	public String getNome() {
		return nome;
	}

	public String getIdentificador() {
		return identificador;
	}

	public String getDescricao() {
		return descricao;
	}

    // Setters 
	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
    
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

    
    public void imprimirValorRegistrador() {
        if (numeroBinario != null) {
            System.out.println("Valor do Registrador (Binário): " + numeroBinario);
            System.out.println("Valor do Registrador (Inteiro): " + numeroInteiro);
        } else {
            System.out.println("Registrador vazio.");
        }
    }

}
