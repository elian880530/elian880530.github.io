/*
 * CNodo.java
 *
 *  Created on 15 de enero de 2009, 0:22
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package conexiones;

/**
 *
 * @author eghernandez
 */

public class CNodo<T>
{
    private T dato;
    private CNodo<T> siguiente;
    
    public CNodo(T dato, CNodo<T> siguiente) 
    {
        this.dato = dato;
        this.siguiente = siguiente;
    }
    
    public T getDato()
    {
        return dato;
    }
    
    public void setDato(T dato)
    {
        this.dato = dato;
    }
    
    public CNodo<T> getSiguiente()
    {
        return siguiente;
    }
    
    public void setSiguiente(CNodo<T> siguiente)
    {
        this.siguiente = siguiente;
    }
}
