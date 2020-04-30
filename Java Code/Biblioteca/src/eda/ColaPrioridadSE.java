/*
 * ColaPrioridadSE.java
 *
 * Created on 30 de octubre de 2007, 23:53
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package eda;

/**
 *
 * @author DDC Tecnicas de Programacion
 */
public class ColaPrioridadSE<T> extends ColaSE<T>{
    
    /** Creates a new instance of ColaPrioridadSE */
    public ColaPrioridadSE() {
        super();
    }
    
    @Override
    public void Adicionar(T x) 
    {
        NodoSE<T> cursor = this.frente, anterior = null;
        while(cursor != null && ((IPriorizable)cursor.getInfo()).getPrioridad() >= ((IPriorizable)x).getPrioridad())
        {
            anterior = cursor;
            cursor = cursor.getSiguiente();
        }
        NodoSE<T> nodo = new NodoSE<T>(x, cursor);
        if (anterior == null)
        {
            frente = nodo;
            if (fondo == null)
                fondo = nodo;
        }
        else
        {
            anterior.setSiguiente(nodo);
            if (cursor == null)
                fondo = nodo;
        }
    }
}
