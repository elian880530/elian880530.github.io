/*
 * NodoDE.java
 *
 * Created on 10 de octubre de 2007, 1:28
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package TDALista;

/**
 *
 * @author Jaisse
 */
public class NodoDE<T> {
    protected T dato;
    protected NodoDE<T> anterior;
    protected NodoDE<T> siguiente;
    /** Creates a new instance of NodoDE */
    public NodoDE(T dato) {
        this(null, dato, null);
    }
    public NodoDE(NodoDE<T> anterior, T dato, NodoDE<T> siguiente) {
        this.dato = dato;
        this.anterior = anterior;
        this.siguiente = siguiente;
    }
    public T GetDato()
    {
        return dato;
    }
     public NodoDE<T> GetAnterior()
    {
        return anterior;
    }
    public NodoDE<T> GetSiguiente()
    {
        return siguiente;
    }
    public void SetDato(T dato)
    {
        this.dato = dato;
    }
    public void SetAnterior(NodoDE<T> anterior)
    {
        this.anterior = anterior;
    }
    public void SetSiguiente(NodoDE<T> siguiente)
    {
        this.siguiente = siguiente;
    }
    
}
