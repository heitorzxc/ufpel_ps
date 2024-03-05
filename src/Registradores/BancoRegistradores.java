package src.Registradores;

import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import src.Exceptions.RegisterIdenfierError;
import src.Exceptions.ValueOutOfBoundError;

public class BancoRegistradores {
	private ObservableMap<String, Registrador> bancoRegistradores;

	public BancoRegistradores() throws Exception {
		bancoRegistradores = FXCollections.observableHashMap();
		setRegistrador(new Registrador("Acumulador", "A", 128));
		setRegistrador(new Registrador("Registrador de índice", "X", 0));
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
		if (!hasReg(reg)) {
			throw new RegisterIdenfierError("Registrador " + reg + " não é válido!");
		}

		return bancoRegistradores.get(reg).getValor();
	}

	public void setValor(String reg, Integer valor) throws RegisterIdenfierError, ValueOutOfBoundError {
		if (!hasReg(reg)) {
			throw new RegisterIdenfierError("Registrador " + reg + " não é válido!");
		}

		bancoRegistradores.get(reg).setValor(valor);
	}

	public String getNumeroRegistrador(String reg) throws RegisterIdenfierError {
		if (!hasReg(reg)) {
			throw new RegisterIdenfierError("Registrador " + reg + " não é válido!");
		}

		return bancoRegistradores.get(reg).getIdentificador();
	}

	public void setListener(MapChangeListener<String, Registrador> listener) {
		bancoRegistradores.addListener(listener);
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
