
package eda;

public class ColaSE <T> implements ICola<T>
{
    protected NodoSE<T> frente;
    protected NodoSE<T> fondo;
    
    public ColaSE() {
        frente = null;
        fondo = null;
    }
    
    public void Adicionar(T x) 
    {
        NodoSE<T> nuevo = new NodoSE<T>(x, null);
        if (Vacia()) {
            frente = nuevo;
            fondo = nuevo;
        } else {
            fondo.setSiguiente(nuevo);
            fondo = nuevo;
        }
    }
    
    public T Frente() throws ExceptionColaVacia 
    {
        if (Vacia())
            throw new ExceptionColaVacia();
        else
            return frente.getInfo();
    }
    
    public T Fondo() throws ExceptionColaVacia
    {
        if (Vacia())
            throw new ExceptionColaVacia();
        else
            return fondo.getInfo();
    }
    
    public T Extraer() throws ExceptionColaVacia
    {
        if (Vacia())
            throw new ExceptionColaVacia();
        else {
            NodoSE<T> cursor = frente;
            frente = frente.getSiguiente();
            return cursor.getInfo();
        }
    }
    
    public boolean Vacia() 
    {
        return (frente == null);
    }
}