
package Error;

import Compilador.Source_Position;

/**
 *
 * @author EGH
 */



public class SyntacticError extends CompilerError{

    public SyntacticError(Source_Position position, String text)
    {
        super(position, text);
    }
}
