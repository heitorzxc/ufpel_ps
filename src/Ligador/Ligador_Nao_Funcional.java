/*
 * package src.Ligador;
 * 
 * import java.util.HashMap;
 * import java.util.Map;
 * 
 * public class LigadorPasso1 {
 * private int PROGADDR; // ENDEREÇO DE INÍCIO DO PROGRAMA
 * private int CSADDR; // ENDEREÇO DE INÍCIO DA SEÇÃO DE CONTROLE ATUAL
 * private Map<String, Integer> ESTAB; // TABELA DE SÍMBOLOS EXTERNOS
 * 
 * public LigadorPasso1() {
 * ESTAB = new HashMap<>();
 * // PROGADDR DEVE SER FORNECIDO PELO SISTEMA OPERACIONAL, AQUI É SÓ UM
 * // PLACEHOLDER
 * PROGADDR = 0;
 * CSADDR = PROGADDR;
 * }
 * 
 * public void processaEntrada(List<Registro> registros) { // SUPONDO QUE EXISTE
 * UMA CLASSE PARA REPRESENTAR OS REGISTROS
 * // LIDOS
 * for (Registro registro : registros) {
 * if (registro.getTipo().equals("H")) { // CABEÇALHO DA SEÇÃO DE CONTROLE
 * CSADDR = PROGADDR;
 * // AQUI, VOCÊ PRECISA DEFINIR CSLTH COM BASE NO REGISTRO LIDO
 * int CSLTH = registro.getTamanhoSecaoControle();
 * 
 * if (ESTAB.containsKey(registro.getNomeSecao())) {
 * // DEFINE O ERRO DE SÍMBOLO EXTERNO DUPLICADO
 * } else {
 * ESTAB.put(registro.getNomeSecao(), CSADDR);
 * }
 * 
 * for (Registro reg : registro.getRegistrosInternos()) {
 * if (!reg.getTipo().equals("E")) {
 * if (reg.getTipo().equals("D")) {
 * // PROCESSA REGISTROS DE DEFINIÇÃO
 * for (Simbolo simbolo : reg.getSimbolos()) {
 * if (ESTAB.containsKey(simbolo.getNome())) {
 * // DEFINE O ERRO DE SÍMBOLO EXTERNO DUPLICADO
 * } else {
 * ESTAB.put(simbolo.getNome(), CSADDR + simbolo.getEndereco());
 * }
 * }
 * }
 * } else {
 * // SAIR DO LOOP INTERNO SE FOR O REGISTRO DE FIM DE SEÇÃO 'E'
 * break;
 * }
 * }
 * CSADDR += CSLTH; // PREPARA O ENDEREÇO PARA A PRÓXIMA SEÇÃO DE CONTROLE
 * }
 * }
 * }
 * 
 * }
 * 
 * public class LigadorPasso2 {
 * private int PROGADDR; // ENDEREÇO DE INÍCIO DO PROGRAMA
 * private int CSADDR; // ENDEREÇO DE INÍCIO DA SEÇÃO DE CONTROLE ATUAL
 * private int EXECADDR; // ENDEREÇO DE EXECUÇÃO DO PROGRAMA
 * private Map<String, Integer> ESTAB; // Tabela de Símbolos Externos
 * 
 * public LigadorPasso2(Map<String, Integer> estab) {
 * this.ESTAB = estab;
 * // PROGADDR DEVE SER FORNECIDO PELO SISTEMA OPERACIONAL, AQUI É SÓ UM
 * PLACEHOLDER
 * PROGADDR = 0;
 * CSADDR = PROGADDR;
 * EXECADDR = PROGADDR;
 * }
 * 
 * public void processaEntrada(List<Registradores> Registradores) { // USANDO A
 * CLASSE REGISTRADORES
 * 
 * // loop no banco de registradores para encontrar registrador H (HEADER)
 * 
 * //for (Registradores registrador : registrador) {
 * //if (BancoRegistradores.getInstance..getTipo().equals("H")) { // CABEÇALHO
 * DA SEÇÃO DE CONTROLE
 * CSADDR = PROGADDR; // ENDEREÇO DO CONTROLE DE SEÇÃO RECEBE O ENDEREÇO DO
 * PROGRAMA
 * // DEFINIR CSLTH COM BASE NO REGISTRO CAPTURADO
 * int CSLTH = registro.getTamanhoSecaoControle();
 * 
 * for (Registradores registradores : registrador.getNome()) {
 * if (!registrador.getNome ().equals("E")) {
 * if (registrador.getNome ().equals("T")) {
 * // CONVERTER O CÓDIGO OBJETO EM FORMA DE CARACTERE PARA REPRESENTAÇÃO
 * INTERNA, SE NECESSÁRIO PARA DEPOIS ENTÃO MOVER O CÓDIGO OBJETO DO REGISTRO
 * PARA A LOCALIZAÇÃO (CSADDR + ENDEREÇO ESPECIFICADO)
 * } else if (registrador.getNome ().equals("M")) {
 * // MODIFICA O ENDEREÇO CONFORME NECESSÁRIO, BASEANDO-SE NA ESTAB (TABELA DE
 * SIMBOLOS EXTERNOS)
 * }
 * // REGISTRO HEADER, DEFINE, REFER, TEXT, END, MODIFICATION
 * }
 * }
 * }
 * }
 * }
 * 
 * 
 * // PROGADDR = 0
 * // ESTAB -> used to store the name and address of each external symbol in the
 * // set of control sections being loaded. The table also often indicates in
 * which
 * // control section the symbol is defined. A hashed organization is typically
 * // used for this table.
 */