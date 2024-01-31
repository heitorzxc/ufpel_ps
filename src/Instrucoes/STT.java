package src.Instrucoes;

import src.Maquina.*;
import src.Registradores.*;


/*******
 * STT *
 *******/
public class STT {
    public static void executar(String nixbpq, Endereco endereco, BancoRegistradores registradores, Memoria memoria){

        System.out.println("A instrucao é: "+endereco.getPalavra().getInsHexa()+".");
        System.out.println("Já que o opcode é = "+endereco.getPalavra().getOpcode()+".");
        System.out.println("Essa instrucao tem indice: "+endereco.getIndice()+".");

        Registrador reg = registradores.getRegistrador("T");
        String enderecoDesejado = endereco.getPalavra().getEnderecoBinario();
        int enderecoDesejadoInt = Integer.parseInt(enderecoDesejado, 2);
        
        String valorRegistrador = String.format("%24s", Integer.toBinaryString(reg.getNumeroInteiro())).replace(' ', '0');
        valorRegistrador = String.format("%24s", valorRegistrador).replace(' ', '0').substring(valorRegistrador.length() - 24);
        
        Instrucao salvarInstrucao = endereco.getPalavra();

        if(nixbpq.equals("010000")){
            System.out.println("A intruçao é ST"+reg.getNome()+" imediato: O endereço atual recebe o valor armazenado no Registrador "+reg.getNome()+".");
			System.out.println("O valor no Registrador "+reg.getNome()+" será = "+reg.getNumeroInteiro());
            salvarInstrucao.setNumBin(valorRegistrador);

        }else if(nixbpq.equals("110000")){  
            System.out.println("A intruçao é ST"+reg.getNome()+" direto.");

            for (Endereco enderecoComparado : memoria.getMemoria()) {
                if (enderecoComparado.getEndDeci() == enderecoDesejadoInt) {             
                    salvarInstrucao = enderecoComparado.getPalavra();
                    salvarInstrucao.setNumBin(valorRegistrador);
        
                    System.out.println("Instrucao modificada no endereço " + enderecoDesejado + ": " + salvarInstrucao.getNumBin() +" em inteiro e "+reg.getNumeroInteiro()+".");
                    break; 
                }
            }  
        }

    }
    
}
