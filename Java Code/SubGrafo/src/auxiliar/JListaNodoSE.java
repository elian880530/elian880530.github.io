/*
 * CListaNodoSE.java
 *
 * Created on 26 de septiembre de 2006, 15:19
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package auxiliar;

/**
 *
 * @author lester
 */
public class JListaNodoSE<Tipo> extends JAListaNodoSE<Tipo> implements ILista<Tipo> {
   
    /** Creates a new instance of CListaNodoSE */
    public JListaNodoSE() {
        super();
    }
    public JNodoSE<Tipo> getCabeza(){
        return cabeza;
    }
    
    public int longitud() {
        int total = 0;
        JNodoSE<Tipo> aux = cabeza;
        while(aux.getSiguiente() != null) {
            aux = aux.getSiguiente();
            total++; 
        }
        return total;
    }
    
    public void adicionar(Tipo x) {
        JNodoSE<Tipo> aux = cabeza;
        while(aux.getSiguiente() != null) 
            aux = aux.getSiguiente();
        JNodoSE<Tipo> temp = new JNodoSE<Tipo>(x);
        aux.setSiguiente(temp);
    }
    
    public boolean vacia() {
        return cabeza.getSiguiente() == null;
    }
}
