package Errores;

/**
 *
 * @author EGH
 */
import Compilador.SourcePosition;

public class CompilerError {

    private SourcePosition position;
    private String text;

    public CompilerError(SourcePosition position, String text) {
        this.position = position;
        this.text = text;
    }

    public String Text() {
        return text;
    }

    public SourcePosition Position() {
        return position;
    }
}
