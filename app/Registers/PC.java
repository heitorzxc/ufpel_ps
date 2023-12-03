package app.Registers;

public final class PC {

    static Integer value = 0;

    public static Integer getValue() {
        return value;
    }

    public static void setValue(Integer value) {
        PC.value = value;
    }

    public static void reset() {
        PC.value = 0;
    }

}
