/*
 * ExcepcionFueraDeRango.java
 *
 * Created on 21 de octubre de 2006, 13:04
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package VisualClass;

/**
 *
 * @author lester
 */
public class ExcepcionFueraDeRango extends RuntimeException {
    
    /** Creates a new instance of ExcepcionFueraDeRango */
    public ExcepcionFueraDeRango() {
        super("Movimiento fuera de rango");
    }
    
}
