package src.Instrucoes;

import src.Maquina.Endereco;
import src.Maquina.Memoria;
import src.Registradores.BancoRegistradores;
import src.Registradores.Registrador;
import src.Interface.Controller;

  
   
  
public class J {
    public static int executar(String nixbpq, Endereco endereco, BancoRegistradores registradores, Memoria memoria, int index, Controller controller){
        Registrador regPC = registradores.getRegistrador("PC");
        
        controller.handleTERMINAL("A intruçao é J imediato, que transfere o valor binário "+endereco.getPalavra().getEnderecoBinario()+" para o Registrador PC.");
        
        String pegaDado = endereco.getPalavra().getEnderecoBinario();//copiou o endereço binário daquela palavra, que na verdade é um dado. 
        int dadoConvertido = Integer.parseInt(pegaDado, 2); // Converte para inteiro
        regPC.setNumeroInteiro(dadoConvertido);//seta o PC com o novo endereço.
        int enderecoDesejadoInt = regPC.getNumeroInteiro();
        
        if(nixbpq.equals("010000")){
          controller.handleTERMINAL("ENDERECO DESEJADO" + enderecoDesejadoInt);
          for (Endereco enderecoComparado : memoria.getMemoriaComInstrucoes()) {
              if (enderecoComparado.getEndDeci() == enderecoDesejadoInt) {
                  controller.handleTERMINAL("ENDERECO COMPARADO => " + enderecoComparado.getEndDeci());
                  // Quando encontra o endereço desejado ele encontrou a instrucao que o jump manda ir de volta ou pra frente.
                  controller.handleTERMINAL("O Registrador PC aponta para = " +regPC.getNumeroInteiro()); 
                  controller.handleTERMINAL("O i era: "+index+".");
                  controller.handleTERMINAL(Integer.toString(enderecoComparado.getIndice()));
                  index = (enderecoComparado.getIndice()-1);
                  controller.handleTERMINAL("O novo i será: "+index+".");
                  break; // Sai do loop quando encontra o endereço
              }
          } 
        } 
        
      return index;
    }
}
