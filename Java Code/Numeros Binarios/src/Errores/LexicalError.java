package Errores;

/**
 *
 * @author EGH
 */
import Compilador.SourcePosition;

public class LexicalError extends CompilerError {

    public LexicalError(SourcePosition position, String text) {
        super(position, text);
    }
}
