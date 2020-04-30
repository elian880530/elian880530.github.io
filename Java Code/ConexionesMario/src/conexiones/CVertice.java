/*
 * CVertice.java
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

public class CVertice<T>
{
    private T dato;
    private boolean visitado;
    private int id;
    
    public CVertice(T dato,int id) 
    {
        this.dato = dato;
        this.visitado = false;
        this.id = id;
    }
    
    public T getDato() 
    {
        return dato;
    }
    
    public boolean EstaVisitado() 
    {
        return visitado;
    }
    
    public int getId() 
    {
        return id;
    }
    
    public void setDato(T dato) 
    {
        this.dato = dato;
    }
    
    public void setVisitado(boolean visitado) 
    {
        this.visitado = visitado;
    }
}
