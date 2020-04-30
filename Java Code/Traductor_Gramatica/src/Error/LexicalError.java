/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Error;

import Compilador.Source_Position;

/**
 *
 * @author EGH
 */



public class LexicalError extends CompilerError{
    
    public LexicalError(Source_Position position, String text)
    {
        super(position, text);
    }
}
