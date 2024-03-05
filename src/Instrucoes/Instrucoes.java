package src.Instrucoes;

import java.util.HashMap;

public class Instrucoes {
    private static HashMap<String, Instrucao> instrucaoPorOpcode;
    private static HashMap<String, Instrucao> instrucaoPorNome;

    public Instrucoes() {

    }

    public static void inicializaInstrucoes() {
        instrucaoPorOpcode = new HashMap<>();
        instrucaoPorNome = new HashMap<>();

        setInstrucao(new LDA());
        setInstrucao(new LDB());
        setInstrucao(new LDL());
        setInstrucao(new LDS());
        setInstrucao(new LDT());
        setInstrucao(new ADD());
        setInstrucao(new SUB());
        setInstrucao(new DIV());
        setInstrucao(new MUL());
        setInstrucao(new MULR());
        setInstrucao(new DIVR());
        setInstrucao(new ADDR());
        setInstrucao(new SUBR());
        setInstrucao(new RMO());
        setInstrucao(new STA());
        setInstrucao(new CLEAR());
    }

    public static void setInstrucao(Instrucao instrucao) {
        instrucaoPorOpcode.put(instrucao.getOpcode(), instrucao);
        instrucaoPorNome.put(instrucao.getNome(), instrucao);
    }

    public static String getOpcodePorNome(String nome) {
        return instrucaoPorNome.get(nome).getOpcode();
    }

    public static Instrucao getInstrucaoPorOpcode(String opcode) {
        return instrucaoPorOpcode.get(opcode);
    }

    // public static void executaInstrucao(Instrucao instrucao, Endereco endereco,
    // BancoRegistradores registradores,Memoria memoria) {
    // InstrucaoExecutavel inst = getInstrucaoPorOpcode(instrucao.getOpcode());
    // inst.executar(instrucao, endereco, registradores, memoria);
    // }
}
