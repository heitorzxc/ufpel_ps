package src.Instrucoes;

import java.util.HashMap;
import src.Maquina.Endereco;
import src.Maquina.Memoria;
import src.Registradores.BancoRegistradores;

public class Instrucoes {
    private HashMap<String, InstrucaoExecutavel> instrucoes;
    private HashMap<String, String> opcodesPorNome;
    
    public Instrucoes(){
        instrucoes = new HashMap<>();
        opcodesPorNome = new HashMap<>();
        inicializaInstrucoes("0", new LDA(), "LDA");
        inicializaInstrucoes("68", new LDB(), "LDB");
        inicializaInstrucoes("8", new LDL(), "LDL");
        inicializaInstrucoes("6C", new LDS(), "LDS");
        inicializaInstrucoes("74", new LDT(), "LDT");
        inicializaInstrucoes("18", new ADD(), "ADD");
        inicializaInstrucoes("1C", new SUB(), "SUB");
        inicializaInstrucoes("24", new DIV(), "DIV");
        inicializaInstrucoes("20", new MUL(), "MUL");
        inicializaInstrucoes("98", new MULR(), "MULR");
        inicializaInstrucoes("9C", new DIVR(), "DIVR");
        inicializaInstrucoes("90", new ADDR(), "ADDR");
        inicializaInstrucoes("94", new SUBR(), "SUBR");
        inicializaInstrucoes("AC", new RMO(), "RMO");
        inicializaInstrucoes("0C", new STA(), "STA");
        inicializaInstrucoes("4", new CLEAR(), "CLEAR");
    }

    public void inicializaInstrucoes(String opcode, InstrucaoExecutavel instrucao, String nome){
        instrucoes.put(opcode, instrucao);
        opcodesPorNome.put(nome, opcode);
    }

    public String getOpcodePorNome(String nome) {
        return opcodesPorNome.get(nome);
    }

    public InstrucaoExecutavel getInstrucaoPorOpcode(String opcode){
        return instrucoes.get(opcode);
    }

    public void executaInstrucao(Instrucao instrucao, Endereco endereco, BancoRegistradores registradores, Memoria memoria){
        InstrucaoExecutavel inst = getInstrucaoPorOpcode(instrucao.getOpcode());
        inst.executar(instrucao, endereco, registradores, memoria);
    }
}
