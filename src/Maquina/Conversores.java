package src.Maquina;
public class Conversores {

    // Inteiro para Hexadecimal
    public static String intToHex(int number) {
        return Integer.toHexString(number);
    }

    // Inteiro para Binário
    public static String intToBin(int number) {
        return Integer.toBinaryString(number);
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

    // Inteiro para String
    public static String intToString(int number) {
        return String.valueOf(number);
    }

    // String para Inteiro
    public static int stringToInt(String string) {
        return Integer.parseInt(string);
    }

    // Hexadecimal para String
    public static String hexToString(String hex) {
        return intToString(hexToInt(hex));
    }

    // String para Hexadecimal
    public static String stringToHex(String string) {
        return intToHex(stringToInt(string));
    }

    // Binário para String
    public static String binToString(String bin) {
        return intToString(binToInt(bin));
    }

    // String para Binário
    public static String stringToBin(String string) {
        return intToBin(stringToInt(string));
    }

}
