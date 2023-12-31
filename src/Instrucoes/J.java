package src.Instrucoes;

import src.Maquina.Endereco;
import src.Maquina.Memoria;
import src.Registradores.BancoRegistradores;
import src.Registradores.Registrador;

  
   
  
public class J {
    public static int executar(String nixbpq, Endereco endereco, BancoRegistradores registradores, Memoria memoria, int index){
        Registrador regPC = registradores.getRegistrador("PC");
        
        System.out.println("A intruçao é J imediato, que transfere o valor binário "+endereco.getPalavra().getEnderecoBinario()+" para o Registrador PC.");
        
        String pegaDado = endereco.getPalavra().getEnderecoBinario();//copiou o endereço binário daquela palavra, que na verdade é um dado. 
        int dadoConvertido = Integer.parseInt(pegaDado, 2); // Converte para inteiro
        regPC.setNumeroInteiro(dadoConvertido);//seta o PC com o novo endereço.
        int enderecoDesejadoInt = regPC.getNumeroInteiro();
        
        if(nixbpq.equals("010000")){
          System.err.println("EDENRECO DESEJADO" + enderecoDesejadoInt);
          for (Endereco enderecoComparado : memoria.getMemoriaComInstrucoes()) {
              if (enderecoComparado.getEndDeci() == enderecoDesejadoInt) {
                  System.err.println("ENDERECO COMPARADO => " + enderecoComparado.getEndDeci());
                  // Quando encontra o endereço desejado ele encontrou a instrucao que o jump manda ir de volta ou pra frente.
                  System.out.println("O Registrador PC aponta para = " +regPC.getNumeroInteiro()); 
                  System.out.println("O i era: "+index+".");
                  System.out.println(enderecoComparado.getIndice());
                  index = (enderecoComparado.getIndice()-1);
                  System.out.println("O novo i será: "+index+".");
                  break; // Sai do loop quando encontra o endereço
              }
          } 
        } 
        
      return index;
    }
}
