

package Analizador_Lexico;

/**
 *
 * @author EGH
 */


public class Scanning_Problem_Exception extends RuntimeException {

  
    
    Exception innerException;
    
    public Scanning_Problem_Exception() {
    }

   
    public Scanning_Problem_Exception(String msg) {
        super(msg);
    }
    
    
    public Scanning_Problem_Exception(Exception inner) {
        innerException = inner;
    }
    
    public Exception getInnerException()
    {
        return innerException;
    }
}
