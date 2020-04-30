/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package EasyCompiler.Errors;

/**
 *
 * @author tomaso
 */

import EasyCompiler.*;

public class LexicalError extends CompilerError{
    
    public LexicalError(SourcePosition position, String text)
    {
        super(position, text);
    }
}
