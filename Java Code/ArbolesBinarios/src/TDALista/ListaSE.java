/*
 * CListaNodoSE.java
 *
 * Created on 26 de septiembre de 2006, 15:19
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package TDALista;

/**
 *
 * @author lester
 */
public class ListaSE<Tipo> implements ILista<Tipo> {
   
    protected NodoSE<Tipo> cabeza;
    
    /** Creates a new instance of CListaNodoSE */
    public ListaSE() {
        super();
        cabeza = null;
    }
    
    protected NodoSE<Tipo> obtenerNodo(int i) {
        if((i < longitud()) && (i >= 0))
        {
           NodoSE<Tipo> aux = cabeza;
           for(int j = 0; j < i;  j++)
               aux = aux.getSiguiente();
           return aux;                     
        }
        else 
            throw new ExcepcionParametroNoValido(); 
    }
    
    public void insertar(Tipo x, int i) {
        NodoSE<Tipo> aux = obtenerNodo(i - 1);
        NodoSE<Tipo> temp = new NodoSE(x, aux.getSiguiente());
        aux.setSiguiente(temp); 
    }
    
    public Tipo obtener(int i) {
        return obtenerNodo(i).getDato();                     
    }
    
    public void eliminar(int i) {
        NodoSE<Tipo> aux = obtenerNodo(i - 1);
        aux.setSiguiente(aux.getSiguiente().getSiguiente());
    }
    
    public int longitud() {
        int total = 0;
        NodoSE<Tipo> aux = cabeza;
        while(aux != null) {
            aux = aux.getSiguiente();
            total++; 
        }
        return total;
    }
    
    public void adicionar(Tipo x) {
        NodoSE<Tipo> aux = cabeza;
        if(cabeza == null)
            cabeza = new NodoSE<Tipo>(x);
        else
        {
            while(aux.getSiguiente() != null) 
                aux = aux.getSiguiente();
            NodoSE<Tipo> temp = new NodoSE<Tipo>(x);
            aux.setSiguiente(temp);
        }
    }
    
    public boolean vacia() {
        return cabeza == null;
    }
}
