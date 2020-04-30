/*
 * ExceptionListaVacia.java
 *
 * Created on 31 de octubre de 2007, 0:40
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package eda;

/**
 *
 * @author DDC Técnicas de Programación
 */
public class ExceptionListaVacia extends RuntimeException {
    
    /** Creates a new instance of ExceptionListaVacia */
    public ExceptionListaVacia() {
        super("Lista Vacia");
    }
}
