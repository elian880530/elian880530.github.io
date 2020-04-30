/*
 * ListaDE.java
 *
 * Created on 10 de octubre de 2007, 1:35
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package TDALista;

/**
 *
 * @author Milena
 */
public class ListaDE<T> implements ILista<T> {
    
    NodoDE<T> cabeza;
    int longitud;
    /** Creates a new instance of ListaDE */
    public ListaDE() 
    {
        cabeza = null;
        longitud = 0;
    }
    
    public void Adicionar(T elemento) 
    {
        NodoDE<T> nodo = new NodoDE(null, elemento, null);
        if(Vacia())
            cabeza = nodo;
        else
        {
            NodoDE<T> cursor = cabeza;
            while(cursor.GetSiguiente() != null)
                cursor = cursor.GetSiguiente();
            nodo.SetAnterior(cursor);
            cursor.SetSiguiente(nodo);// o lo que es lo mismo nodo.GetAnterior().SetSiguiente(nodo);
        }
        longitud++;
    }
    
    public void Eliminar(int pos)
    {
        if(Vacia()) throw new Exception_ListaVacia();
        if(pos < 0 || pos >= Longitud()) throw new Exception_PosFueraDeRango(pos);
        if(pos == 0)
        {
            cabeza = cabeza.GetSiguiente();
            if(cabeza != null)
                cabeza.SetAnterior(null);
        }
        else
        {
            int pos_cursor = 0;
            NodoDE<T> cursor = cabeza;
            while(pos_cursor < pos - 1)
            {
                cursor = cursor.GetSiguiente();
                pos_cursor++;
            }
            cursor.SetSiguiente(cursor.GetSiguiente().GetSiguiente());
            if(cursor.GetSiguiente() != null)
                cursor.GetSiguiente().SetAnterior(cursor);
        }
        longitud --;
    }

    public void Insertar(T elemento, int pos) 
    {
        if(pos < 0 || pos >= Longitud()) throw new Exception_PosFueraDeRango(pos);
        NodoDE<T> nodo = new NodoDE(null, elemento, null);
        if(pos == 0)
        {
            nodo.SetSiguiente(cabeza);
            nodo.GetSiguiente().SetAnterior(nodo);
            cabeza = nodo;
        }
        else
        {
            int pos_cursor = 0;
            NodoDE<T> cursor = cabeza;
            while(pos_cursor < pos - 1)
            {
                cursor = cursor.GetSiguiente();
                pos_cursor++;
            }
            nodo.SetAnterior(cursor);
            nodo.SetSiguiente(cursor.GetSiguiente()); 
            nodo.GetAnterior().SetSiguiente(nodo);
            nodo.GetSiguiente().SetAnterior(nodo);
        }
        longitud++;
    }

    public int Longitud() {
        return longitud;
    }

    public T Obtener(int pos) 
    {
        if(pos < 0 || pos >= Longitud()) throw new Exception_PosFueraDeRango(pos);
        int pos_cursor = 0;
        NodoDE<T> cursor = cabeza;
        while(pos_cursor < pos)
        {
            cursor = cursor.GetSiguiente();
            pos_cursor++;
        }
        return cursor.GetDato();
    }

    public boolean Vacia() {
        return cabeza == null;
    }
    
    @SuppressWarnings("empty-statement")
    public int Buscar(T x) 
    {
        try {
            if (cabeza == null) {
                throw new Exception("Lista Vacia");
            }
            if (cabeza.dato.equals(x)) {
                return 0;
            }
            NodoDE<T> Cursor = cabeza;
            int pos = 0;
            while (Cursor != null && !Cursor.GetDato().equals(x)) {
                Cursor = Cursor.GetSiguiente();
                pos++;
            }
            if (Cursor != null) {
                return pos;
            }
        } 
        catch (Exception ex) {
           ;
        }
        return -1;
    }

    
}
