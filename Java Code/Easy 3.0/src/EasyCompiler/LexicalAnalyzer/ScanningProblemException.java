/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package EasyCompiler.LexicalAnalyzer;

/**
 *
 * @author tomaso
 */

//el único objetivo de esta clase es hacer que encapsular la NO RuntimeException
//que pude lanzarse al leer el scanner y acomodarme la programacion del parser
public class ScanningProblemException extends RuntimeException {

    /**
     * Creates a new instance of <code>ScanningProblemException</code> without detail message.
     */
    
    Exception innerException;
    
    public ScanningProblemException() {
    }

    /**
     * Constructs an instance of <code>ScanningProblemException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public ScanningProblemException(String msg) {
        super(msg);
    }
    
    
    public ScanningProblemException(Exception inner) {
        innerException = inner;
    }
    
    public Exception getInnerException()
    {
        return innerException;
    }
}
