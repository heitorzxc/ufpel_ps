package src.Instrucoes;

import src.Maquina.Endereco;
import src.Maquina.Memoria;
import src.Registradores.BancoRegistradores;
import src.Registradores.Registrador;
import src.Interface.Controller;

/*******
 * ADD *
 *******/
public class MUL {
    public static void executar(String nixbpq, Endereco endereco, BancoRegistradores registradores, Memoria memoria, Controller controller){
        Registrador regA = registradores.getRegistrador("A");
        
        if(nixbpq.equals("010000")){
          controller.handleTERMINAL("A intruçao é MUL imediato, que multiplica o acumulador com o valor inteiro do binário "+endereco.getPalavra().getEnderecoBinario());
          String pegaDado = endereco.getPalavra().getEnderecoBinario();//copiou o endereço binário daquela palavra, que na verdade é um dado. 
          int dadoConvertido = Integer.parseInt(pegaDado, 2); // Converte para inteiro
          regA.setNumeroInteiro((regA.getNumeroInteiro()*dadoConvertido));//multiplica pelo valor
          controller.handleTERMINAL("O valor no Registrador A = "+regA.getNumeroInteiro()+"\n");
        }    
    }
}
