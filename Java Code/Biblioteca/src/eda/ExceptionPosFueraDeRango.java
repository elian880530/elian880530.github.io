/*
 * ExceptionPosFueraDeRango.java
 *
 * Created on 31 de octubre de 2007, 0:42
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package eda;

/**
 *
 * @author DDC Técnicas de Programación
 */
public class ExceptionPosFueraDeRango extends RuntimeException {
    
    /** Creates a new instance of ExceptionPosFueraDeRango */
    public ExceptionPosFueraDeRango() {
        super("Posición fuera de rango");
    }
    
}
