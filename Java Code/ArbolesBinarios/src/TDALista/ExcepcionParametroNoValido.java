/*
 * ExcepcionParametroNoValido.java
 *
 * Created on 26 de septiembre de 2006, 3:25
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package TDALista;

/**
 *
 * @author lester
 */
public class ExcepcionParametroNoValido extends RuntimeException {
    
    /** Creates a new instance of ExcepcionParametroNoValido */
    public ExcepcionParametroNoValido() {
        super("Parametro fuera de rango.");
    }
    
}
