/*
 * ExcepcionMemoria.java
 *
 * Created on 26 de septiembre de 2006, 3:30
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package TDALista;

/**
 *
 * @author lester
 */
public class ExcepcionMemoria extends RuntimeException {
    
    /** Creates a new instance of ExcepcionMemoria */
    public ExcepcionMemoria() {
        super("Se ha agotado la capacidad de almacenamiento.");
    }
    
}
