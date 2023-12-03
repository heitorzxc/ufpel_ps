package src.Maquina;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import src.Registradores.BancoRegistradores;
import src.Registradores.Registrador;

public class Maquina {
    public BancoRegistradores registradores;
    public Memoria memoria;
    
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
        
        /*  já ta sendo feito quando instancia o banco de registradores  
            regA.setNumeroBinario("000000000000000010000000"); 
            regX.setNumeroBinario("000000000000000000000000");
            regL.setNumeroBinario("000000000000000000000000");
            regB.setNumeroBinario("000000000000000000000000");
            regS.setNumeroBinario("000000000000000000000000");
            regT.setNumeroBinario("000000000000000000000000");
            regF.setNumeroBinario("000000000000000000000000");
            regPC.setNumeroBinario("000000000000000000000000");
            regSW.setNumeroBinario("000000000000000000000000");   */
        
        System.out.println("-----------------------------------------------");
        //  regS.setNumeroInteiro(2);
        Registrador regS = registradores.getRegistrador("S");
        regS.setNumeroInteiro(2);
        
        //  regT.setNumeroInteiro(32);
        Registrador regT = registradores.getRegistrador("T");
        regT.setNumeroInteiro(32);
        
        Registrador regA = registradores.getRegistrador("A");
        
        
        // registradores.imprimirValoresRegistradores();
    
        List<Endereco> listaInstrucoes = memoria.getMemoria();
        for (int i = 0; i < listaInstrucoes.size(); i++) {
            Endereco endereco = listaInstrucoes.get(i);
            System.out.println(endereco.toString());
            
            Instrucao instrucao = endereco.getPalavra();
            if (instrucao.getNumBin().equals("11110100")){
                break;
            }
            if (instrucao.getOpcode().equals("0")) {
                System.out.println("A instrucao é: "+endereco.getPalavra().getInsHexa()+".");
                System.out.println("Já que o opcode é = "+endereco.getPalavra().getOpcode()+".");
                System.out.println("A intruçao é LDA, que carrega 0 no registrador acumulador.");
                regA.setNumeroInteiro(0);
                System.out.println("O valor no acumulador (Registrador A) = "+regA.getNumeroInteiro()+"");
                System.out.println("--------");
            }
            if (instrucao.getOpcode().equals("18")){
                System.out.println("A instrucao é: "+instrucao.getInsHexa()+".");
                System.out.println("Já que o opcode é = "+instrucao.getOpcode()+".");
                if (instrucao.getNixbpq().equals("010001")){
                    System.out.println("A intruçao é ADD imediato, que soma o valor no endereço "+endereco.getPalavra().getEnderecoBinario()+" no acumulador (Registrador A).");
                    String enderecoDesejado = endereco.getPalavra().getEnderecoBinario();
                    int enderecoDesejadoInt = Integer.parseInt(enderecoDesejado, 2); // Converte para inteiro
                    // Percorre a lista de endereços na memória
                    for (Endereco enderecoComparado : memoria.getMemoria()) {
                        if (enderecoComparado.getEndDeci() == enderecoDesejadoInt) {
                            // Quando encontra o endereço desejado, acessa o endereço e a instrução associada
                            String numeroBinario = enderecoComparado.getPalavra().getNumBin();
                            int numeroConvertido = Integer.parseInt(numeroBinario, 2);
                            System.out.println("O valor no endereço é = "+numeroConvertido);
                            regA.setNumeroInteiro((numeroConvertido+regA.getNumeroInteiro()));
                            break; // Sai do loop quando encontra o endereço
                        }
                    }                   
                    System.out.println("O valor no acumulador (Registrador A) = "+regA.getNumeroInteiro()+"\n");
                    System.out.println("--------");
                }
            }
            if (instrucao.getOpcode().equals("1C")){
                System.out.println("A instrucao é: "+instrucao.getInsHexa()+".");
                System.out.println("Já que o opcode é = "+instrucao.getOpcode()+".");
                if (instrucao.getNixbpq().equals("010001")){
                    System.out.println("A intruçao é SUB imediato, que subtrai o valor positivo no endereço "+endereco.getPalavra().getEnderecoBinario()+" do acumulador (Registrador A).");
                    String enderecoDesejado = endereco.getPalavra().getEnderecoBinario();
                    int enderecoDesejadoInt = Integer.parseInt(enderecoDesejado, 2); // Converte para inteiro
                    // Percorre a lista de endereços na memória
                    for (Endereco enderecoComparado : memoria.getMemoria()) {
                        if (enderecoComparado.getEndDeci() == enderecoDesejadoInt) {
                            // Quando encontra o endereço desejado, acessa o endereço e a instrução associada
                            String numeroBinario = enderecoComparado.getPalavra().getNumBin();
                            int numeroConvertido = Integer.parseInt(numeroBinario, 2);
                            System.out.println("O valor no endereço é = "+numeroConvertido);
                            regA.setNumeroInteiro((regA.getNumeroInteiro()-numeroConvertido));
                            break; // Sai do loop quando encontra o endereço
                        }
                    }                   
                    System.out.println("O valor no acumulador (Registrador A) = "+regA.getNumeroInteiro()+"\n");
                    System.out.println("--------");
                }
            }
            if (instrucao.getOpcode().equals("98")){
                System.out.println("A instrucao é: "+instrucao.getInsHexa()+".");
                System.out.println("Já que o opcode é = "+instrucao.getOpcode()+".");
                System.out.println("Essa instrucao MULR multiplica dois registradores.");
                System.out.println("O registrador S será multiplicado com o valor no acumulador.");
                System.out.println("O registrador S é = " +regS.getNumeroInteiro());
                System.out.println("O acumulador é = " +regA.getNumeroInteiro());
                regA.setNumeroInteiro(regA.getNumeroInteiro()*regS.getNumeroInteiro());
                System.out.println("O valor no acumulador (Registrador A) = "+regA.getNumeroInteiro()+"\n");
                System.out.println("--------");
            }
            if (instrucao.getOpcode().equals("9C")){
                System.out.println("A instrucao é: "+instrucao.getInsHexa()+".");
                System.out.println("Já que o opcode é = "+instrucao.getOpcode()+".");
                System.out.println("Essa instrucao DIVR divide dois registradores.");
                System.out.println("O registrador A será dividido com o valor no Registrador T.");
                System.out.println("O registrador T é = " +regT.getNumeroInteiro());
                System.out.println("O acumulador é = " +regA.getNumeroInteiro());
                regA.setNumeroInteiro(regA.getNumeroInteiro()/regT.getNumeroInteiro());
                System.out.println("O valor no acumulador (Registrador A) = "+regA.getNumeroInteiro()+"\n");
                System.out.println("--------");
            }
            if (instrucao.getOpcode().equals("4")){
                System.out.println("A instrucao é: "+instrucao.getInsHexa()+".");
                System.out.println("Já que o opcode é = "+instrucao.getOpcode()+".");
                System.out.println("Essa instrucao CLEAR torna um registrador 0.");
                System.out.println("O acumulador será zerado.");
                System.out.println("O acumulador é = " +regA.getNumeroInteiro());
                regA.setNumeroInteiro(0);
                System.out.println("O valor no acumulador (Registrador A) = "+regA.getNumeroInteiro()+"\n");
                System.out.println("--------");
            }
            
            if(instrucao.getOpcode().equals("44")){
                System.out.println("A instrucao é: "+instrucao.getInsHexa()+".");
                System.out.println("Já que o opcode é = "+instrucao.getOpcode()+".");
                regA.setNumeroInteiro(0);
            }
        }

