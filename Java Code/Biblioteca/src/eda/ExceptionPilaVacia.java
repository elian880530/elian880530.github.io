/*
 * ExceptionPilaVacia.java
 *
 * Created on 31 de octubre de 2007, 0:52
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package eda;

/**
 *
 * @author DDC Técnicas de Programación
 */
public class ExceptionPilaVacia extends RuntimeException {
    
    /** Creates a new instance of ExceptionPilaVacia */
    public ExceptionPilaVacia() {
         super("Pila Vacia");
    }
    
}
