package Compilador;

/**
 *
 * @author EGH
 */
public class InOut {

    private static EasyConsole console = new DefaultEasyConsole();

    public static void Write(String format, Object[] args) {
        console.Write(format, args);
    }

    public static String Read() {
        return console.Read();
    }

    public static void SetConsole(EasyConsole newConsole) {
        console = newConsole;
    }
}
