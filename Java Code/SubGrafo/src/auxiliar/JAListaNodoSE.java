/*
 * CAListaNodo.java
 *
 * Created on 26 de septiembre de 2006, 14:10
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package auxiliar;

/**
 *
 * @author lester
 */
public abstract class JAListaNodoSE<Tipo> implements ILista<Tipo> {
    
    protected JNodoSE<Tipo> cabeza;
    
    /** Creates a new instance of CAListaNodo */
    public JAListaNodoSE() {
        cabeza = new JNodoSE<Tipo>();
    }
    
    /** Creates a new instance of CListaArreglo */
    
    public void insertar(Tipo x, int i) {
         if((i < longitud()) && (i >= 0))
         {
             JNodoSE<Tipo> aux = cabeza;
             for(int j = 0; j < i;  j++)
                 aux = aux.getSiguiente();
             JNodoSE<Tipo> temp = new JNodoSE(x, aux.getSiguiente());
             aux.setSiguiente(temp);
         }
         else 
            throw new JExcepcionParametroNoValido(); 
    }
    
    public Tipo obtener(int i) {
        if((i < longitud()) && (i >= 0))
        {
           JNodoSE<Tipo> aux = cabeza;
           for(int j = 0; j < i;  j++)
               aux = aux.getSiguiente();
           return aux.getSiguiente().getDato();                     
        }
        else 
            throw new JExcepcionParametroNoValido(); 
    }
    
    public boolean eliminar(int i) {
        if((i < longitud()) && (i >= 0))
        {
           JNodoSE<Tipo> aux = cabeza;
           for(int j = 0; j < i;  j++)
               aux = aux.getSiguiente();
           aux.setSiguiente(aux.getSiguiente().getSiguiente());
           return true;
        }
        else 
            throw new JExcepcionParametroNoValido(); 
    }
    
    public abstract int longitud();
    public abstract void adicionar(Tipo x);
    public abstract boolean vacia();
}
