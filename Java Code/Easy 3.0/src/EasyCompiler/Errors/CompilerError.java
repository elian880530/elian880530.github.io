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

public class CompilerError{

    private SourcePosition position;
    private String text;

    public CompilerError(SourcePosition position, String text)
    {
        this.position = position;
        this.text = text;
    }
    
    public String Text()
    {
        return text;
    }

    public SourcePosition Position()
    {
       return position;
    }     
}
