package src.Montador;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import src.Exceptions.IvalidInstructionFormatError;
import src.Exceptions.RegisterIdenfierError;
import src.Instrucoes.Instrucao;
import src.Instrucoes.Instrucoes;
import src.Registradores.BancoRegistradores;
import src.Utils.Arquivos;
import src.Utils.Conversao;

// START -> Specify name and starting address for the program.
// END -> Indicate the end of the source program and (optionally) specify the
// first executable instruction in the program.
// BYTE -> Generate character or hexadecimal constant, occupying as many
// bytes as needed to represent the constant.
// WORD -> Generate one-word integer constant.
// RESB -> Reserve the indicated number of bytes for a data area.
// RESW -> Reserve the indicated number of words for a data area.

public class MontadorAntigo {
	private HashMap<String, Integer> tabelaSimbolos = new HashMap<>();

	private HashMap<String, String> pseudoIntrucoes = new HashMap<>();

	private ArrayList<String> code = new ArrayList<>();
	private ArrayList<String> codeSemLabels = new ArrayList<>();
	private ArrayList<String> binaryCode = new ArrayList<>();
	private String srcArquivo;
	private String dstArquivo;

	public MontadorAntigo(String nomeArquivoFonte) {
		this.srcArquivo = nomeArquivoFonte;
		this.dstArquivo = "../../resources/resultados/saida.asm";

		pseudoIntrucoes.put("START", "");
		pseudoIntrucoes.put("END", "11110100");
		pseudoIntrucoes.put("BYTE", "");
		pseudoIntrucoes.put("WORD", "");
		pseudoIntrucoes.put("RESB", "");
		pseudoIntrucoes.put("RESW", "");

		tabelaSimbolos.put("A", 0);
		tabelaSimbolos.put("B", 3);
		tabelaSimbolos.put("L", 2);
		tabelaSimbolos.put("PC", 8);
		tabelaSimbolos.put("S", 4);
		tabelaSimbolos.put("SW", 9);
		tabelaSimbolos.put("T", 5);
		tabelaSimbolos.put("X", 1);

	}

