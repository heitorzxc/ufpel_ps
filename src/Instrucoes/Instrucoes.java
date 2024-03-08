package src.Instrucoes;

import java.util.HashMap;

import src.Exceptions.*;

public class Instrucoes {
    private static HashMap<String, Instrucao> instrucaoPorOpcode;
    private static HashMap<String, Instrucao> instrucaoPorNome;

    // private static final String INDIRETO = "indireto";
    // private static final String DIRETO = "direto";
    // private static final String IMEDIATO = "imediato";

    private static final char INDIRETO = '@';
    private static final char IMEDIATO = '#';

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

    public static Instrucao getInstrucaoPorNome(String nome) throws InvalidInstructionError {
        try {
            return instrucaoPorNome.get(nome);
        } catch (Exception e) {
            throw new InvalidInstructionError("Operação: " + nome + "é inválida.");
        }
    }

    public static Instrucao getInstrucaoPorOpcode(String opcode) throws InvalidInstructionError {
        try {
            return instrucaoPorOpcode.get(opcode);
        } catch (Exception e) {
            throw new InvalidInstructionError("Opcode: " + opcode + "é inválido.");
        }
    }

    public static Boolean isValida(String code) {
        String opcode = getOpcode(code);
        Instrucao operacao;

        if (opcode.startsWith("+")) {
            opcode = opcode.substring(1);
        }

        try {
            operacao = getInstrucaoPorNome(opcode); // "valida" a operacao indeiretamente
        } catch (Exception e) {
            return false;
        }

        String[] operandos = getOperandos(code);

        return operandos.length == operacao.getNumeroDeOperandos(); // valida o numero de operandos
    }

    public static String getOpcode(String code) {
        return code.split(" ")[0];
    }

    public static String[] getOperandos(String code) {
        String[] partes = code.split(" ");
        String[] operandos = new String[partes.length - 2];

        System.arraycopy(partes, 1, operandos, 0, operandos.length);
        return operandos;
    }

    public static String getNixbpe(String code) {
        String nix;

        String[] operando = getOperandos(code);

        switch (operando[0].charAt(0)) { // tipos de enderecamento
            case IMEDIATO:
                nix = "01000";
                break;

            case INDIRETO:
                nix = "10000";
                break;

            default:
                nix = "11000";
                break;
        }

        if (code.startsWith("+"))
            return nix + "1";

        return nix + "0";
    }

}
