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
}