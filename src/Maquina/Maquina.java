package src.Maquina;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import src.Instrucoes.Instrucao;
import src.Instrucoes.Instrucoes;
import src.Instrucoes.J;
import src.Registradores.BancoRegistradores;
import src.Registradores.Registrador;

public class Maquina {
    public BancoRegistradores registradores;
    public Memoria memoria;
    public Instrucoes instrucoes;
    
    public Maquina(){
        this.memoria = new Memoria();
        this.registradores = new BancoRegistradores();
        this.instrucoes = new Instrucoes();
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
    
    public void executarPrograma(){
        memoria.imprimirMemoria();
        
        Registrador regS = registradores.getRegistrador("S");
        regS.setNumeroInteiro(2);
        
        Registrador regT = registradores.getRegistrador("T");
        regT.setNumeroInteiro(32);
        
        Registrador regB = registradores.getRegistrador("B");
        regB.setNumeroInteiro(1);
        
        List<Endereco> listaInstrucoes = memoria.getMemoriaComInstrucoes();     
        
        System.err.println("QUANTIDADE INSTRUÇÕES => " + listaInstrucoes.size());
        
        for(int i = 0; i < listaInstrucoes.size(); i++){
            Endereco endereco = listaInstrucoes.get(i);
            Instrucao instrucao = endereco.getPalavra();

            if (instrucao.getNumBin().equals("11110100")){
                break;
            } else if(instrucao.getOpcode() == "3C"){
                i = J.executar(instrucao.getNixbpq(), endereco, registradores, memoria, i);
                break;
            } else {
                instrucoes.executaInstrucao(instrucao, endereco, registradores, memoria);
            }

          
        }
        
    }
   
}
    