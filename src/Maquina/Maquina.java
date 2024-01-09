package src.Maquina;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javafx.util.converter.IntegerStringConverter;
import src.Instrucoes.ADD;
import src.Instrucoes.ADDR;
import src.Instrucoes.CLEAR;
import src.Instrucoes.DIV;
import src.Instrucoes.DIVR;
import src.Instrucoes.Instrucao;
import src.Instrucoes.J;
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

import src.Interface.Controller;

public class Maquina {
    public BancoRegistradores registradores;
    public Memoria memoria;
    public Controller controller;
    
    public void setController(Controller controller){
        this.controller=controller;
        memoria.setController(controller);
    }
    public Controller getController(){
        return controller;
    }
    public Maquina(){
        this.memoria = new Memoria();
        this.registradores = new BancoRegistradores();
    }
    
   
   
    public void carregarInstrucoes(String pathArquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(pathArquivo))) {
            String linha;
            int endereco = 0;
            int posMemoria = 0;

            while ((linha = br.readLine()) != null) {
                Instrucao instrucao = new Instrucao(linha.trim());
                Endereco enderecoAtual = new Endereco(Integer.toString(endereco));
                
                enderecoAtual.setPalavra(instrucao);
                enderecoAtual.setIndice(posMemoria);
                memoria.setPosicaoMemoria(posMemoria, enderecoAtual);

                // Aumenta o endereço conforme o tamanho da instrução em bits
                endereco += instrucao.getNumBin().length();
                posMemoria++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void carregarInstrucoesDoTextField(String textoDoTextField) {
    
        String[] linhas = textoDoTextField.split("\n");
        int endereco = 0;
        int posMemoria = 0;
    
        for (String linha : linhas) {
            Instrucao instrucao = new Instrucao(linha.trim());
            Endereco enderecoAtual = new Endereco(Integer.toString(endereco));
    
            enderecoAtual.setPalavra(instrucao);
            enderecoAtual.setIndice(posMemoria);
            memoria.setPosicaoMemoria(posMemoria, enderecoAtual);
    
            // Aumenta o endereço conforme o tamanho da instrução em bits
            endereco += instrucao.getNumBin().length();
            posMemoria++;
        }
    }

    public void imprimirMemoria(){
        memoria.imprimirMemoria();
    }
    
    
    public void executarPrograma(){

        
        Registrador regS = registradores.getRegistrador("S");
        regS.setNumeroInteiro(2);
        
        Registrador regT = registradores.getRegistrador("T");
        regT.setNumeroInteiro(32);
        
        Registrador regB = registradores.getRegistrador("B");
        regB.setNumeroInteiro(1);
        
        List<Endereco> listaInstrucoes = memoria.getMemoriaComInstrucoes();     
        
       //System.err.println("QUANTIDADE INSTRUÇÕES => " + listaInstrucoes.size());
        controller.handleNumInst(Integer.toString(listaInstrucoes.size()));
        for(int i = 0; i < listaInstrucoes.size(); i++){
            Endereco endereco = listaInstrucoes.get(i);
            Instrucao instrucao = endereco.getPalavra();

            if (instrucao.getNumBin().equals("11110100")){
                break;
            }
            
            switch(instrucao.getOpcode()){
                case "0":
                    LDA.executar(instrucao.getNixbpq(), endereco, registradores, memoria, controller);
                    break;
                case "68":
                    LDB.executar(instrucao.getNixbpq(), endereco, registradores, memoria, controller);
                    break;
                case "8":
                    LDL.executar(instrucao.getNixbpq(), endereco, registradores, memoria, controller);
                    break;
                case "6C":
                    LDS.executar(instrucao.getNixbpq(), endereco, registradores, memoria, controller);
                    break;
                case "74":
                    LDT.executar(instrucao.getNixbpq(), endereco, registradores, memoria, controller);
                    break;
                case "18":
                    ADD.executar(instrucao.getNixbpq(), endereco, registradores, memoria, controller);
                    break;
                case "1C":
                    SUB.executar(instrucao.getNixbpq(), endereco, registradores, memoria, controller);
                    break;
                case "24":
                    DIV.executar(instrucao.getNixbpq(), endereco, registradores, memoria, controller);
                    break;
                case "20":
                    MUL.executar(instrucao.getNixbpq(), endereco, registradores, memoria, controller);
                    break;
                case "98":
                    MULR.executar(instrucao.getEnderecoBinario(), endereco, registradores, memoria, controller);
                    break;
                case "9C":
                    DIVR.executar(instrucao.getEnderecoBinario(), endereco, registradores, memoria, controller);
                    break;
                case "90":
                    ADDR.executar(instrucao.getEnderecoBinario(), endereco, registradores, memoria, controller);
                    break;
                case "94":
                    SUBR.executar(instrucao.getEnderecoBinario(), endereco, registradores, memoria, controller);
                    break;
                case "AC":
                    RMO.executar(instrucao.getEnderecoBinario(), endereco, registradores, memoria, controller);
                    break;
                case "3C":
                    i = J.executar(instrucao.getNixbpq(), endereco, registradores, memoria, i, controller);
                    break;
                case "0C":
                    STA.executar(instrucao.getNixbpq(), endereco, registradores, memoria, controller);
                    break;
                case "4":
                    CLEAR.executar(instrucao.getNixbpq(), endereco, registradores, memoria, controller);
                    break;
                
            }
        }
        
    }
   
     public void executarProgramaStep(int indice){

        
        Registrador regS = registradores.getRegistrador("S");
        regS.setNumeroInteiro(2);
        
        Registrador regT = registradores.getRegistrador("T");
        regT.setNumeroInteiro(32);
        
        Registrador regB = registradores.getRegistrador("B");
        regB.setNumeroInteiro(1);
        
        List<Endereco> listaInstrucoes = memoria.getMemoriaComInstrucoes();     
        
       //System.err.println("QUANTIDADE INSTRUÇÕES => " + listaInstrucoes.size());
        controller.handleNumInst(Integer.toString(listaInstrucoes.size()));
        if(indice < listaInstrucoes.size()){
            Endereco endereco = listaInstrucoes.get(indice);
            Instrucao instrucao = endereco.getPalavra();

            if (instrucao.getNumBin().equals("11110100")){
                System.exit(0);
            }
            
            switch(instrucao.getOpcode()){
                case "0":
                    LDA.executar(instrucao.getNixbpq(), endereco, registradores, memoria, controller);
                    break;
                case "68":
                    LDB.executar(instrucao.getNixbpq(), endereco, registradores, memoria, controller);
                    break;
                case "8":
                    LDL.executar(instrucao.getNixbpq(), endereco, registradores, memoria, controller);
                    break;
                case "6C":
                    LDS.executar(instrucao.getNixbpq(), endereco, registradores, memoria, controller);
                    break;
                case "74":
                    LDT.executar(instrucao.getNixbpq(), endereco, registradores, memoria, controller);
                    break;
                case "18":
                    ADD.executar(instrucao.getNixbpq(), endereco, registradores, memoria, controller);
                    break;
                case "1C":
                    SUB.executar(instrucao.getNixbpq(), endereco, registradores, memoria, controller);
                    break;
                case "24":
                    DIV.executar(instrucao.getNixbpq(), endereco, registradores, memoria, controller);
                    break;
                case "20":
                    MUL.executar(instrucao.getNixbpq(), endereco, registradores, memoria, controller);
                    break;
                case "98":
                    MULR.executar(instrucao.getEnderecoBinario(), endereco, registradores, memoria, controller);
                    break;
                case "9C":
                    DIVR.executar(instrucao.getEnderecoBinario(), endereco, registradores, memoria, controller);
                    break;
                case "90":
                    ADDR.executar(instrucao.getEnderecoBinario(), endereco, registradores, memoria, controller);
                    break;
                case "94":
                    SUBR.executar(instrucao.getEnderecoBinario(), endereco, registradores, memoria, controller);
                    break;
                case "AC":
                    RMO.executar(instrucao.getEnderecoBinario(), endereco, registradores, memoria, controller);
                    break;
                case "3C":
                    indice = J.executar(instrucao.getNixbpq(), endereco, registradores, memoria, indice, controller);
                    break;
                case "0C":
                    STA.executar(instrucao.getNixbpq(), endereco, registradores, memoria, controller);
                    break;
                case "4":
                    CLEAR.executar(instrucao.getNixbpq(), endereco, registradores, memoria, controller);
                    break;
                
            }
        }
        indice+=1;
        
    }
   
}
    