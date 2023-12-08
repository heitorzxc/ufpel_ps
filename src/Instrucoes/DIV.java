package src.Instrucoes;

import src.Maquina.Endereco;
import src.Maquina.Memoria;
import src.Registradores.BancoRegistradores;
import src.Registradores.Registrador;

/*******
 * ADD *
 *******/
public class DIV {
    public static void executar(String nixbpq, Endereco endereco, BancoRegistradores registradores, Memoria memoria){
        Registrador regA = registradores.getRegistrador("A");
        
        if(nixbpq.equals("010000")){
          System.out.println("A intruçao é DIV imediato, que divide o acumulador com o valor inteiro do binário "+endereco.getPalavra().getEnderecoBinario());
          String pegaDado = endereco.getPalavra().getEnderecoBinario();//copiou o endereço binário daquela palavra, que na verdade é um dado. 
          int dadoConvertido = Integer.parseInt(pegaDado, 2); // Converte para inteiro
          regA.setNumeroInteiro((regA.getNumeroInteiro()/dadoConvertido));//divide pelo valor
          System.out.println("O valor no Registrador A = "+regA.getNumeroInteiro()+"\n");
        }    
    }
}
