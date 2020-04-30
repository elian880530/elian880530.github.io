package Errores;

import Compilador.SourcePosition;

/**
 *
 * @author EGH
 */
public class SyntacticError extends CompilerError {

    public SyntacticError(SourcePosition position, String text) {
        super(position, text);
    }
}