        memoria.imprimirMemoria();
    }
   
}
    
    
    
    
    
        
     
    //     public static void printExemplos(){

    //     String binario1 = "11000100"; // 8 bits
    //     String binario2 = "1011010000010000"; // 16 bits
    //     String binario3 = "111000110010000000010001"; // 24 bits
    //     String binario4 = "00000011000100001100001100000011"; // 32 bits

    //     Instrucao inst1 = new Instrucao(binario1);
    //     Instrucao inst2 = new Instrucao(binario2);
    //     Instrucao inst3 = new Instrucao(binario3);
    //     Instrucao inst4 = new Instrucao(binario4);

    //     inst1.imprimirDetalhesInstrucao();
    //     inst2.imprimirDetalhesInstrucao();
    //     inst3.imprimirDetalhesInstrucao();
    //     inst4.imprimirDetalhesInstrucao();

    //     //Exemplo de Instrução de 1 Byte
    //     // Instrução FIX = C4 ou 11000100, Opcode C4, converte float para inteiro, não acessa a memória, apenas o registrador F.
    //     // Chamando o método para imprimir os detalhes da instrução de 1 byte

    //     //Exemplo de Instrução de 2 Byte
    //     // Instrução CLEAR X (X = Registrador de Índice -> r1 = 0001(endereço de X), r2 = 0000) = B410 ou 1011010000010000, Opcode B4.
    //     // Chamando o método para imprimir os detalhes da instrução de 2 bytes

    //     //Exemplo de Instrução de 3 Bytes
    //     //Instrução TD OUTPUT = E32011, Opcode E0

    //     // Chamando o método para imprimir os detalhes da instrução de 3 bytes

    //     //Exemplo de Instrução de 4 Bytes
    //     //Instrução LDA A = 310C303, Opcode 00, endereço que contém o valor que será carregado em A = 0000 1100 0011 0000 0011

    //     // Chamando o método para imprimir os detalhes da instrução de 4 bytes
    // }
    // //percorrer memoria virtual em um laço, a parada é um numero binario em específico
    // //identificar o numero binario/palavra.
    // //definir o formato de instrução, se é de 1byte, 2bytes, 3bytes ou 4bytes, com base no seu tamanho. 
    // //se for 3bytes
    // //identificar o opcode
    // //ir para o case daquela instrucao
    // //identificar o tipo de endereçamento com base no nixbpq
    // //acionar os registradores corretos e/ou obter os valores nos endereços/registradores
    // //executar a operação da instrucao
    // //atualizar o pc
    // //sair do case
    // //pegar a próxima palavra
