package src.Instrucoes;

import src.Memoria.Endereco;
import src.Memoria.Memoria;
import src.Registradores.*;
import src.Utils.Conversao;

/*******
 * LDB *
 *******/
public class LDS extends Instrucao {
    public LDS() {
        super("LDS", "6C", 1, true);
    }

    public void executar(Endereco instrucao, BancoRegistradores registradores, Memoria memoria) throws Exception {
        String nixbpe = instrucao.getNIXBPE();
        Integer enderecoDestino = Conversao.StrNumBinC2(instrucao.getEndereco());

        Integer valorMem = 0;

        if (nixbpe.startsWith("01")) { // IMEDIATO
            valorMem = enderecoDestino;
        } else {
            if (nixbpe.startsWith("11")) { // DIRETO
                enderecoDestino = calculaEnderecoDireto(enderecoDestino, nixbpe, registradores);
                System.out.println(enderecoDestino);
            }

            if (nixbpe.startsWith("10")) { // INDIRETO
                Endereco enderecoMemoria = memoria.getValor(enderecoDestino);
                enderecoDestino = Conversao.StrNumBinC2(enderecoMemoria.getEndereco());
            }

            System.err.println(memoria.getValor(enderecoDestino)); // Pegando endereço armazenado na posição
                                                                   // enderecoDestino
            System.err.println(memoria.getValor(enderecoDestino).getInstrucaoBinario());
            System.err.println("Valor no endereço " + enderecoDestino + "=>"
                    + memoria.getValor(enderecoDestino).getInstrucaoBinario());
            valorMem = Conversao.StrNumBinC2(memoria.getValor(enderecoDestino).getInstrucaoBinario());
        }

        registradores.setValor("S", valorMem);
        System.err.println(registradores.getValor("S"));
    }
}

// Registrador regS = registradores.getRegistrador("S");

// String nixbpq = instrucao.getNixbpq();

// System.out.println("A instrucao é: " + endereco.getPalavra().getInsHexa() +
// ".");
// System.out.println("Já que o opcode é = " + endereco.getPalavra().getOpcode()
// + ".");
// System.out.println("Essa instrucao tem indice: " + endereco.getIndice() +
// ".");

// if (nixbpq.equals("010000")) {
// System.out.println("A intruçao é LDS imediato, que insere o valor inteiro do
// binário "
// + endereco.getPalavra().getEnderecoBinario() + " no Registrador S.");
// String pegaDado = endereco.getPalavra().getEnderecoBinario();// copiou o
// endereço binário daquela palavra,
// // que na verdade é um dado.
// int dadoConvertido = Integer.parseInt(pegaDado, 2); // Converte para inteiro
// System.out.println("O valor no Registrador S será = " + dadoConvertido);
// regS.setNumeroInteiro(dadoConvertido);

// } else if (nixbpq.equals("110000")) {
// System.out.println("A intruçao é LDS direto, que insere o valor que está no
// endereço "
// + endereco.getPalavra().getEnderecoBinario() + " no Registrador S.");

// String enderecoDesejado = endereco.getPalavra().getEnderecoBinario();
// int enderecoDesejadoInt = Integer.parseInt(enderecoDesejado, 2);
// for (Endereco enderecoComparado : memoria.getMemoria()) {
// if (enderecoComparado.getEndDeci() == enderecoDesejadoInt) {
// // Quando encontra o endereço desejado, acessa o endereço e a instrução
// // associada
// String numeroBinario = enderecoComparado.getPalavra().getNumBin();
// int numeroConvertido = Integer.parseInt(numeroBinario, 2);
// System.out.println("O valor no endereço é = " + numeroConvertido);
// regS.setNumeroInteiro(numeroConvertido);
// break; // Sai do loop quando encontra o endereço
// }
// }
// }

// System.out.println("O valor no Registrador S = " + regS.getNumeroInteiro() +
// "\n");
// System.out.println("--------");
