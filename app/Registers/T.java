package app.Registers;

public final class T {

    static Integer value = 0;

    public static Integer getValue() {
        return value;
    }

    public static void setValue(Integer value) {
        T.value = value;
    }

    public static void reset() {
        T.value = 0;
    }

    public static String printAcumulador(){
        return "Valor no Registrador T  = " +getValue()
                + "\n---------------------------";
    }

}