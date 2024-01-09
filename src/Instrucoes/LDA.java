package src.Instrucoes;

import src.Maquina.Endereco;
import src.Maquina.Memoria;
import src.Registradores.BancoRegistradores;
import src.Registradores.Registrador;
import src.Interface.Controller;

/*******
 * LDA *
 *******/
public class LDA {    
    public static void executar(String nixbpq, Endereco endereco, BancoRegistradores registradores, Memoria memoria, Controller controller){
        Registrador regA = registradores.getRegistrador("A");

        controller.handleTERMINAL("A instrucao é: "+endereco.getPalavra().getInsHexa()+".");
        controller.handleTERMINAL("Já que o opcode é = "+endereco.getPalavra().getOpcode()+".");
        controller.handleTERMINAL("Essa instrucao tem indice: "+endereco.getIndice()+".");
        
        if(nixbpq.equals("010000")){
            controller.handleTERMINAL("A intruçao é LDA imediato, que insere o valor inteiro do binário "+endereco.getPalavra().getEnderecoBinario()+" no Registrador A.");
            
            String pegaDado = endereco.getPalavra().getEnderecoBinario();//copiou o endereço binário daquela palavra, que na verdade é um dado. 
            int dadoConvertido = Integer.parseInt(pegaDado, 2); // Converte para inteiro
            controller.handleTERMINAL("O valor no Registrador A será = "+dadoConvertido);
            regA.setNumeroInteiro((dadoConvertido));
        } else if (nixbpq.equals("110000")){
            controller.handleTERMINAL("A intruçao é LDA direto, que insere o valor que está no endereço "+endereco.getPalavra().getEnderecoBinario()+" no Registrador A.");
            
            String enderecoDesejado = endereco.getPalavra().getEnderecoBinario();
            int enderecoDesejadoInt = Integer.parseInt(enderecoDesejado, 2);
            
            for (Endereco enderecoComparado : memoria.getMemoria()) {
                if (enderecoComparado.getEndDeci() == enderecoDesejadoInt) {
                    // Quando encontra o endereço desejado, acessa o endereço e a instrução associada
                    String numeroBinario = enderecoComparado.getPalavra().getNumBin();
                    int numeroConvertido = Integer.parseInt(numeroBinario, 2);
                    controller.handleTERMINAL("O valor no endereço é = "+numeroConvertido);
                    regA.setNumeroInteiro(numeroConvertido);
                    break; // Sai do loop quando encontra o endereço
                }
            } 
        }
        
        controller.handleTERMINAL("O valor no Registrador A = "+regA.getNumeroInteiro()+"");
    }
}
