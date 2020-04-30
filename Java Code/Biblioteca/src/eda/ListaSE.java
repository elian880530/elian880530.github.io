package eda;

/* Clase ListaSE: Implementaci�n del TDA Lista usando
 * nodos simplemente enlazados, con apuntadores al primer nodo 
 * y al �ltimo no */
public class ListaSE<T> implements ILista<T> {

    private int longitud;
    protected NodoSE<T> primero;
    protected NodoSE<T> ultimo;

    public ListaSE() {
        primero = null;
        ultimo = null;
        longitud = 0;
    }

    public void Adicionar(T x) {
        NodoSE<T> nuevo = new NodoSE<T>(x, null);
        if (Vacia()) {
            primero = ultimo = nuevo;
        } else {
            ultimo.setSiguiente(nuevo);
            ultimo = nuevo;
        }
        longitud++;
    }

    public void Insertar(T x, int pos) throws ExceptionPosFueraDeRango {
        if ((pos < 0) || (pos >= longitud)) 
            throw new ExceptionPosFueraDeRango();

        NodoSE<T> nuevo = new NodoSE<T>(x, null);
        if (pos == 0) {
            nuevo.setSiguiente(primero);
            primero = nuevo;
        } else {
            NodoSE<T> cursor = primero;
            int pos_cursor = 0;
            while ((cursor.getSiguiente() != null) && (pos_cursor <= pos - 1)) {
                pos_cursor++;
                cursor = cursor.getSiguiente();
            }
            nuevo.setSiguiente(cursor.getSiguiente());
            cursor.setSiguiente(nuevo);
        }
        longitud++;
    }

    public T Obtener(int pos) throws ExceptionPosFueraDeRango {
        if ((pos < 0) || (pos >= longitud)) {
            throw new ExceptionPosFueraDeRango();
        }

        NodoSE<T> cursor = primero;
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

        NodoSE<T> cursor = primero;
        if (pos == 0) {
            primero = cursor.getSiguiente();
        } else {
            NodoSE<T> anterior = primero;
            int pos_cursor = 0;
            while ((cursor != null) && (pos_cursor != pos)) {
                anterior = cursor;
                cursor = cursor.getSiguiente();
                pos_cursor++;
            }
            anterior.setSiguiente(cursor.getSiguiente());
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
        NodoSE<T> cursor = primero;
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
}
