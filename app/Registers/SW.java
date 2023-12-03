package app.Registers;

public final class SW {

    static Integer value = 0;

    public static Integer getValue() {
        return value;
    }

    public static void setValue(Integer value) {
        SW.value = value;
    }

    public static void reset() {
        SW.value = 0;
    }
}