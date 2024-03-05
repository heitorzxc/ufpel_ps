package src.Montador;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import src.Instrucoes.Instrucoes;
import src.Registradores.BancoRegistradores;

public class Montador {
	private HashMap<String, Integer> tabelaSimbolos = new HashMap<>();
	private ArrayList<String> code = new ArrayList<>();
	private ArrayList<String> codeSemLabels = new ArrayList<>();
	private ArrayList<String> binaryCode = new ArrayList<>();
	private Instrucoes instrucoes = new Instrucoes();

    public Montador(String nomeArquivoFonte, String nomeArquivoDestino) {
		try {
			leArquivo(nomeArquivoFonte);
			
			System.out.println("\n\n leitura do codigo: \n");
			for(String linha: code)
				System.out.println(linha);

			primeiraEtapa(code);

			System.out.println("\n\n primeira Etapa: \n");
			for(String key: tabelaSimbolos.keySet())
				System.out.println(key + ": " + tabelaSimbolos.get(key));

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
        
		removeLabels(code);
		System.out.println("\n\n codigo sem os labels: \n");
			for(String linha: codeSemLabels)
				System.out.println(linha);

		segundaEtapa(codeSemLabels);

		try {
			salvaArquivo(nomeArquivoDestino);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }

    public void leArquivo(String file) throws FileNotFoundException {
        try {
            File obj = new File(file);
            Scanner reader = new Scanner(obj);

            while (reader.hasNextLine()) {
                String data = reader.nextLine();

                if (!data.isEmpty()) { // Não adiciona linha vazias
                    String content = data.split(";")[0].replaceAll("\\s+", " ").trim();
                    code.add(content); // Adiciona a linha sem o comentário
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Arquivo não encontrado!");
        }
    }

    public void salvaArquivo(String nomeArquivo) throws FileNotFoundException{
        try{
			PrintWriter writer = new PrintWriter(nomeArquivo);
			
            for(String linha: binaryCode){
                writer.println(linha);
            }

            writer.close();
        }
        catch(Exception e){
            throw new FileNotFoundException("Arquivo não encontrado!");
        }

    }

	private int getTamanhoOperacao(String linha){
		String[] t = linha.trim().split(":");
		Integer p = t.length == 1 ? 0 : 1;

		return linha.split("\\s+").length - p;
	}

    private void primeiraEtapa(List<String> code) throws Exception{
        //// percorrer a primeira vez o codigo para pegar os endereços
        int addrs = 0;

        for(String linha: code){
			String label = getLabel(linha);

			Integer tamanhoOperacao = getTamanhoOperacao(linha);


            if(!label.equals("")){ // verifica se tem label
                if(tabelaSimbolos.containsKey(label)){
                    throw new Exception("Label repetido: " + label + " !");
                }
                tabelaSimbolos.put(label, addrs); // adiciona o label na tabela de simbolos
            }
			addrs += tamanhoOperacao;
        }

    }

    public void removeLabels(ArrayList<String> code){
        for(String linha: code){
            int index = linha.indexOf(":");

            if(index == -1){
				codeSemLabels.add(linha);	
				continue;
			}

            codeSemLabels.add(linha.substring(index+1).trim());
        }
    }

    public String toBin(String number, int width){
        String binary = Integer.toBinaryString(Integer.parseInt(number));
        return String.format("%" + width + "s", binary).replace(' ', '0');
    }

	private boolean isAddrs(String op){
		// verifica se é do tipo 4
		return op.charAt(0) == '+' ? true : false;
	}
   
	private void segundaEtapa(List<String> code){
		for(String linha: code){
			String opcode = getOpcode(linha);
			List<String> operandos = getOperandos(linha);
			StringBuilder nixbpe = new StringBuilder();

			StringBuilder operacaoBinario = new StringBuilder();
			String operandosBinario = new String("");
			
			operacaoBinario.append(getBinarioOpcode(opcode));

			if(operandos.size() == 1){
				String oprnd = verificaTabelaDeSimbolos(operandos.get(0));

				nixbpe = getNixbpe(opcode, operandos);

				operandosBinario = toBin(getNumero(oprnd), 12);
			}

			else if(operandos.size() == 2){
				String r1 = verificaTabelaDeSimbolos(operandos.get(0));
				String r2 = verificaTabelaDeSimbolos(operandos.get(1));

				try {
					r1 = toBin(getNumero(r1), 8);
					r2 = toBin(getNumero(r2), 8);
					
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

	private String verificaTabelaDeSimbolos(String op){
		if(tabelaSimbolos.containsKey(op)){
			return tabelaSimbolos.get(op).toString();
		}

		String reg = BancoRegistradores.getNumeroRegistrador(op);

		if(!reg.equals("")){
			return reg;
		}

		return op;

	}

	private String getNumero(String numero){
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

	private String getBinarioOpcode(String nomeInstrucao){
		String cod;
		if(nomeInstrucao.equals("J")){
			cod = "3C";
		} else if(nomeInstrucao.equals("WORD")){
			cod = "AA";
		} else if(nomeInstrucao.equals("RESW")){
			cod = "AB";
		} else {
			cod = instrucoes.getOpcodePorNome(nomeInstrucao);
		}
	
		int i = Integer.parseInt(cod, 16);
		return toBin(Integer.toString(i), 8);
	}

	private String verificaEnderecamento(String numero){
		if(numero.charAt(0) == '@'){
            return "indireto";
        } 
		
		if(numero.charAt(0) == '#'){
			return "imediato";
        } 
		
		return "direto";
	}

    private StringBuilder getNixbpe(String opcode, List<String> operandos){
        String firstOperando = operandos.get(0);
        StringBuilder nixbpe = new StringBuilder();
        
        switch (verificaEnderecamento(firstOperando)) {
			case "imediato":
                nixbpe.append("01000");
				break;
			case "indireto":
                nixbpe.append("01000");
                break;
		
			case "direto":
			    nixbpe.append("11000");
                break;

			default:
				break;
		}

        if(isAddrs(opcode)){
            nixbpe.append("1");     
        } else {
            nixbpe.append("0");
        }

        return nixbpe;
    }

    private String getOpcode(String linha) {
		String[] partes = linha.split("\\s+");

		if (partes.length == 0 || partes.length == 1) {
			return null;
		}

		String possivelOpcode = partes[0];
		// if (OPTAB.containsKey(possivelOpcode)) {
		// 	return possivelOpcode;
		// } else {
		// 	return partes.length > 1 ? partes[1] : null;
		// }

		return possivelOpcode;
	}

	private List<String> getOperandos(String linha) {
		List<String> operandos = new ArrayList<>();

		String[] partes = linha.split("\\s+");

		if(partes.length > 2) 
			for(int i = 1; i < partes.length; ++i)
				operandos.add(partes[i]);
		else
			operandos.add(partes[1]);
		
		return operandos;
		
	}
    
	private String getLabel(String linha){
		List<String> splitedLine = new ArrayList<String>(Arrays.asList(linha.trim().split(" ")));
		// int lengthOfOperation = splitedLine.size();

		String possivelLabel = splitedLine.get(0).trim();

		if(possivelLabel.endsWith(":")){
			return possivelLabel.substring(0, possivelLabel.length() - 1);
		}

		return "";
	}
}


