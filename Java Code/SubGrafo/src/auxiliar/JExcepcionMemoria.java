/*
 * ExcepcionMemoria.java
 *
 * Created on 26 de septiembre de 2006, 3:30
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package auxiliar;

/**
 *
 * @author lester
 */
public class JExcepcionMemoria extends RuntimeException {
    
    /** Creates a new instance of ExcepcionMemoria */
    public JExcepcionMemoria() {
        super("Se ha agotado la capacidad de almacenamiento.");
    }
    
}
