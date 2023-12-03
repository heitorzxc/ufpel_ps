package app.Registers;

public final class L {

    static Integer value = 0;

    public static Integer getValue() {
        return value;
    }

    public static void setValue(Integer value) {
        L.value = value;
    }

    public static String printAcumulador(){
        return "Valor no Registrador L = " +getValue()
            + "\n---------------------------";
    }
}