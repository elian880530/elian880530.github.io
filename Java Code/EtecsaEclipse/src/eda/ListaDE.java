package src.eda;

import src.eda.*;
//eda.ExceptionListaVacia;
//import eda.ExceptionPosFueraDeRango;
//import eda.ILista;

/* Clase ListaSE: Implementaci�n del TDA Lista usando
 * nodos doblemente enlazados, con apuntadores al primer nodo 
 * y al �ltimo no */
public class ListaDE<T> extends ILista<T> {

    private int longitud;
    protected NodoDE<T> primero;
    protected NodoDE<T> ultimo;

    public ListaDE() {
        primero = null;
        ultimo = null;
        longitud = 0;
    }

    public void Adicionar(T x) {
        NodoDE<T> nuevo = new NodoDE<T>(x, null, null);
        if (Vacia()) {
            primero = ultimo = nuevo;
        } else {
            ultimo.setSiguiente(nuevo);
            nuevo.setAnterior(ultimo);
            ultimo = nuevo;
        }
        longitud++;
    }

    public void Insertar(T x, int pos) throws ExceptionPosFueraDeRango {
        if ((pos < 0) || (pos >= longitud)) {
            throw new ExceptionPosFueraDeRango();
        }

        NodoDE<T> nuevo = new NodoDE<T>(x, null, null);
        if (pos == 0) {
            nuevo.setSiguiente(primero);
            primero.setAnterior(nuevo);
            primero = nuevo;
        } else if (pos == longitud - 1) {
                ultimo = ultimo.getAnterior();
        } else {
            NodoDE<T> cursor = primero;
            int pos_cursor = 0;
            while ((cursor.getSiguiente() != null) && (pos_cursor < pos - 1)) {
                pos_cursor++;
                cursor = cursor.getSiguiente();
            }
            nuevo.setSiguiente(cursor.getSiguiente());
            cursor.getSiguiente().setAnterior(nuevo);
            cursor.setSiguiente(nuevo);
            nuevo.setAnterior(cursor);
        }
        longitud++;
    }

    public T Obtener(int pos) throws ExceptionPosFueraDeRango {
        if ((pos < 0) || (pos >= longitud)) {
            throw new ExceptionPosFueraDeRango();
        }

        NodoDE<T> cursor = primero;
        for (int i = 0; i < pos; i++) {
            cursor = cursor.getSiguiente();
        }
        return cursor.getInfo();
    }

    public void Eliminar(int pos) {
        if (Vacia()) {
            throw new ExceptionListaVacia();
        }
        if ((pos < 0) || (pos >= longitud)) {
            throw new ExceptionPosFueraDeRango();
        }

        NodoDE<T> cursor = primero;
        if (pos == 0) {
            primero = cursor.getSiguiente();
        } else if (pos == longitud - 1) {
            ultimo = ultimo.getAnterior();
        }
        else {
            NodoDE<T> anterior = primero;
            int pos_cursor = 0;
            while ((cursor != null) && (pos_cursor != pos)) {
                anterior = cursor;
                cursor = cursor.getSiguiente();
                pos_cursor++;
            }
            anterior.setSiguiente(cursor.getSiguiente());
            cursor.getSiguiente().setAnterior(anterior);
            cursor.setSiguiente(null);
            if (cursor == ultimo) {
                ultimo = anterior;
            }
        }
        longitud--;
    }

    public int Longitud() {
        return longitud;
    }

    public boolean Vacia() {
        return (longitud == 0);
    }

    public int Buscar(T x) throws Exception {
        if (Vacia()) {
            throw new ExceptionPosFueraDeRango();
        }

        int pos = 0;
        NodoDE<T> cursor = primero;
        while ((cursor != null) && (!cursor.getInfo().equals(x))) {
            cursor = cursor.getSiguiente();
            pos++;
        }

        if (cursor == null) {
            return -1;
        } else {
            return pos;
        }
    }
    
    public void RotarIsquierda(int n){
    
        for(int i=0;i<n;i++){
            
        Adicionar(primero.getInfo());
        primero=primero.getSiguiente();
        }
    }
    
   
}
