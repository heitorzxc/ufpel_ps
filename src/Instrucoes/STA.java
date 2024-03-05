package src.Instrucoes;

import src.Memoria.Endereco;
import src.Memoria.Memoria;
import src.Registradores.BancoRegistradores;

/*******
 * ADD *
 *******/
public class STA extends Instrucao {
    public STA() {
        super("STA", "0C", 2);
    }

    public void executar(Endereco instrucao, BancoRegistradores registradores, Memoria memoria) {
        // Registrador regA = registradores.getRegistrador("A");

        // // String nixbpq = instrucao.getNixbpq();

        // String enderecoDesejado = endereco.getPalavra().getEnderecoBinario();
        // int enderecoDesejadoInt = Integer.parseInt(enderecoDesejado, 2);

        // String valorRegistrador = String.format("%24s",
        // Integer.toBinaryString(regA.getNumeroInteiro())).replace(' ',
        // '0');
        // valorRegistrador = String.format("%24s", valorRegistrador).replace(' ', '0')
        // .substring(valorRegistrador.length() - 24);

        // for (Endereco enderecoComparado : memoria.getMemoria()) {
        // if (enderecoComparado.getEndDeci() == enderecoDesejadoInt) {
        // // Quando encontra o endereço desejado, modifica a instrução com o valor do
        // // registrador
        // Instrucao salvarInstrucao = enderecoComparado.getPalavra();
        // salvarInstrucao.setNumBin(valorRegistrador);

        // // Imprime a instrução modificada
        // System.out.println("Instrucao modificada no endereço " + enderecoDesejado +
        // ": "
        // + salvarInstrucao.getNumBin() + " em inteiro e " + regA.getNumeroInteiro() +
        // ".");
        // break; // Sai do loop quando encontra o endereço
        // }
        // }
    }
}