	public ArrayList<String> executar()
			throws FileNotFoundException, RegisterIdenfierError,
			IvalidInstructionFormatError {
		try {
			code = Arquivos.lerArquivo(this.srcArquivo);

			// System.out.println("\n\n leitura do codigo: \n");
			// for (String linha : code)
			// System.out.println(linha);

			primeiraEtapa(code);

			// System.out.println("\n\n primeira Etapa: \n");
			// for (String key : tabelaSimbolos.keySet())
			// System.out.println(key + ": " + tabelaSimbolos.get(key));

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		removeLabels(code);
		// System.out.println("\n\n codigo sem os labels: \n");
		// for (String linha : codeSemLabels)
		// System.out.println(linha);

		segundaEtapa(codeSemLabels);

		try {
			Arquivos.salvaArquivo(binaryCode);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// Arquivos.salvaArquivo(dstArquivo == null ? dstArquivo : "./a.txt",
		// binaryCode);

		return code;
	}

	// private int getTamanhoOperacao(String linha) {
	// String[] t = linha.trim().split(":");

	// String[] op = t[1].split(" ");

	// if (pseudoIntrucoes.get(op[0]))
	// return 1;

	// return linha.split("\\s+").length;
	// }

	private void primeiraEtapa(List<String> code) throws Exception {
		//// percorrer a primeira vez o codigo para pegar os endereços
		int addrs = 0;

		for (String linha : code) {
			String label = getLabel(linha);

			if (!label.equals("")) { // verifica se tem label
				if (tabelaSimbolos.containsKey(label)) {
					throw new Exception("Label repetido: " + label + " !");
				}

				tabelaSimbolos.put(label, addrs);
			}

			addrs += 1;
		}

	}

	public void removeLabels(ArrayList<String> code) {
		for (String linha : code) {
			int index = linha.indexOf(":");

			if (index == -1) {
				codeSemLabels.add(linha);
				continue;
			}

			codeSemLabels.add(linha.substring(index + 1).trim());
		}
	}

	private void segundaEtapa(List<String> code) throws RegisterIdenfierError,
			IvalidInstructionFormatError {

		for (Integer linha = 0; linha < code.size(); ++linha) {
			String instru = code.get(linha);

			String opcode = getOpcode(instru);

			if (!isValida(instru)) {
				throw new IvalidInstructionFormatError(
						"Instrucao: " + instru + "\n linha: " + linha);
			}

			String[] operandos = getOperandos(instru);

			StringBuilder operacaoBinario = new StringBuilder();

			if (pseudoIntrucoes.containsKey(opcode)) {
				if (opcode.equals("END"))
					operacaoBinario.append(pseudoIntrucoes.get(opcode));
				else
					operacaoBinario.append(Conversao.intToBin(operandos[0], 24));
			}

			else if (operandos.length == 1) {
				String opbin = getBinarioOpcode(opcode).substring(0, 6);
				System.out.println("opcode 1: " + opcode + "  " + opbin);
				String oprnd = verificaTabelaDeSimbolos(operandos[0]);

				int size = 12;
				String nixbpe = getNixbpe(opcode, operandos);

				if (nixbpe.charAt(5) == '1')
					size += 8;

				operacaoBinario.append(opbin);
				operacaoBinario.append(nixbpe);
				operacaoBinario.append(Conversao.intToBin(oprnd, size));
			}

			else if (operandos.length == 2) {
				String opbin = getBinarioOpcode(opcode);
				System.out.println("opcode 2: " + opcode + opbin);

				String op1 = verificaTabelaDeSimbolos(operandos[0]);
				String op2 = verificaTabelaDeSimbolos(operandos[1]);

				if (op1 == null || op2 == null) {
					System.out.println("deu erro");
					break;
				}

				op1 = Conversao.intToBin(op1, 4);

				op2 = Conversao.intToBin(op2, 4);

				String operandosBinario = op1 + op2;
				operacaoBinario.append(opbin);
				operacaoBinario.append(operandosBinario);
			}

			// String t = operacaoBinario.toString();
			// System.out.println(t + " " + t.length());

			binaryCode.add(operacaoBinario.toString());
		}

	}

	private String verificaTabelaDeSimbolos(String op) throws RegisterIdenfierError {
		String oprnd = op;

		if (op.startsWith("#") || op.startsWith("@"))
			oprnd = op.substring(1);

		if (tabelaSimbolos.containsKey(oprnd)) {
			return tabelaSimbolos.get(oprnd).toString();
		}

		return oprnd;
	}

	private String getNixbpe(String opcode, String[] operandos) {
		boolean n = false, i = false, x = false, b = false, p = false, e = false;

		// Verifica se a instrução é no formato estendido
		if (opcode.startsWith("+")) {
			e = true;
		}

		if (operandos.length > 0) {
			String operando = operandos[0];

			// Verifica o tipo de endereçamento pelo primeiro caractere do operando
			switch (operando.charAt(0)) {
				case '#': // Imediato
					i = true;
					break;
				case '@': // Indireto
					n = true;
					break;
				default: // Direto
					n = true;
					i = true;
					break;
			}

			// Verifica se a indexação é utilizada
			if (operando.contains(",X") || operando.contains(",x")) {
				x = true;
			}
		}

		return (n ? "1" : "0") +
				(i ? "1" : "0") +
				(x ? "1" : "0") +
				(b ? "1" : "0") +
				(p ? "1" : "0") +
				(e ? "1" : "0");
	}

	private String getLabel(String linha) {
		String[] splitedLine = linha.trim().split(" ");

		String possivelLabel = splitedLine[0].trim();

		if (possivelLabel.endsWith(":")) {
			return possivelLabel.substring(0, possivelLabel.length() - 1);
		}

		return "";
	}

	private String getOpcode(String linha) {
		String[] partes = linha.split(" ");
		String opcode = partes[0];

		if (opcode.startsWith("+"))
			return opcode.substring(1);

		return opcode;
	}

	private String[] getOperandos(String linha) {
		String[] parts = linha.trim().split("\\s+");
		if (parts.length > 0) {
			return Arrays.copyOfRange(parts, 1, parts.length);
		}
		return new String[0]; // Retorna um array vazio caso não haja operandos
	}

	private String getBinarioOpcode(String opcode) {
		String op = Instrucoes.getOpcodePorNome(opcode);

		if (op == null)
			return "";

		op = Conversao.hexToBin(op);

		return Conversao.expandeBinario(op, 8);
	}

	private Boolean isValida(String code) {
		String opcode = getOpcode(code);

		Instrucao operacao = Instrucoes.getInstrucaoPorNome(opcode); // "valida" a operacao indeiretamente

		if (operacao == null)
			return pseudoIntrucoes.containsKey(opcode);

		String[] operandos = getOperandos(code);

		return operandos.length == operacao.getNumeroDeOperandos(); // valida o numero de operandos
	}
}
