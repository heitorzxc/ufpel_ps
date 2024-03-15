package src.Instrucoes;

import java.util.HashMap;

import src.Exceptions.InvalidInstructionError;
import src.Utils.Conversao;

public class Instrucoes {
    private static HashMap<String, Instrucao> instrucaoPorOpcode;
    private static HashMap<String, Instrucao> instrucaoPorNome;

    // private static final String INDIRETO = "indireto";
    // private static final String DIRETO = "direto";
    // private static final String IMEDIATO = "imediato";

    private Instrucoes() { // impedir de instanciar

    }

    public static void inicializaInstrucoes() {
        instrucaoPorOpcode = new HashMap<>();
        instrucaoPorNome = new HashMap<>();
        
        setInstrucao(new ADD());
        setInstrucao(new ADDR());
        setInstrucao(new AND());
        setInstrucao(new CLEAR());
        setInstrucao(new DIV());
        setInstrucao(new DIVR());
        setInstrucao(new J());
        setInstrucao(new JEQ());
        setInstrucao(new JGT());
        setInstrucao(new JLT());
        setInstrucao(new JSUB());
        setInstrucao(new LDA());
        setInstrucao(new LDB());
        setInstrucao(new LDL());
        setInstrucao(new LDS());
        setInstrucao(new LDT());
        setInstrucao(new LDX());
        setInstrucao(new MUL());
        setInstrucao(new MULR());
        setInstrucao(new OR());
        setInstrucao(new RMO());
        setInstrucao(new RSUB());
        setInstrucao(new SHIFTL());
        setInstrucao(new SHIFTR());
        setInstrucao(new STA());
        setInstrucao(new STB());
        setInstrucao(new STL());
        setInstrucao(new STS());
        setInstrucao(new STT());
        setInstrucao(new SUB());
        setInstrucao(new SUBR());
      
    }

    public static void setInstrucao(Instrucao instrucao) {
        instrucaoPorOpcode.put(instrucao.getOpcode(), instrucao);
        instrucaoPorNome.put(instrucao.getNome(), instrucao);
    }

    public static String getOpcodePorNome(String nome) {
        return instrucaoPorNome.get(nome).getOpcode();
    }

    public static Instrucao getInstrucaoPorNome(String nome) {
        try {
            return instrucaoPorNome.get(nome);
        } catch (Exception e) {
            return null;
        }
    }

    public static Instrucao getInstrucaoPorOpcode(String opcode) {
        try {
            return instrucaoPorOpcode.get(opcode);
        } catch (Exception e) {
            return null;
        }
    }

}
