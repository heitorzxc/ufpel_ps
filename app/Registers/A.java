package app.Registers;

public final class A {

    static Integer value = 0;

    public static Integer getValue() {
        return value;
    }

    public static void setValue(Integer value) {
        A.value = value;
    }

    public static void reset() {
        A.value = 0;
        System.out.println("Acumulador Zerado!");
    }

    public static String printAcumulador(){
    return "Valor no Acumulador(A)  = " +getValue()
            + "\n---------------------------";
    }
}