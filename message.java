// package src.Montador;

// import java.io.BufferedReader;
// import java.io.FileReader;
// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.Collections;
// import java.util.HashMap;
// import java.util.List;

// import src.Instrucoes.Instrucao;
// import src.Maquina.Endereco;

// // MONTADOR

// /*
// 	Relocar endereços simbólicos por endereços numéricos.
// 	Relocar códigos de operações simbólicas por códigos de
// 	máquina.
// 	Reservar espaço de armazenagem para instruções e dados.
// 	Traduzir constantes para representação de máquina.
//             (pseudo ou normal)
// 	LABEL // OPERATION // OPERANDO 1 // OPERANDO 2
//  */

// public class Montador {
// 	private ArrayList<String> assemblyInput;
// 	private ArrayList<String> output;

// 	private HashMap<String, String> OPTAB; // tabela instrucoes
// 	private HashMap<String, String> POPTAB; // tabela pseudo-instrucoes
// 	private HashMap<String, Integer> SYMTAB;

// 	public Montador(String path){	
// 		assemblyInput = new ArrayList<>();
// 		output = new ArrayList<>();
// 		POPTAB = new HashMap<>();
// 		SYMTAB = new HashMap<>();
// 		OPTAB = new HashMap<>();
// 		incializaPseudoOperationTable();
// 		inicializaOperationsTable();
// 		carregarInput(path);
// 		primeiroStep();
// 		segundoStep();
// 	}

// 	public void carregarInput(String pathArquivo) {
// 		try (BufferedReader br = new BufferedReader(new FileReader(pathArquivo))) {
// 			String linha;

// 			while ((linha = br.readLine()) != null) {
// 				if (!linha.trim().isEmpty()) {
// 					assemblyInput.add(linha);
// 				}
// 			}
// 		} catch (IOException e) {
// 			e.printStackTrace();
// 		}
// 	}

// 	private void primeiroStep(){
// 		int LC = 0;
// 		String linha = assemblyInput.get(0);

// 		System.out.println("=================");
// 		String label = getLabel(linha);
// 		System.out.println(label);
// 		String opcode = getOpcode(linha);
// 		System.out.println(opcode);
// 		List<String> operandos = getOperandos(linha);
// 		System.out.println(operandos);
// 		System.out.println("=================");

// 		if (label != null && label.equals("START")) {
// 			LC = Integer.parseInt(opcode);
// 		} 

// 		for(int i = 1; i < assemblyInput.size(); i++){
// 			linha = assemblyInput.get(i);
// 			label = getLabel(linha);
// 			opcode = getOpcode(linha);
// 			operandos = getOperandos(linha);
			
// 			System.err.println("----------------------");
// 			System.out.println("LINHA => " + linha);
// 			System.err.println("LABEL => " + label);
// 			System.err.println("OPCODE => " + opcode);
// 			System.err.println("OPERANDOS => " + operandos);
// 			System.err.println("----------------------");

// 			if(opcode != null && opcode.equals("END")){ // pseudoinst end
// 				break;
// 			}

//             if (label != null) { //
// 				if (SYMTAB.containsKey(label)) { // SE LABEL JÁ TIVER NA TABELA DE SIMBOLOS
// 					System.err.println("Erro: Label duplicado - " + label);
// 					return;
// 				} 

// 				SYMTAB.put(label, LC);
// 			}

//             if (opcode != null) {
// 				if (OPTAB.containsKey(opcode)) { // É INSTRUÇÃO 
// 					LC++;
// 				} else if (POPTAB.containsKey(opcode)) { // É PSEUDO-INSTRUÇÃO
// 					LC += calcularTamanhoPseudoInstrucao(opcode, operandos);
// 				} else {
// 					System.err.println("Err => Opcode desconhecido - " + opcode);
// 				}
// 			}
// 		}
// 	}

// 	private void segundoStep(){
        
// 	}

// 	private String tratarPseudoInstrucao(String opcode, List<String> operandos) {
// 		StringBuilder pseudoCodigo = new StringBuilder();
	
// 		switch (opcode) {
// 			case "WORD":
// 				for (String operando : operandos) {
// 					pseudoCodigo.append(Integer.toBinaryString(Integer.parseInt(operando)).toUpperCase());
// 				}
// 				break;
	
// 			case "BYTE":
// 				for (String operando : operandos) {
//                     if (operando.startsWith("C'")) { /
//                         for (int i = 2; i < operando.length() - 1; i++) {
//                             pseudoCodigo.append(Integer.toBinaryString(operando.charAt(i)).toUpperCase());
//                         }
//                     } else if (operando.startsWith("X'")) { // Tratando constantes hexadecimais
//                         pseudoCodigo.append(operando.substring(2, operando.length() - 1).toUpperCase());
//                     } else { // Tratando constantes numéricas
//                         pseudoCodigo.append(Integer.toBinaryString(Integer.parseInt(operando)).toUpperCase());
//                     }
// 				}
// 				break;
	
