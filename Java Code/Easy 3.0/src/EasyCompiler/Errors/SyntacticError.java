/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package EasyCompiler.Errors;

/**
 *
 * @author tomaso
 */

import EasyCompiler.SourcePosition;

public class SyntacticError extends CompilerError{

    public SyntacticError(SourcePosition position, String text)
    {
        super(position, text);
    }
}
