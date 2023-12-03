import java.util.List;

public class Maquina {
    public static void main(String[] args) {
        Registrador regA = new Registrador();//A -> Acumulador ("endereço" = 0)
        Registrador regX = new Registrador();//X -> Registrador de Índice ("endereço" = 1)
        Registrador regL = new Registrador();//Registrador de Ligação ("endereço" = 2)
        Registrador regB = new Registrador();//Usado para Endereçamento ("endereço" = 3)
        Registrador regS = new Registrador();//Usado para uso geral ("endereço" = 4)
        Registrador regT = new Registrador();//Usado para uso geral("endereço" = 5)
        Registrador regF = new Registrador();//Acumulador de Ponto Flutuante ("endereço" = 6)
        Registrador regPC = new Registrador();//Contador de Instruções ("endereço" = 8)
        Registrador regSW = new Registrador();//Contém várias informações, incluindo código condicional (CC) ("endereço" = 9)

        regA.setNumeroBinario("000000000000000010000000");
        //regA.imprimirValorRegistrador();
        regX.setNumeroBinario("000000000000000000000000");
        regL.setNumeroBinario("000000000000000000000000");
        regB.setNumeroBinario("000000000000000000000000");
        regS.setNumeroBinario("000000000000000000000000");
        regT.setNumeroBinario("000000000000000000000000");
        regF.setNumeroBinario("000000000000000000000000");
        regPC.setNumeroBinario("000000000000000000000000");
        regSW.setNumeroBinario("000000000000000000000000");
        
        
        Memoria memoria = new Memoria("C:\\Users\\JC\\Desktop\\programas\\2 - Java\\exemplobinarios.txt");
        memoria.imprimirMemoria();
        System.out.println("-----------------------------------------------");
        regS.setNumeroInteiro(2);
        regT.setNumeroInteiro(32);
        regB.setNumeroInteiro(1);
        List<Enderecos> listaInstrucoes = memoria.getMemoria();
        System.out.println("\nA lista na memoria tem "+listaInstrucoes.size()+" instruçoes.\n");
        System.out.println("-----------------------------------------------");
        for (int i = 0; i < listaInstrucoes.size(); i++) {
            Enderecos endereco = listaInstrucoes.get(i);
            Instrucao instrucao = endereco.getPalavra();
            if (instrucao.getNumBin().equals("11110100")){
                break;
            }
            if (instrucao.getOpcode().equals("0")) {
                System.out.println("A instrucao é: "+endereco.getPalavra().getInsHexa()+".");
                System.out.println("Já que o opcode é = "+endereco.getPalavra().getOpcode()+".");
                System.out.println("Essa instrucao tem indice: "+endereco.getIndice()+".");
                if (instrucao.getNixbpq().equals("010000")){//endereçamento imediato e palavra de 24 bits
                    System.out.println("A intruçao é LDA imediato, que insere o valor inteiro do binário "+endereco.getPalavra().getEnderecoBinario()+" no Registrador A.");
                    String pegaDado = endereco.getPalavra().getEnderecoBinario();//copiou o endereço binário daquela palavra, que na verdade é um dado. 
                    int dadoConvertido = Integer.parseInt(pegaDado, 2); // Converte para inteiro
                    System.out.println("O valor no Registrador A será = "+dadoConvertido);
                    regA.setNumeroInteiro((dadoConvertido));
                }
                if (instrucao.getNixbpq().equals("110000")){//endereçamento direto e palavra de 24 bits
                    System.out.println("A intruçao é LDA direto, que insere o valor que está no endereço "+endereco.getPalavra().getEnderecoBinario()+" no Registrador A.");
                    String enderecoDesejado = endereco.getPalavra().getEnderecoBinario();
                    int enderecoDesejadoInt = Integer.parseInt(enderecoDesejado, 2);
                    for (Enderecos enderecoComparado : memoria.getMemoria()) {
                        if (enderecoComparado.getEndDeci() == enderecoDesejadoInt) {
                            // Quando encontra o endereço desejado, acessa o endereço e a instrução associada
                            String numeroBinario = enderecoComparado.getPalavra().getNumBin();
                            int numeroConvertido = Integer.parseInt(numeroBinario, 2);
                            System.out.println("O valor no endereço é = "+numeroConvertido);
                            regA.setNumeroInteiro(numeroConvertido);
                            break; // Sai do loop quando encontra o endereço
                        }
                    } 
                }
                System.out.println("O valor no Registrador A = "+regA.getNumeroInteiro()+"");
                System.out.println("--------");
            }
            if (instrucao.getOpcode().equals("68")) {
                System.out.println("A instrucao é: "+endereco.getPalavra().getInsHexa()+".");
                System.out.println("Já que o opcode é = "+endereco.getPalavra().getOpcode()+".");
                System.out.println("Essa instrucao tem indice: "+endereco.getIndice()+".");
                if (instrucao.getNixbpq().equals("010000")){//endereçamento imediato e palavra de 24 bits
                    System.out.println("A intruçao é LDB imediato, que insere o valor inteiro do binário "+endereco.getPalavra().getEnderecoBinario()+" no Registrador B.");
                    String pegaDado = endereco.getPalavra().getEnderecoBinario();//copiou o endereço binário daquela palavra, que na verdade é um dado. 
                    int dadoConvertido = Integer.parseInt(pegaDado, 2); // Converte para inteiro
                    System.out.println("O valor no Registrador B será = "+dadoConvertido);
                    regB.setNumeroInteiro(dadoConvertido);
                }
                if (instrucao.getNixbpq().equals("110000")){//endereçamento direto e palavra de 24 bits
                    System.out.println("A intruçao é LDB direto, que insere o valor que está no endereço "+endereco.getPalavra().getEnderecoBinario()+" no Registrador B.");
                    String enderecoDesejado = endereco.getPalavra().getEnderecoBinario();
                    int enderecoDesejadoInt = Integer.parseInt(enderecoDesejado, 2);
                    for (Enderecos enderecoComparado : memoria.getMemoria()) {
                        if (enderecoComparado.getEndDeci() == enderecoDesejadoInt) {
                            // Quando encontra o endereço desejado, acessa o endereço e a instrução associada
                            String numeroBinario = enderecoComparado.getPalavra().getNumBin();
                            int numeroConvertido = Integer.parseInt(numeroBinario, 2);
                            System.out.println("O valor no endereço é = "+numeroConvertido);
                            regB.setNumeroInteiro(numeroConvertido);
                            break; // Sai do loop quando encontra o endereço
                        }
                    } 
                }
                System.out.println("O valor no Registrador B = "+regB.getNumeroInteiro()+"\n");
                System.out.println("--------");
            }
            if (instrucao.getOpcode().equals("8")) {
                System.out.println("A instrucao é: "+endereco.getPalavra().getInsHexa()+".");
                System.out.println("Já que o opcode é = "+endereco.getPalavra().getOpcode()+".");
                System.out.println("Essa instrucao tem indice: "+endereco.getIndice()+".");
                if (instrucao.getNixbpq().equals("010000")){//endereçamento imediato e palavra de 24 bits
                    System.out.println("A intruçao é LDL imediato, que insere o valor inteiro do binário "+endereco.getPalavra().getEnderecoBinario()+" no Registrador L.");
                    String pegaDado = endereco.getPalavra().getEnderecoBinario();//copiou o endereço binário daquela palavra, que na verdade é um dado. 
                    int dadoConvertido = Integer.parseInt(pegaDado, 2); // Converte para inteiro
                    System.out.println("O valor no Registrador L será = "+dadoConvertido);
                    regL.setNumeroInteiro(dadoConvertido);
                }
                if (instrucao.getNixbpq().equals("110000")){//endereçamento direto e palavra de 24 bits
                    System.out.println("A intruçao é LDL direto, que insere o valor que está no endereço "+endereco.getPalavra().getEnderecoBinario()+" no Registrador L.");
                    String enderecoDesejado = endereco.getPalavra().getEnderecoBinario();
                    int enderecoDesejadoInt = Integer.parseInt(enderecoDesejado, 2);
                    for (Enderecos enderecoComparado : memoria.getMemoria()) {
                        if (enderecoComparado.getEndDeci() == enderecoDesejadoInt) {
                            // Quando encontra o endereço desejado, acessa o endereço e a instrução associada
                            String numeroBinario = enderecoComparado.getPalavra().getNumBin();
                            int numeroConvertido = Integer.parseInt(numeroBinario, 2);
                            System.out.println("O valor no endereço é = "+numeroConvertido);
                            regL.setNumeroInteiro(numeroConvertido);
                            break; // Sai do loop quando encontra o endereço
                        }
                    } 
                }
                System.out.println("O valor no Registrador L = "+regL.getNumeroInteiro()+"\n");
                System.out.println("--------");
            }
            if (instrucao.getOpcode().equals("6C")) {
                System.out.println("A instrucao é: "+endereco.getPalavra().getInsHexa()+".");
                System.out.println("Já que o opcode é = "+endereco.getPalavra().getOpcode()+".");
                System.out.println("Essa instrucao tem indice: "+endereco.getIndice()+".");
                if (instrucao.getNixbpq().equals("010000")){//endereçamento imediato e palavra de 24 bits
                    System.out.println("A intruçao é LDS imediato, que insere o valor inteiro do binário "+endereco.getPalavra().getEnderecoBinario()+" no Registrador S.");
                    String pegaDado = endereco.getPalavra().getEnderecoBinario();//copiou o endereço binário daquela palavra, que na verdade é um dado. 
                    int dadoConvertido = Integer.parseInt(pegaDado, 2); // Converte para inteiro
                    System.out.println("O valor no Registrador S será = "+dadoConvertido);
                    regS.setNumeroInteiro(dadoConvertido);
                }
                if (instrucao.getNixbpq().equals("110000")){//endereçamento direto e palavra de 24 bits
                    System.out.println("A intruçao é LDS direto, que insere o valor que está no endereço "+endereco.getPalavra().getEnderecoBinario()+" no Registrador S.");
                    String enderecoDesejado = endereco.getPalavra().getEnderecoBinario();
                    int enderecoDesejadoInt = Integer.parseInt(enderecoDesejado, 2);
                    for (Enderecos enderecoComparado : memoria.getMemoria()) {
                        if (enderecoComparado.getEndDeci() == enderecoDesejadoInt) {
                            // Quando encontra o endereço desejado, acessa o endereço e a instrução associada
                            String numeroBinario = enderecoComparado.getPalavra().getNumBin();
                            int numeroConvertido = Integer.parseInt(numeroBinario, 2);
                            System.out.println("O valor no endereço é = "+numeroConvertido);
                            regS.setNumeroInteiro(numeroConvertido);
                            break; // Sai do loop quando encontra o endereço
                        }
                    } 
                }
                System.out.println("O valor no Registrador S = "+regS.getNumeroInteiro()+"\n");
                System.out.println("--------");
            }

            if (instrucao.getOpcode().equals("74")) {
                System.out.println("A instrucao é: "+endereco.getPalavra().getInsHexa()+".");
                System.out.println("Já que o opcode é = "+endereco.getPalavra().getOpcode()+".");
                System.out.println("Essa instrucao tem indice: "+endereco.getIndice()+".");
                if (instrucao.getNixbpq().equals("010000")){//endereçamento imediato e palavra de 24 bits
                    System.out.println("A intruçao é LDT imediato, que insere o valor inteiro do binário "+endereco.getPalavra().getEnderecoBinario()+" no Registrador T.");
                    String pegaDado = endereco.getPalavra().getEnderecoBinario();//copiou o endereço binário daquela palavra, que na verdade é um dado. 
                    int dadoConvertido = Integer.parseInt(pegaDado, 2); // Converte para inteiro
                    System.out.println("O valor no Registrador T será = "+dadoConvertido);
                    regT.setNumeroInteiro(dadoConvertido);
                }
                if (instrucao.getNixbpq().equals("110000")){//endereçamento direto e palavra de 24 bits
                    System.out.println("A intruçao é LDT direto, que insere o valor que está no endereço "+endereco.getPalavra().getEnderecoBinario()+" no Registrador T.");
                    String enderecoDesejado = endereco.getPalavra().getEnderecoBinario();
                    int enderecoDesejadoInt = Integer.parseInt(enderecoDesejado, 2);
                    for (Enderecos enderecoComparado : memoria.getMemoria()) {
                        if (enderecoComparado.getEndDeci() == enderecoDesejadoInt) {
                            // Quando encontra o endereço desejado, acessa o endereço e a instrução associada
                            String numeroBinario = enderecoComparado.getPalavra().getNumBin();
                            int numeroConvertido = Integer.parseInt(numeroBinario, 2);
                            System.out.println("O valor no endereço é = "+numeroConvertido);
                            regT.setNumeroInteiro(numeroConvertido);
                            break; // Sai do loop quando encontra o endereço
                        }
                    } 
                }
                System.out.println("O valor no Registrador T = "+regT.getNumeroInteiro()+"\n");
                System.out.println("--------");
            }
            if (instrucao.getOpcode().equals("18")){
                System.out.println("A instrucao é: "+instrucao.getInsHexa()+".");
                System.out.println("Já que o opcode é = "+instrucao.getOpcode()+".");
                System.out.println("Essa instrucao tem indice: "+endereco.getIndice()+".");
                if (instrucao.getNixbpq().equals("010001")){//endereçamento imediato e palavra de 32 bits
                    System.out.println("A intruçao é ADD imediato, que soma o valor binário "+endereco.getPalavra().getEnderecoBinario()+" no acumulador (Registrador A) como se fosse um dado.");
                    String pegaDado = endereco.getPalavra().getEnderecoBinario();//copiou o endereço binário daquela palavra, que na verdade é um dado. 
                    int dadoConvertido = Integer.parseInt(pegaDado, 2); // Converte para inteiro
                    regA.setNumeroInteiro((dadoConvertido+regA.getNumeroInteiro()));
                }
                if (instrucao.getNixbpq().equals("110001")){ //endereçamento direto e palavra de 32 bits
                    System.out.println("A intruçao é ADD direto, que soma o valor no endereço "+endereco.getPalavra().getEnderecoBinario()+" no acumulador (Registrador A).");
                    String enderecoDesejado = endereco.getPalavra().getEnderecoBinario();
                    int enderecoDesejadoInt = Integer.parseInt(enderecoDesejado, 2); // Converte para inteiro 
                    // Percorre a lista de endereços na memória
                    for (Enderecos enderecoComparado : memoria.getMemoria()) {
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
                System.out.println("Essa instrucao tem indice: "+endereco.getIndice()+".");
                if (instrucao.getNixbpq().equals("010001")){//endereçamento imediato e palavra de 32 bits
                    System.out.println("A intruçao é SUB imediato, que soma o valor binário "+endereco.getPalavra().getEnderecoBinario()+" no acumulador (Registrador A) como se fosse um dado.");
                    String pegaDado = endereco.getPalavra().getEnderecoBinario();//copiou o endereço binário daquela palavra, que na verdade é um dado. 
                    int dadoConvertido = Integer.parseInt(pegaDado, 2); // Converte para inteiro
                    regA.setNumeroInteiro((regA.getNumeroInteiro()-dadoConvertido));//subtrai o valor
                }
                if (instrucao.getNixbpq().equals("110001")){//endereçamento direto e palavra de 32 bits
                    System.out.println("A intruçao é SUB direto, que subtrai o valor positivo no endereço "+endereco.getPalavra().getEnderecoBinario()+" do acumulador (Registrador A).");
                    String enderecoDesejado = endereco.getPalavra().getEnderecoBinario();
                    int enderecoDesejadoInt = Integer.parseInt(enderecoDesejado, 2); // Converte para inteiro
                    // Percorre a lista de endereços na memória
                    for (Enderecos enderecoComparado : memoria.getMemoria()) {
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
            if (instrucao.getOpcode().equals("24")){
                System.out.println("A instrucao é: "+instrucao.getInsHexa()+".");
                System.out.println("Já que o opcode é = "+instrucao.getOpcode()+".");
                System.out.println("Essa instrucao tem indice: "+endereco.getIndice()+".");
                if (instrucao.getNixbpq().equals("010000")){//endereçamento imediato e palavra de 24 bits
                    System.out.println("A intruçao é DIV imediato, que divide o acumulador com o valor inteiro do binário "+endereco.getPalavra().getEnderecoBinario());
                    String pegaDado = endereco.getPalavra().getEnderecoBinario();//copiou o endereço binário daquela palavra, que na verdade é um dado. 
                    int dadoConvertido = Integer.parseInt(pegaDado, 2); // Converte para inteiro
                    regA.setNumeroInteiro((regA.getNumeroInteiro()/dadoConvertido));//divide pelo valor
                    System.out.println("O valor no Registrador A = "+regA.getNumeroInteiro()+"\n");
                }
                System.out.println("--------");
            }
            if (instrucao.getOpcode().equals("20")){
                System.out.println("A instrucao é: "+instrucao.getInsHexa()+".");
                System.out.println("Já que o opcode é = "+instrucao.getOpcode()+".");
                System.out.println("Essa instrucao tem indice: "+endereco.getIndice()+".");
                if (instrucao.getNixbpq().equals("010000")){//endereçamento imediato e palavra de 24 bits
                    System.out.println("A intruçao é MUL imediato, que multiplica o acumulador com o valor inteiro do binário "+endereco.getPalavra().getEnderecoBinario());
                    String pegaDado = endereco.getPalavra().getEnderecoBinario();//copiou o endereço binário daquela palavra, que na verdade é um dado. 
                    int dadoConvertido = Integer.parseInt(pegaDado, 2); // Converte para inteiro
                    regA.setNumeroInteiro((regA.getNumeroInteiro()*dadoConvertido));//multiplica pelo valor
                    System.out.println("O valor no Registrador A = "+regA.getNumeroInteiro()+"\n");
                }
                System.out.println("--------");
            }
            if (instrucao.getOpcode().equals("98")){
                System.out.println("A instrucao é: "+instrucao.getInsHexa()+".");
                System.out.println("Já que o opcode é = "+instrucao.getOpcode()+".");
                System.out.println("Essa instrucao tem indice: "+endereco.getIndice()+".");
                System.out.println("Essa instrucao MULR multiplica dois registradores.");
                if (instrucao.getEnderecoBinario().equals("00000100")){// vê quais são os dois registradores, nesse caso A e S
                    System.out.println("A <- (A) * (S)");
                    System.out.println("O registrador S será multiplicado com o Registrador A, o acumulador.");
                    System.out.println("O registrador S tem valor = " +regS.getNumeroInteiro());
                    System.out.println("O registrador A tem valor = " +regA.getNumeroInteiro());
                    regA.setNumeroInteiro(regA.getNumeroInteiro()*regS.getNumeroInteiro());
                    System.out.println("O valor no Registrador A = "+regA.getNumeroInteiro()+"\n");
                }
                System.out.println("--------");
            }
            if (instrucao.getOpcode().equals("9C")){
                System.out.println("A instrucao é: "+instrucao.getInsHexa()+".");
                System.out.println("Já que o opcode é = "+instrucao.getOpcode()+".");
                System.out.println("Essa instrucao tem indice: "+endereco.getIndice()+".");
                System.out.println("Essa instrucao DIVR divide dois registradores.");
                if (instrucao.getEnderecoBinario().equals("00000101")){// vê quais são os dois registradores, nesse caso A e T
                    System.out.println("A <- (A) / (T)");
                    System.out.println("O registrador A será divido com o valor no Registrador T.");
                    System.out.println("O registrador T tem valor = " +regT.getNumeroInteiro());
                    System.out.println("O registrador A tem valor = " +regA.getNumeroInteiro());
                    regA.setNumeroInteiro(regA.getNumeroInteiro()/regT.getNumeroInteiro());
                    System.out.println("O valor no acumulador (Registrador A) = "+regA.getNumeroInteiro()+"\n");
                }
                System.out.println("--------");
            }
            if (instrucao.getOpcode().equals("90")){
                System.out.println("A instrucao é: "+instrucao.getInsHexa()+".");
                System.out.println("Já que o opcode é = "+instrucao.getOpcode()+".");
                System.out.println("Essa instrucao tem indice: "+endereco.getIndice()+".");
                System.out.println("Essa instrucao ADDR soma dois registradores.");
                if (instrucao.getEnderecoBinario().equals("00010011")){// vê quais são os dois registradores, nesse caso S e T
                    System.out.println("S <- (S) + (T)");
                    System.out.println("O valor no registrador S será somado com o valor no Registrador T.");
                    System.out.println("O registrador S tem valor = " +regS.getNumeroInteiro());
                    System.out.println("O registrador T tem valor = " +regT.getNumeroInteiro());
                    regS.setNumeroInteiro(regS.getNumeroInteiro()+regT.getNumeroInteiro());
                    System.out.println("O valor no Registrador S = "+regS.getNumeroInteiro()+"\n");
                }
                System.out.println("--------");
            }
            if (instrucao.getOpcode().equals("94")){
                System.out.println("A instrucao é: "+instrucao.getInsHexa()+".");
                System.out.println("Já que o opcode é = "+instrucao.getOpcode()+".");
                System.out.println("Essa instrucao tem indice: "+endereco.getIndice()+".");
                System.out.println("Essa instrucao SUBR subtrai dois registradores.");
                if (instrucao.getEnderecoBinario().equals("00110001")){// vê quais são os dois registradores, nesse caso T e S
                    System.out.println("T <- (T) - (S)");
                    System.out.println("O valor no registrador S será subtraido do valor no Registrador T.");
                    System.out.println("O registrador S tem valor = " +regS.getNumeroInteiro());
                    System.out.println("O registrador T tem valor = " +regT.getNumeroInteiro());
                    regT.setNumeroInteiro(regT.getNumeroInteiro()-regS.getNumeroInteiro());
                    System.out.println("O valor no Registrador T = "+regT.getNumeroInteiro()+"\n");
                }
                System.out.println("--------");
            }
            if (instrucao.getOpcode().equals("AC")){
                System.out.println("A instrucao é: "+instrucao.getInsHexa()+".");
                System.out.println("Já que o opcode é = "+instrucao.getOpcode()+".");
                System.out.println("Essa instrucao tem indice: "+endereco.getIndice()+".");
                System.out.println("Essa instrucao RMO transfere o valor de um registrador para outro.");
                if (instrucao.getEnderecoBinario().equals("00110000")){// vê quais são os dois registradores, nesse caso B e A
                    System.out.println("B <- (A)");
                    System.out.println("O valor no registrador B será o valor inteiro no Registrador T.");
                    System.out.println("O registrador B tem valor = " +regB.getNumeroInteiro());
                    System.out.println("O registrador A tem valor = " +regA.getNumeroInteiro());
                    regB.setNumeroInteiro(regA.getNumeroInteiro());
                }
                System.out.println("Agora, o valor no Registrador B = "+regB.getNumeroInteiro()+"\n");
                System.out.println("--------");
            }
            if (instrucao.getOpcode().equals("3C")){
                System.out.println("A instrucao é: "+instrucao.getInsHexa()+".");
                System.out.println("Já que o opcode é = "+instrucao.getOpcode()+".");
                System.out.println("Essa instrucao tem indice: "+endereco.getIndice()+".");
                if (instrucao.getNixbpq().equals("010000")){//endereçamento imediato e palavra de 32 bits
                    System.out.println("A intruçao é J imediato, que transfere o valor binário "+endereco.getPalavra().getEnderecoBinario()+" para o Registrador PC.");
                    String pegaDado = endereco.getPalavra().getEnderecoBinario();//copiou o endereço binário daquela palavra, que na verdade é um dado. 
                    int dadoConvertido = Integer.parseInt(pegaDado, 2); // Converte para inteiro
                    regPC.setNumeroInteiro(dadoConvertido);//seta o PC com o novo endereço.
                    int enderecoDesejadoInt = regPC.getNumeroInteiro();
                    // Percorre a lista de endereços na memória
                    for (Enderecos enderecoComparado : memoria.getMemoria()) {
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
                System.out.println("--------");
            }
            if (instrucao.getOpcode().equals("C")) {
                System.out.println("A instrucao é: " + instrucao.getInsHexa() + ".");
                System.out.println("Já que o opcode é = " + instrucao.getOpcode() + ".");
                System.out.println("Essa instrucao tem indice: "+endereco.getIndice()+".");
                System.out.println("Essa instrucao STA grava o valor do Registrador numa posicao da memoria.");
            
                String enderecoDesejado = endereco.getPalavra().getEnderecoBinario();
                int enderecoDesejadoInt = Integer.parseInt(enderecoDesejado, 2);
                System.out.println("O endereço que se deseja salvar o valor de Reg A em é: " + enderecoDesejadoInt);
            
                // Obtém o valor do registrador como uma string de 24 bits, preenchidos com zeros à esquerda, se necessário
                String valorRegistrador = String.format("%24s", Integer.toBinaryString(regA.getNumeroInteiro())).replace(' ', '0');
                valorRegistrador = String.format("%24s", valorRegistrador).replace(' ', '0').substring(valorRegistrador.length() - 24);
            
                for (Enderecos enderecoComparado : memoria.getMemoria()) {
                    if (enderecoComparado.getEndDeci() == enderecoDesejadoInt) {
                        // Quando encontra o endereço desejado, modifica a instrução com o valor do registrador
                        Instrucao salvarInstrucao = enderecoComparado.getPalavra();
                        salvarInstrucao.setNumBin(valorRegistrador);
            
                        // Imprime a instrução modificada
                        System.out.println("Instrucao modificada no endereço " + enderecoDesejado + ": " + salvarInstrucao.getNumBin() +" em inteiro e "+regA.getNumeroInteiro()+".");
                        break; // Sai do loop quando encontra o endereço
                    }
                }
                System.out.println("--------");
            }
            if (instrucao.getOpcode().equals("4")){
                System.out.println("A instrucao é: "+instrucao.getInsHexa()+".");
                System.out.println("Já que o opcode é = "+instrucao.getOpcode()+".");
                System.out.println("Essa instrucao tem indice: "+endereco.getIndice()+".");
                System.out.println("Essa instrucao CLEAR torna um registrador 0.");
                System.out.println("O acumulador será zerado.");
                System.out.println("O acumulador é = " +regA.getNumeroInteiro());
                regA.setNumeroInteiro(0);
                System.out.println("O valor no acumulador (Registrador A) = "+regA.getNumeroInteiro()+"\n");
                System.out.println("--------");
            }
        }
        //memoria.imprimirMemoria();
    }
}
