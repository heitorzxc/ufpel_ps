package src.Registradores;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import src.Exceptions.RegisterIdenfierError;
import src.Exceptions.ValueOutOfBoundError;

public class BancoRegistradores {
	private ObservableMap<String, Registrador> bancoRegistradores;

	private static BancoRegistradores instance;

	public static BancoRegistradores getInstance() {
		if (instance == null)
			instance = new BancoRegistradores();

		return instance;
	}

	private BancoRegistradores() {
		bancoRegistradores = FXCollections.observableHashMap();
		setRegistrador(new Registrador("Acumulador", "A"));
		setRegistrador(new Registrador("Registrador de índice", "X"));
		setRegistrador(new Registrador("Registrador de ligação", "L"));
		setRegistrador(new Registrador("Registrador base", "B"));
		setRegistrador(new Registrador("Registrador de uso geral", "S"));
		setRegistrador(new Registrador("Registrador de uso geral", "T"));
		setRegistrador(new Registrador("Contador de Instruções", "PC"));
		setRegistrador(new Registrador("Palavra de status", "SW"));
	}

	private Boolean hasReg(String reg) {
		return bancoRegistradores.containsKey(reg);
	}

	private void setRegistrador(Registrador reg) {
		bancoRegistradores.put(reg.getIdentificador(), reg);
	}

	public Integer getValor(String reg) throws RegisterIdenfierError {
		String cod = decotifica(reg);

		if (cod.equals("")) {
			cod = reg;
		}

		if (!hasReg(cod)) {
			throw new RegisterIdenfierError("Registrador " + reg + " não é válido!");
		}

		return bancoRegistradores.get(cod).getValor();
	}

	private String decotifica(String reg) {
		switch (reg) {
			case "0000":
				return "A";

			case "0001":
				return "X";

			case "0010":
				return "L";

			case "0011":
				return "B";

			case "0100":
				return "S";

			case "0101":
				return "T";

			case "0110":
				return "PC";

			case "0111":
				return "SW";

			default:
				return "";

		}
	}

	public void setValor(String reg, Integer valor) throws RegisterIdenfierError, ValueOutOfBoundError {
		String cod = decotifica(reg);

		if (cod.equals("")) {
			cod = reg;
		}

		if (!hasReg(cod)) {
			throw new RegisterIdenfierError("Registrador " + reg + " não é válido!");
		}

		bancoRegistradores.get(cod).setValor(valor);
	}

	public String getNumeroRegistrador(String reg) throws RegisterIdenfierError {
		if (!hasReg(reg)) {
			throw new RegisterIdenfierError("Registrador " + reg + " não é válido!");
		}

		return bancoRegistradores.get(reg).getIdentificador();
	}

	public void setListener(ChangeListener<? super Number> listener) {
		for (String reg : bancoRegistradores.keySet()) {
			bancoRegistradores.get(reg).setListener(listener);
		}
	}

	@Override
	public String toString() {
		System.out.println("Valores nos Registradores:");
		for (Registrador registrador : bancoRegistradores.values()) {
			System.out.println(registrador);
		}

		return "";
	}
}
