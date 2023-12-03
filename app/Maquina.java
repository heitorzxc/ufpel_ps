package app;

import java.util.List;
import app.Registers.*;

public class Maquina {

    public Maquina(){}

    public void ExecutaMaquina(String arquivo){
  
        Memoria memoria = new Memoria(arquivo);
        memoria.imprimirMemoria();

        S.setValue(2);
        T.setValue(32);
        B.setValue(1);

        List<Enderecos> listaInstrucoes = memoria.getMemoria();
        System.out.println("\nA lista na memoria tem "+listaInstrucoes.size()+" instruçoes.\n");
        System.out.println("-----------------------------------------------");
       
        for (int i = 0; i < listaInstrucoes.size(); i++) {
            Enderecos endereco = listaInstrucoes.get(i);
            Instrucao instrucao = endereco.getPalavra();
            if (instrucao.getNumBin().equals("11110100")){
                break;
            }

            System.out.println("A instrucao é: "+instrucao.getInsHexa()+".");
            System.out.println("Já que o opcode é = "+instrucao.getOpcode()+".");
            System.out.println("Essa instrucao tem indice: "+endereco.getIndice()+".");

            String pegaDado = endereco.getPalavra().getEnderecoBinario();//copiou o endereço binário daquela palavra, que na verdade é um dado. 
            int dadoConvertido = Integer.parseInt(pegaDado, 2); // Converte para inteiro
            int value = dadoConvertido;

            switch (instrucao.getOpcode()){

                case "0": //LDA
                    if (instrucao.getNixbpq().equals("010000")){//endereçamento imediato e palavra de 24 bits
                        System.out.println("A intruçao é LDA imediato, que insere o valor inteiro do binário "+endereco.getPalavra().getEnderecoBinario()+" no Registrador A.");
                        System.out.println("O valor no Registrador A será = "+dadoConvertido);
                    }
                    if (instrucao.getNixbpq().equals("110000")){//endereçamento direto e palavra de 24 bits        
                        System.out.println("A intruçao é LDA direto, que insere o valor que está no endereço "+endereco.getPalavra().getEnderecoBinario()+" no Registrador A.");                        
                        int address = memoria.procuraNaMemoria(dadoConvertido);
                        value = address;   
                        System.out.println("O valor no endereço é = "+address);                                                
                    }
                    A.setValue(value);
                    A.printAcumulador();
                    break;

                case "18": //ADD
                    if (instrucao.getNixbpq().equals("010001")){//endereçamento imediato e palavra de 32 bits
                        System.out.println("A intruçao é ADD imediato, que soma o valor binário "+endereco.getPalavra().getEnderecoBinario()+" no acumulador (Registrador A) como se fosse um dado.");
                    }
                    if (instrucao.getNixbpq().equals("110001")){ //endereçamento direto e palavra de 32 bits
                        System.out.println("A intruçao é ADD direto, que soma o valor no endereço "+endereco.getPalavra().getEnderecoBinario()+" no acumulador (Registrador A).");
                        int address = memoria.procuraNaMemoria(dadoConvertido);
                        value = address;   
                        System.out.println("O valor no endereço é = "+address);                                               
                    }
                    A.setValue(A.getValue() + value);
                    A.printAcumulador();
                    break;

                case "4": //CLEAR
                    System.out.println("Essa instrucao CLEAR torna um registrador 0.");
                    System.out.println("O acumulador será zerado.");
                    System.out.println("O acumulador é = " +A.getValue());

                    A.reset();
                    A.printAcumulador();
                    break;

                case "24": //DIV
                    if (instrucao.getNixbpq().equals("010000")){//endereçamento imediato e palavra de 24 bits
                        System.out.println("A intruçao é DIV imediato, que divide o acumulador com o valor inteiro do binário "+endereco.getPalavra().getEnderecoBinario());
                        A.setValue(A.getValue()/dadoConvertido);;
                        A.printAcumulador();
                    }
                    break;

                case "9C": //DIVR
                    System.out.println("Essa instrucao DIVR divide dois registradores.");
                    if (instrucao.getEnderecoBinario().equals("00000101")){// vê quais são os dois registradores, nesse caso A e T
                        A.printAcumulador();
                        T.printAcumulador();
                        A.setValue(A.getValue()/T.getValue());
                        A.printAcumulador();
                    }
                    break;

                case "20": //MUL
                    if (instrucao.getNixbpq().equals("010000")){//endereçamento imediato e palavra de 24 bits
                        System.out.println("A intruçao é MUL imediato, que multiplica o acumulador com o valor inteiro do binário "+endereco.getPalavra().getEnderecoBinario());                   
                        A.setValue(A.getValue()*value);
                        A.printAcumulador();
                    }
                    break;

                case "90": //ADDR
                    System.out.println("Essa instrucao ADDR soma dois registradores.");
                    if (instrucao.getEnderecoBinario().equals("00010011")){// vê quais são os dois registradores, nesse caso S e T
                        S.printAcumulador();
                        T.printAcumulador();
                        S.setValue(S.getValue()+T.getValue());
                        S.printAcumulador();
                    }
                    break;

                case "98": //MULR
                    System.out.println("Essa instrucao MULR multiplica dois registradores.");
                    if (instrucao.getEnderecoBinario().equals("00000100")){// vê quais são os dois registradores, nesse caso A e S
                        A.printAcumulador();
                        S.printAcumulador();
                        A.setValue(A.getValue()*S.getValue());
                        A.printAcumulador();
                    }
                    break;

                case "AC": //RMO
                    System.out.println("Essa instrucao RMO transfere o valor de um registrador para outro.");
                    if (instrucao.getEnderecoBinario().equals("00110000")){// vê quais são os dois registradores, nesse caso B e A
                        B.setValue(A.getValue());
                        B.printAcumulador();
                    }
                    break;

                case "0C": //STA
                    System.out.println("Essa instrucao STA grava o valor do Registrador numa posicao da memoria.");
                    System.out.println("O endereço que se deseja salvar o valor de Reg A em é: " + dadoConvertido);
                
                    // Obtém o valor do registrador como uma string de 24 bits, preenchidos com zeros à esquerda, se necessário
                    String valorRegistrador = String.format("%24s", Integer.toBinaryString(A.getValue())).replace(' ', '0');
                    valorRegistrador = String.format("%24s", valorRegistrador).replace(' ', '0').substring(valorRegistrador.length() - 24);
                       
                    for (Enderecos enderecoComparado : memoria.getMemoria()) {
                        if (enderecoComparado.getEndDeci() == dadoConvertido) {
                            // Quando encontra o endereço desejado, modifica a instrução com o valor do registrador
                            Instrucao salvarInstrucao = enderecoComparado.getPalavra();
                            salvarInstrucao.setNumBin(valorRegistrador);
                
                            // Imprime a instrução modificada
                            System.out.println("Instrucao modificada no endereço " + dadoConvertido + ": " + salvarInstrucao.getNumBin() +" em inteiro e "+A.getValue()+".");
                            break; 
                        }
                    }
                    System.out.println("--------");
                    break;

                case "3C": //J
                    if (instrucao.getNixbpq().equals("010000")){//endereçamento imediato e palavra de 32 bits
                        System.out.println("A intruçao é J imediato, que transfere o valor binário "+endereco.getPalavra().getEnderecoBinario()+" para o Registrador PC.");                 
                        
                        PC.setValue(dadoConvertido);

                        for (Enderecos enderecoComparado : memoria.getMemoria()) {
                            if (enderecoComparado.getEndDeci() == PC.getValue()) {
                                // Quando encontra o endereço desejado ele encontrou a instrucao que o jump manda ir de volta ou pra frente.
                                System.out.println("O Registrador PC aponta para = " +PC.getValue()); 
                                System.out.println("O i era: "+i+".");
                                i = (enderecoComparado.getIndice()-1);
                                System.out.println("O novo i será: "+i+".");
                                break; 
                            }
                        } 
                    }
                    System.out.println("--------");
                    break;

                case "1C": //SUB
                    if (instrucao.getNixbpq().equals("010001")){//endereçamento imediato e palavra de 32 bits
                        System.out.println("A intruçao é SUB imediato, que soma o valor binário "+endereco.getPalavra().getEnderecoBinario()+" no acumulador (Registrador A) como se fosse um dado.");
                    }
                    if (instrucao.getNixbpq().equals("110001")){//endereçamento direto e palavra de 32 bits
                        System.out.println("A intruçao é SUB direto, que subtrai o valor positivo no endereço "+endereco.getPalavra().getEnderecoBinario()+" do acumulador (Registrador A).");        
                        int address = memoria.procuraNaMemoria(dadoConvertido);
                        value = address;                           
                    }
                    A.setValue(A.getValue() - value);
                    A.printAcumulador();
                    break;

                case "94": //SUBR
                    System.out.println("Essa instrucao SUBR subtrai dois registradores.");
                    if (instrucao.getEnderecoBinario().equals("00110001")){// vê quais são os dois registradores, nesse caso T e S
                        T.printAcumulador();
                        S.printAcumulador();
                        T.setValue(T.getValue()-S.getValue());
                    }
                    T.printAcumulador();
                    break;
                
                case "68": //LDB
                    if (instrucao.getNixbpq().equals("010000")){//endereçamento imediato e palavra de 24 bits
                        System.out.println("A intruçao é LDB imediato, que insere o valor inteiro do binário "+endereco.getPalavra().getEnderecoBinario()+" no Registrador B.");                       
                    }
                    if (instrucao.getNixbpq().equals("110000")){//endereçamento direto e palavra de 24 bits
                        System.out.println("A intruçao é LDB direto, que insere o valor que está no endereço "+endereco.getPalavra().getEnderecoBinario()+" no Registrador B.");
                        int address = memoria.procuraNaMemoria(dadoConvertido);
                        value = address;             
                    }
                    B.setValue(value);
                    B.printAcumulador();
                    break;

                case "8": //LDL 
                    if (instrucao.getNixbpq().equals("010000")){//endereçamento imediato e palavra de 24 bits
                        System.out.println("A intruçao é LDL imediato, que insere o valor inteiro do binário "+endereco.getPalavra().getEnderecoBinario()+" no Registrador L.");
                        System.out.println("O valor no Registrador L será = "+dadoConvertido);
                    }
                    if (instrucao.getNixbpq().equals("110000")){//endereçamento direto e palavra de 24 bits
                        System.out.println("A intruçao é LDL direto, que insere o valor que está no endereço "+endereco.getPalavra().getEnderecoBinario()+" no Registrador L.");
                        int address = memoria.procuraNaMemoria(dadoConvertido);
                        value = address;           
                    }
                    L.setValue(value);;
                    L.printAcumulador();
                    break;
                
                case "74": //LDT
                    if (instrucao.getNixbpq().equals("010000")){//endereçamento imediato e palavra de 24 bits
                        System.out.println("A intruçao é LDT imediato, que insere o valor inteiro do binário "+endereco.getPalavra().getEnderecoBinario()+" no Registrador T.");
                        System.out.println("O valor no Registrador T será = "+dadoConvertido);
                    }
                    if (instrucao.getNixbpq().equals("110000")){//endereçamento direto e palavra de 24 bits
                        System.out.println("A intruçao é LDT direto, que insere o valor que está no endereço "+endereco.getPalavra().getEnderecoBinario()+" no Registrador T.");
                        int address = memoria.procuraNaMemoria(dadoConvertido);
                        value = address;            
                    }
                    T.setValue(value);
                    T.printAcumulador();
                    break;

                default:
                    break;
            } // end Switch
            System.out.println("\n----------------------");
        }// end for

        memoria.imprimirMemoria();
    }
}
