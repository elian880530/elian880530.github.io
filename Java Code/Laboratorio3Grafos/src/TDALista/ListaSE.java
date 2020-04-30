/*
 * ListaSE.java
 *
 * Created on 9 de octubre de 2007, 8:32
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package TDALista;

import java.util.logging.Level;
import java.util.logging.Logger;


public class ListaSE<T> implements ILista<T>
{
    NodoSE<T> cabeza;
    int longitud;
    /** Creates a new instance of ListaSE */
    public ListaSE() 
    {
        cabeza = null;
        longitud = 0;
    }
    
    public void Adicionar(T elemento) 
    {
        NodoSE<T> nodo = new NodoSE(elemento, null);
        if(Vacia())
            cabeza = nodo;
        else
        {
            NodoSE<T> cursor = cabeza;
            while(cursor.GetSiguiente() != null)
                cursor = cursor.GetSiguiente();
            cursor.SetSiguiente(nodo);
        }
        longitud++;
    }

    public void Eliminar(int pos)
    {
        if(Vacia()) throw new Exception_ListaVacia();
        if(pos < 0 || pos >= Longitud()) throw new Exception_PosFueraDeRango(pos);
        if(pos == 0)
            cabeza = cabeza.GetSiguiente();
        else
        {
            int pos_cursor = 0;
            NodoSE<T> cursor = cabeza;
            while(pos_cursor < pos - 1)
            {
                cursor = cursor.GetSiguiente();
                pos_cursor++;
            }
            cursor.SetSiguiente(cursor.GetSiguiente().GetSiguiente());
        }
        longitud --;
    }

    public void Insertar(T elemento, int pos) 
    {
        if(pos < 0 || pos >= Longitud()) throw new Exception_PosFueraDeRango(pos);
        NodoSE<T> nodo = new NodoSE(elemento, null);
        if(pos == 0)
        {
            nodo.SetSiguiente(cabeza);
            cabeza = nodo;
        }
        else
        {
            int pos_cursor = 0;
            NodoSE<T> cursor = cabeza;
            while(pos_cursor < pos - 1)
            {
                cursor = cursor.GetSiguiente();
                pos_cursor++;
            }
            nodo.SetSiguiente(cursor.GetSiguiente()); 
            cursor.SetSiguiente(nodo);
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
        NodoSE<T> cursor = cabeza;
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
            NodoSE<T> Cursor = cabeza;
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
