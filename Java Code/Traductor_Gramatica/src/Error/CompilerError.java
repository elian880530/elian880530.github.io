

package Error;

import Compilador.Source_Position;

/**
 *
 * @author EGH
 */



public class CompilerError{

    private Source_Position position;
    private String text;

    public CompilerError(Source_Position position, String text)
    {
        this.position = position;
        this.text = text;
    }
    
    public String Text()
    {
        return text;
    }

    public Source_Position Position()
    {
       return position;
    }     
}
