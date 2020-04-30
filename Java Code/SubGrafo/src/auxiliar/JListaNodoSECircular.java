/*
 * CListaNodoSECircular.java
 *
 * Created on 26 de septiembre de 2006, 15:30
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package auxiliar;

/**
 *
 * @author lester
 */
public  class JListaNodoSECircular<Tipo> extends JAListaNodoSE<Tipo> implements ILista<Tipo> {
    
    /** Creates a new instance of CListaNodoSECircular */
    public JListaNodoSECircular() {
        super();
        cabeza.setSiguiente(cabeza);
    }
    
     public int longitud() {
        int total = 0;
        JNodoSE<Tipo> aux = cabeza;
        while(aux.getSiguiente() != cabeza) {
            aux = aux.getSiguiente();
            total++; 
        }
        return total;
    }
    
    public void adicionar(Tipo x) {
        JNodoSE<Tipo> aux = cabeza;
        while(aux.getSiguiente() != cabeza) 
            aux = aux.getSiguiente();
        JNodoSE<Tipo> temp = new JNodoSE<Tipo>(x,cabeza);
        aux.setSiguiente(temp);
    }
   
    public boolean vacia() {
        return cabeza.getSiguiente() == cabeza;
    }

	
	
	
}
