package src.Utils;

public class Conversao {

    // Inteiro para Hexadecimal
    public static String intToHex(int number) {
        return Integer.toHexString(number);
    }

    // Inteiro para Binário
    public static String intToBin(int number) {
        return Integer.toBinaryString(number);
    }

    public static String intToBin(String number, int width) {
        String binary = Integer.toBinaryString(Integer.parseInt(number));
        return String.format("%" + width + "s", binary).replace(' ', '0');
    }

    // Hexadecimal para Inteiro
    public static int hexToInt(String hex) {
        return Integer.parseInt(hex, 16);
    }

    // Binário para Inteiro
    public static int binToInt(String bin) {
        return Integer.parseInt(bin, 2);
    }

    // Hexadecimal para Binário
    public static String hexToBin(String hex) {
        return intToBin(hexToInt(hex));
    }

    // Binário para Hexadecimal
    public static String binToHex(String bin) {
        return intToHex(binToInt(bin));
    }

    // Integer para String
    public static String integerToString(Integer number) {
        return String.valueOf(number);
    }

    // String para Hexadecimal
    public static String stringToHex(String string) {
        return intToHex(stringToInt(string));
    }

    // String contendo número em binário em complemento de 2 para Integer decimal
    public static Integer StrNumBinC2(String string) {
        String x = string;

        Integer resultado = Integer.parseInt(x, 2);

        if (x.startsWith("1")) {
            resultado = ~resultado; // inverte
            resultado += 1; // soma 1
            String t = Integer.toBinaryString(resultado); // converte para string
            t = t.substring(t.length() - x.length(), t.length()); // pega a mesma quantidade de bits que o numero
            resultado = 0 - Integer.parseInt(t, 2); // convert para integer
        }

        return resultado;
    }

    // String para Inteiro
    public static int stringToInt(String string) {
        return Integer.parseInt(string);
    }

    // expade um número binario, ex: 8bits para 16bits
    public static String expandeBinario(String binario, Integer bits) {
        return String.format("%" + bits + "s", binario).replace(' ', '0');
    }
}
