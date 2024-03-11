// package src.Montador;

// import src.Instrucoes.Instrucao;
// import src.Utils.Conversao;

// public class LinhaAssembly {
// private String linha;
// private String label;
// private String opcode;

// private String[] operandos; // Operandos extraidos da linha

// public LinhaAssembly(String linha) {
// this.linha = linha;
// setPropriedades();
// }

// private void setPropriedades() {

// }

// public static String getOpcode(String code) {
// return code.split(" ")[0];
// }

// public static String getBinarioOpcode(String nome) {
// String bin = "";
// try {
// if (nome.startsWith("+")) //
// Instrucao inst = Instrucoes.getInstrucaoPorNome(nome.substring(1));
// bin = Conversao.hexToBin().getOpcode();
// else {
// bin = Conversao.hexToBin(getInstrucaoPorNome(nome).getOpcode());
// }
// } catch (Exception e) {
// }
// return bin;
// }

// public static String[] getOperandos(String code) {
// String[] partes = code.split(" ");
// String[] operandos = new String[partes.length - 1];

// System.arraycopy(partes, 1, operandos, 0, operandos.length);
// return operandos;
// }

// }
