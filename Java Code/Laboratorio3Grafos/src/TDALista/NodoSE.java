/*
 * NodoSE.java
 *
 * Created on 9 de octubre de 2007, 8:35
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package TDALista;


public class NodoSE<T> {
    protected T dato;
    protected NodoSE<T> siguiente;
    /** Creates a new instance of NodoSE */
    public NodoSE(T dato) {
        this(dato, null);
    }
    public NodoSE(T dato, NodoSE<T> siguiente) {
        this.dato = dato;
        this.siguiente = siguiente;
    }
    public T GetDato()
    {
        return dato;
    }
    public NodoSE<T> GetSiguiente()
    {
        return siguiente;
    }
    public void SetDato(T dato)
    {
        this.dato = dato;
    }
    public void SetSiguiente(NodoSE<T> siguiente)
    {
        this.siguiente = siguiente;
    }
}
