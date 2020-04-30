/*
 * ExceptionColaVacia.java
 *
 * Created on 31 de octubre de 2007, 0:49
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package eda;

/**
 *
 * @author DDC Tecnicas de Programacion
 */
public class ExceptionColaVacia extends RuntimeException {
    
    /** Creates a new instance of ExceptionColaVacia */
    public ExceptionColaVacia() {
        super("Cola Vacia");
    }
    
}
