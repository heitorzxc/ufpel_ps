package src.Registradores;

import src.Exceptions.ValueOutOfBoundError;
import src.Utils.Conversao;

public class Registrador {
	private Integer valor;

	private String nome;
	private String identificador;

	// Como não vai ter o registrador de float que é 48 bits, já seto todos como
	// tamanho 24 bits
	private Integer tamanho = 24;

	Registrador(String nome, String identificador) throws Exception {
		this.setValor(0);
		this.nome = nome;
		this.identificador = identificador;
	}

	Registrador(String nome, String identificador, Integer valor) throws Exception {
		this.nome = nome;
		this.identificador = identificador;

		this.setValor(valor);
	}

	Registrador(String nome, String identificador, String valor) throws Exception {
		this.nome = nome;
		this.identificador = identificador;

		this.setValor(valor);
	}

	// Getters
	public Integer getValor() {
		return valor;
	}

	public String getNome() {
		return nome;
	}

	public String getIdentificador() {
		return identificador;
	}

	// Setters
	public void setValor(String num) throws Exception {
		try {
			this.setValor(Conversao.binToInt(num));
		} catch (Exception e) {
			throw e;
		}
	}

	public void setValor(Integer novoValor) throws ValueOutOfBoundError {
		// Garante que o número esteja dentro do limite de 24 bits
		if (novoValor < 0 || novoValor >= Math.pow(2, this.tamanho)) {
			throw new ValueOutOfBoundError("Valor " + novoValor + " está foram do limite de 24 bits!");
		}

		this.valor = novoValor;
	}

	@Override
	public String toString() {
		return "Valor do Registrador " + identificador + " (" + nome + "): " + valor;
	}

}
