package src.Maquina;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import src.Instrucoes.ADD;
import src.Instrucoes.ADDR;
import src.Instrucoes.CLEAR;
import src.Instrucoes.DIV;
import src.Instrucoes.DIVR;
import src.Instrucoes.Instrucao;
import src.Instrucoes.LDA;
import src.Instrucoes.LDB;
import src.Instrucoes.LDL;
import src.Instrucoes.LDS;
import src.Instrucoes.LDT;
import src.Instrucoes.MUL;
import src.Instrucoes.MULR;
import src.Instrucoes.RMO;
import src.Instrucoes.STA;
import src.Instrucoes.SUB;
import src.Instrucoes.SUBR;
import src.Registradores.BancoRegistradores;
import src.Registradores.Registrador;

public class Maquina {
    public BancoRegistradores registradores;
    public Memoria memoria;
    
    public Maquina(){
        this.memoria = new Memoria();
        this.registradores = new BancoRegistradores();
    }
    
    
       // private void carregarInstrucoes(String arquivo) {
    //     try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
    //         String linha;
    //         int endereco = 0;

    //         while ((linha = br.readLine()) != null) {
    //             Instrucao instrucao = new Instrucao(linha.trim());

    //             Endereco enderecoAtual = new Endereco(Integer.toString(endereco));
    //             enderecoAtual.setPalavra(instrucao);

    //             memoria.add(enderecoAtual);

    //             // Aumenta o endereço conforme o tamanho da instrução em bits
    //             endereco += instrucao.getNumBin().length();
    //         }
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }
    
    
    public void carregarInstrucoes(String pathArquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(pathArquivo))) {
            String linha;
            int endereco = 0;
            int posMemoria = 0;

            while ((linha = br.readLine()) != null) {
                Instrucao instrucao = new Instrucao(linha.trim());
                Endereco enderecoAtual = new Endereco(Integer.toString(endereco));
                
                enderecoAtual.setPalavra(instrucao);
                // memoria.add(enderecoAtual);
                memoria.setPosicaoMemoria(posMemoria, enderecoAtual);

                // Aumenta o endereço conforme o tamanho da instrução em bits
                endereco += instrucao.getNumBin().length();
                posMemoria++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void executarPrograma(){
        memoria.imprimirMemoria();
        
        
        Registrador regS = registradores.getRegistrador("S");
        regS.setNumeroInteiro(2);
        
        Registrador regT = registradores.getRegistrador("T");
        regT.setNumeroInteiro(32);
        
        Registrador regB = registradores.getRegistrador("B");
        regB.setNumeroInteiro(1);
        
        List<Endereco> listaInstrucoes = memoria.getMemoriaComInstrucoes();     
        
        for(int i = 0; i < listaInstrucoes.size(); i++){
            Endereco endereco = listaInstrucoes.get(i);
            Instrucao instrucao = endereco.getPalavra();
            
            if (instrucao.getNumBin().equals("11110100")){
                break;
            }
            
            switch(instrucao.getOpcode()){
                case "0":
                    LDA.executar(instrucao.getNixbpq(), endereco, registradores, memoria);
                case "68":
                    LDB.executar(instrucao.getNixbpq(), endereco, registradores, memoria);
                case "8":
                    LDL.executar(instrucao.getNixbpq(), endereco, registradores, memoria);
                case "6C":
                    LDS.executar(instrucao.getNixbpq(), endereco, registradores, memoria);
                case "74":
                    LDT.executar(instrucao.getNixbpq(), endereco, registradores, memoria);
                case "18":
                    ADD.executar(instrucao.getNixbpq(), endereco, registradores, memoria);
                case "1C":
                    SUB.executar(instrucao.getNixbpq(), endereco, registradores, memoria);
                case "24":
                    DIV.executar(instrucao.getNixbpq(), endereco, registradores, memoria);
                case "20":
                    MUL.executar(instrucao.getNixbpq(), endereco, registradores, memoria);
                case "98":
                    MULR.executar(instrucao.getEnderecoBinario(), endereco, registradores, memoria);
                case "9C":
                    DIVR.executar(instrucao.getEnderecoBinario(), endereco, registradores, memoria);
                case "90":
                    ADDR.executar(instrucao.getEnderecoBinario(), endereco, registradores, memoria);
                case "94":
                    SUBR.executar(instrucao.getEnderecoBinario(), endereco, registradores, memoria);
                case "AC":
                    RMO.executar(instrucao.getEnderecoBinario(), endereco, registradores, memoria);
                case "3C":
                    if (instrucao.getNixbpq().equals("010000")){//endereçamento imediato e palavra de 32 bits
                        Registrador regPC = registradores.getRegistrador("PC");
                        
                        System.out.println("A intruçao é J imediato, que transfere o valor binário "+endereco.getPalavra().getEnderecoBinario()+" para o Registrador PC.");
                        String pegaDado = endereco.getPalavra().getEnderecoBinario();//copiou o endereço binário daquela palavra, que na verdade é um dado. 
                        int dadoConvertido = Integer.parseInt(pegaDado, 2); // Converte para inteiro
                        regPC.setNumeroInteiro(dadoConvertido);//seta o PC com o novo endereço.
                        int enderecoDesejadoInt = regPC.getNumeroInteiro();
                        // Percorre a lista de endereços na memória
                        for (Endereco enderecoComparado : memoria.getMemoria()) {
                            if (enderecoComparado.getEndDeci() == enderecoDesejadoInt) {
                                // Quando encontra o endereço desejado ele encontrou a instrucao que o jump manda ir de volta ou pra frente.
                                System.out.println("O Registrador PC aponta para = " +regPC.getNumeroInteiro()); 
                                System.out.println("O i era: "+i+".");
                                i = (enderecoComparado.getIndice()-1);
                                System.out.println("O novo i será: "+i+".");
                                break; // Sai do loop quando encontra o endereço
                            }
                        } 
                    }
                case "0C":
                    STA.executar(instrucao.getNixbpq(), endereco, registradores, memoria);
                case "4":
                    CLEAR.executar(instrucao.getNixbpq(), endereco, registradores, memoria);
                
            }
        }
        
    }
   
}
    