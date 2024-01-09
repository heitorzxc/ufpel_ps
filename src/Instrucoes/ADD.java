package src.Instrucoes;

import src.Maquina.Endereco;
import src.Maquina.Memoria;
import src.Registradores.BancoRegistradores;
import src.Registradores.Registrador;
import src.Interface.Controller;
/*******
 * ADD *
 *******/
public class ADD {
    public static void executar(String nixbpq, Endereco endereco, BancoRegistradores registradores, Memoria memoria, Controller controller){
        Registrador regA = registradores.getRegistrador("A");
        
        if(nixbpq.equals("010001")){
          controller.handleTERMINAL("A intruçao é ADD imediato, que soma o valor binário "+endereco.getPalavra().getEnderecoBinario()+" no acumulador (Registrador A) como se fosse um dado.");
          
          String pegaDado = endereco.getPalavra().getEnderecoBinario();//copiou o endereço binário daquela palavra, que na verdade é um dado. 
          int dadoConvertido = Integer.parseInt(pegaDado, 2); // Converte para inteiro
          regA.setNumeroInteiro((dadoConvertido+regA.getNumeroInteiro()));
        } else if (nixbpq.equals("110001")){
          controller.handleTERMINAL("A intruçao é ADD direto, que soma o valor no endereço "+endereco.getPalavra().getEnderecoBinario()+" no acumulador (Registrador A).");
          
          String enderecoDesejado = endereco.getPalavra().getEnderecoBinario();
          int enderecoDesejadoInt = Integer.parseInt(enderecoDesejado, 2); // Converte para inteiro 
          
          for (Endereco enderecoComparado : memoria.getMemoria()) {
              if (enderecoComparado.getEndDeci() == enderecoDesejadoInt) {
                  // Quando encontra o endereço desejado, acessa o endereço e a instrução associada
                  String numeroBinario = enderecoComparado.getPalavra().getNumBin();
                  int numeroConvertido = Integer.parseInt(numeroBinario, 2);
                  controller.handleTERMINAL("O valor no endereço é = "+numeroConvertido);
                  regA.setNumeroInteiro((numeroConvertido+regA.getNumeroInteiro()));
                  break; // Sai do loop quando encontra o endereço
              }
          }            
        }
    }
}
