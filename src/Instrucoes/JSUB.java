package src.Instrucoes;

import src.Memoria.Endereco;
import src.Memoria.Memoria;
import src.Registradores.BancoRegistradores;
import src.Utils.Conversao;

public class JSUB extends Instrucao {
    public JSUB() {
        super("JSUB", "48", 1); 
    }

    public void executar(Endereco instrucao, BancoRegistradores registradores, Memoria memoria) throws Exception {
        Integer enderecoRetorno = registradores.getValor("PC");
        registradores.setValor("L", enderecoRetorno);

        Integer enderecoDestino = Conversao.StrNumBinC2(instrucao.getEndereco());
        registradores.setValor("PC", enderecoDestino); 
    }
}