// 			case "RESW":
// 			case "RESB":
// 				int count = Integer.parseInt(operandos.get(0)) * (opcode.equals("RESW") ? 3 : 1);
// 				for (int i = 0; i < count; i++) {
// 					pseudoCodigo.append("0");
// 				}
// 				break;
	
	
// 			default:
// 				pseudoCodigo.append("Erro: Pseudo-instrução desconhecida - ").append(opcode);
// 				break;
// 		}
	
// 		return pseudoCodigo.toString();
// 	}

// 	private String traduzirInstrucaoParaCodigoMaquina(String opcode, List<String> operandos) {
//         StringBuilder codigoMaquina = new StringBuilder();
    
//         // Obtém o código de máquina para o opcode
//         // String codigoOpcode = OPTAB.get(opcode);
//         // codigoMaquina.append(codigoOpcode);
    
//         // Processa cada operando
//         for (String operando : operandos) {
//             String codigoOperando;
            
//             if (SYMTAB.containsKey(operando)) {
//                 int endereco = SYMTAB.get(operando);
//                 codigoOperando = Integer.toBinaryString(endereco);
//             } else {
//                 codigoOperando = converterOperandoParaCodigoMaquina(operando);
//             }
//             codigoMaquina.append(" ").append(codigoOperando);
//         }
    
//         return codigoMaquina.toString();
//     }
    
//     private String converterOperandoParaCodigoMaquina(String operando) {
//         try {
//             int valor = Integer.parseInt(operando);
//             return Integer.toBinaryString(valor);
//         } catch (NumberFormatException e) {
//             return operando;
//         }
//     }

// 	private int calcularTamanhoPseudoInstrucao(String opcode, List<String> operandos) {
// 		int tamanho = 0;
// 		if ("WORD".equals(opcode)) {
// 				tamanho = 3;
// 		} else if ("RESW".equals(opcode)) {
// 				tamanho = Integer.parseInt(operandos.get(0)) * 3;
// 		} else if ("RESB".equals(opcode)) {
// 				tamanho = Integer.parseInt(operandos.get(0));
// 		} else if ("BYTE".equals(opcode)) {
// 				tamanho++; 
// 		}
// 		return tamanho;
// 	}

// 	private String getOpcode(String linha) {
// 		linha = linha.split(";")[0].trim();

// 		String[] partes = linha.split("\\s+");

// 		if (partes.length == 0 || partes.length == 1) {
// 				return null;
// 		}

// 		String possivelOpcode = partes[0];
// 		if (OPTAB.containsKey(possivelOpcode)) {
// 				return possivelOpcode;
// 		} else {
// 				return partes.length > 1 ? partes[1] : null;
// 		}
// 	}

// 	private List<String> getOperandos(String linha) {
//     List<String> operandos = new ArrayList<>();

//     linha = linha.split(";")[0].trim();

//     String[] partes = linha.split("\\s+");

//     int inicioOperandos = 0;
//     if (partes.length > 1) {
//         if (OPTAB.containsKey(partes[0])) {
//             inicioOperandos = 1; 
//         } else {
//             inicioOperandos = 2;
//         }
//     }

//     for (int i = inicioOperandos; i < partes.length; i++) {
//         String[] ops = partes[i].split(",");
//         Collections.addAll(operandos, ops);
//     }

//     return operandos;
// }
	
// 	private String getLabel(String linha){
//     linha = linha.split(";")[0].trim();

//     String[] partes = linha.split("\\s+");

//     if (partes.length == 0) {
//         return null;
//     }

//     // Verificar se a primeira parte é um opcode conhecido
//     String primeiraParte = partes[0];
//     if (OPTAB.containsKey(primeiraParte)) {
//         // A primeira parte é um opcode, então não há rótulo
//         return null;
//     }

//     // A primeira parte não é um opcode, então é um rótulo
//     return primeiraParte;
// 	}

// 	private void inicializaOperationsTable(){
// 		OPTAB.put("LDA", "0");
// 		OPTAB.put("LDB", "68");
// 		OPTAB.put("LDL", "8");
// 		OPTAB.put("LDS", "6C");
// 		OPTAB.put("LDT", "74");
// 		OPTAB.put("ADD", "18");
// 		OPTAB.put("SUB", "1C");
// 		OPTAB.put("DIV", "24");
// 		OPTAB.put("MUL", "20");
// 		OPTAB.put("MULR", "98");
// 		OPTAB.put("DIVR", "9C");
// 		OPTAB.put("ADDR", "90");
// 		OPTAB.put("SUBR", "94");
// 		OPTAB.put("RMO", "AC");
// 		OPTAB.put("J", "3C");
// 		OPTAB.put("STA", "0C");
// 		OPTAB.put("CLEAR", "4");
// 	}

// 	public void incializaPseudoOperationTable(){
// 		POPTAB.put("START", "0");
// 		POPTAB.put("END", "0");
// 		POPTAB.put("BYTE", "0");
// 		POPTAB.put("RESW", "0");
// 		POPTAB.put("RESB",  "0");
// 		POPTAB.put("WORD", "0");
// 	}
// }
