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
import src.Instrucoes.Instrucoes;
import src.Registradores.BancoRegistradores;
import src.Utils.Arquivos;

public class Montador {
	private HashMap<String, Integer> tabelaSimbolos = new HashMap<>();
	private ArrayList<String> code = new ArrayList<>();
	private ArrayList<String> codeSemLabels = new ArrayList<>();
	private ArrayList<String> binaryCode = new ArrayList<>();
	private Instrucoes instrucoes = new Instrucoes();
	private String srcArquivo;
	private String dstArquivo;
	private boolean retornarCode;

	public Montador(String nomeArquivoFonte, String nomeArquivoDestino) {
		this.srcArquivo = nomeArquivoFonte;
		this.dstArquivo = nomeArquivoDestino;
		this.retornarCode = false;
	}

	public Montador(String nomeArquivoFonte) {
		this.srcArquivo = nomeArquivoFonte;
		this.dstArquivo = "";
		this.retornarCode = true;
	}

	public ArrayList<String> executar() throws FileNotFoundException {
		try {
			code = Arquivos.lerArquivo(this.srcArquivo);

			System.out.println("\n\n leitura do codigo: \n");
			for (String linha : code)
				System.out.println(linha);

			// primeiraEtapa(code);

			// System.out.println("\n\n primeira Etapa: \n");
			// for (String key : tabelaSimbolos.keySet())
			// System.out.println(key + ": " + tabelaSimbolos.get(key));

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// removeLabels(code);
		// System.out.println("\n\n codigo sem os labels: \n");
		// for (String linha : codeSemLabels)
		// System.out.println(linha);

		// segundaEtapa(codeSemLabels);

		// try {
		// salvaArquivo(nomeArquivoDestino);
		// } catch (Exception e) {
		// System.out.println(e.getMessage());
		// }

		// Arquivos.salvaArquivo(dstArquivo == null ? dstArquivo : "./a.txt",
		// binaryCode);

		return code;
	}

	private int getTamanhoOperacao(String linha) {
		String[] t = linha.trim().split(":");
		Integer p = t.length == 1 ? 0 : 1;

		return linha.split("\\s+").length - p;
	}

	private void primeiraEtapa(List<String> code) throws Exception {
		//// percorrer a primeira vez o codigo para pegar os endereços
		int addrs = 0;

		for (String linha : code) {
			String label = getLabel(linha);

			Integer tamanhoOperacao = getTamanhoOperacao(linha);

			if (!label.equals("")) { // verifica se tem label
				if (tabelaSimbolos.containsKey(label)) {
					throw new Exception("Label repetido: " + label + " !");
				}
				tabelaSimbolos.put(label, addrs); // adiciona o label na tabela de simbolos
			}
			addrs += tamanhoOperacao;
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

	public String toBin(String number, int width) {
		String binary = Integer.toBinaryString(Integer.parseInt(number));
		return String.format("%" + width + "s", binary).replace(' ', '0');
	}

	private void segundaEtapa(List<String> code) throws RegisterIdenfierError, IvalidInstructionFormatError {
		for (Integer linha = 0; linha < code.size(); ++linha) {
			String instru = code.get(linha);

			if (!Instrucoes.isValida(instru)) {
				throw new IvalidInstructionFormatError(
						"Instrucao: " + instru + "\n na linha: " + linha + " é inválida.");
			}

			String opcode = Instrucoes.getOpcode(instru);
			String[] operandos = Instrucoes.getOperandos(instru);
			String nixbpe = Instrucoes.getNixbpe(instru);

			StringBuilder operacaoBinario = new StringBuilder();
			String operandosBinario = new String("");

			operacaoBinario.append(getBinarioOpcode(opcode));

			if (operandos.length == 1) {
				String oprnd = verificaTabelaDeSimbolos(operandos[0]);

				nixbpe = Instrucoes.getNixbpe(opcode, operandos);

				operandosBinario = toBin(getNumero(oprnd), 12);
			}

			else if (operandos.length == 2) {
				String r1 = verificaTabelaDeSimbolos(operandos[0]);
				String r2 = verificaTabelaDeSimbolos(operandos[1]);

				try {
					r1 = toBin(getNumero(r1), 4);
					r2 = toBin(getNumero(r2), 4);

				} catch (Exception e) {
					System.out.println("deu erro na linha " + linha);
				}

				operandosBinario = r1 + r2;
			}

			operacaoBinario.append(nixbpe);
			operacaoBinario.append(operandosBinario);

			binaryCode.add(operacaoBinario.toString());
		}

	}

	private String verificaTabelaDeSimbolos(String op) throws RegisterIdenfierError {
		if (tabelaSimbolos.containsKey(op)) {
			return tabelaSimbolos.get(op).toString();
		}

		String reg = BancoRegistradores.getNumeroRegistrador(op);

		if (!reg.equals("")) {
			return reg;
		}

		return op;

	}

	private String getNumero(String numero) {
		String n = "";
		switch (verificaEnderecamento(numero)) {
			case "imediato":
			case "indireto":
				n = numero.substring(1);
				break;
			case "direto":
				n = numero;
				break;

			default:

				break;
		}

		return n;

	}

	private String getBinarioOpcode(String nomeInstrucao) {
		String cod;
		if (nomeInstrucao.equals("J")) {
			cod = "3C";
		} else if (nomeInstrucao.equals("WORD")) {
			cod = "AA";
		} else if (nomeInstrucao.equals("RESW")) {
			cod = "AB";
		} else {
			cod = instrucoes.getOpcodePorNome(nomeInstrucao);
		}

		int i = Integer.parseInt(cod, 16);
		return toBin(Integer.toString(i), 8);
	}

	private String verificaEnderecamento(String numero) {
		if (numero.charAt(0) == '@') {
			return "indireto";
		}

		if (numero.charAt(0) == '#') {
			return "imediato";
		}

		return "direto";
	}

	private String getLabel(String linha) {
		List<String> splitedLine = new ArrayList<String>(Arrays.asList(linha.trim().split(" ")));
		// int lengthOfOperation = splitedLine.size();

		String possivelLabel = splitedLine.get(0).trim();

		if (possivelLabel.endsWith(":")) {
			return possivelLabel.substring(0, possivelLabel.length() - 1);
		}

		return "";
	}
}
