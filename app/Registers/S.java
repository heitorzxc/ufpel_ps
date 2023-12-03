package app.Registers;

public final class S {

    static Integer value = 0;

    public static Integer getValue() {
        return value;
    }

    public static void setValue(Integer value) {
        S.value = value;
    }

    public static void reset() {
        S.value = 0;
    }

    public static String printAcumulador(){
        return "Valor no Registrador S  = " +getValue()
                + "\n---------------------------";
        }
}