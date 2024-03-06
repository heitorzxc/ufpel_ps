package src.Instrucoes;

import src.Memoria.Endereco;
import src.Memoria.Memoria;
import src.Registradores.BancoRegistradores;
import src.Utils.Conversao;

public class JGT extends Instrucao {
    public JGT() {
        super("JGT", "34", 1); 
    }

    public void executar(Endereco instrucao, BancoRegistradores registradores, Memoria memoria) throws Exception {
        if (registradores.getValor("SW") > 0) { // é isso mesmo?
            Integer enderecoDestino = Conversao.StrNumBinC2(instrucao.getEndereco());
            registradores.setValor("PC", enderecoDestino);
        }
    }
}
