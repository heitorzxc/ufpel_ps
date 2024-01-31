package src.Registradores;
import java.util.HashMap;

public class BancoRegistradores {
	private HashMap<String, Registrador> bancoRegistradores; 

	public BancoRegistradores() {
		bancoRegistradores = new HashMap<>();
		bancoRegistradores.put("A", new Registrador("Acumulador", "A", "Armazena os dados (carregados e resultantes) das operações da Unid. de Lógica e Aritmética", "000000000000000010000000"));
		bancoRegistradores.put("X", new Registrador("Registrador de índice", "X", "Usado para endereçamento."));
		bancoRegistradores.put("L", new Registrador("Registrador de ligação", "L", "A instrução Jump to Subrotine (JSUB) armazena o endereço de retorno nesse registrador."));
		bancoRegistradores.put("B", new Registrador("Registrador base", "B", "Usado para endereçamento."));
		bancoRegistradores.put("S", new Registrador("Registrador de uso geral", "S", "Uso geral"));
		bancoRegistradores.put("T", new Registrador("Registrador de uso geral", "T", "Uso geral"));
		// bancoRegistradores.put("F", new Registrador("Acumulador de ponto flutuante", "F", "Armazena os dados (carregados e resultantes) das operações da Unid. de Lógica e Aritmética em ponto flutuante"));
		bancoRegistradores.put("PC", new Registrador("Contador de Instruções", "PC", "Mantém o endereço da próxima instrução a ser executada"));
		bancoRegistradores.put("W", new Registrador("Palavra de status", "W", "Contém várias informações, incluindo código condicional (CC)"));
	}

	public HashMap<String, Registrador> getConjuntoRegistradores() {
		return bancoRegistradores;
	}

	public Registrador getRegistrador(String identificador){
		return bancoRegistradores.get(identificador);
	}

	public static String getNumeroRegistrador(String identificador){
		switch (identificador) {
			case "A":
				return "0";

			case "X":
				return "1";

			case "L":
				return "2";

			case "B":
				return "3";
		
			case "S":
				return "4";

			case "T":
				return "5";

			case "PC":
				return "8";

			case "SW":
				return "9";

			default:
				break;
		}

		return "";
	}

	public void imprimirValoresRegistradores() {
		System.out.println("Valores nos Registradores:");
		for (Registrador registrador : bancoRegistradores.values()) {
			registrador.imprimirValorRegistrador();
		}
	}
}