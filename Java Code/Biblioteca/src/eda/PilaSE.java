package eda;

/* Clase PilaSE */
/* Implementa una Pila con nodos simplemente enlazados */
public class PilaSE<T> implements IPila<T> {
	/* Atributo Tope */
    /* Almacena la direccion del nodo que contiene el elemento tope */
    protected NodoSE<T> top;

    /* Constructor */
    public PilaSE() 
    {
        top = null;    
    }

    public void Apilar(T x) 
    {
        NodoSE<T> nuevo = new NodoSE<T>(x, top);
        top = nuevo;
    }

    public T Extraer() throws ExceptionPilaVacia
    {
        if (!Vacia())
        {
            T x = top.getInfo();
            top = top.getSiguiente();
            return x;
        }
        else throw new ExceptionPilaVacia();
    }

    public T Tope() 
    {
        return top.getInfo();
    }

    public boolean Vacia() 
    {
        return (top == null);
    }
}
