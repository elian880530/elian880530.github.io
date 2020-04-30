/*
 * CListaNodoDE.java
 *
 * Created on 26 de septiembre de 2006, 15:52
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package auxiliar;

/**
 *
 * @author lester
 */
public class JListaNodoDE<Tipo> implements ILista<Tipo> {
    
    protected JNodoDE<Tipo> cabeza;
    
    /** Creates a new instance of CListaNodoDE */
    public JListaNodoDE() {
        cabeza = new JNodoDE<Tipo>();
    }
    
     public void insertar(Tipo x, int i) {
         if((i < longitud()) && (i >= 0))
         {
             JNodoDE<Tipo> aux = cabeza;
             for(int j = 0; j < i;  j++)
                 aux = aux.getSiguiente();
             JNodoDE<Tipo> temp = new JNodoDE(x, aux.getSiguiente(), aux);
             aux.getSiguiente().setAnterior(temp);
             aux.setSiguiente(temp);
         }
         else 
            throw new JExcepcionParametroNoValido(); 
    }
    
    public Tipo obtener(int i) {
        if((i < longitud()) && (i >= 0))
        {
           JNodoDE<Tipo> aux = cabeza;
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
           JNodoDE<Tipo> aux = cabeza;
           for(int j = 0; j < i;  j++)
               aux = aux.getSiguiente();
           aux.setSiguiente(aux.getSiguiente().getSiguiente());
           if(aux.getSiguiente() != null)
               aux.getSiguiente().setAnterior(aux);
           return true;
        }
        else 
            throw new JExcepcionParametroNoValido(); 
    }
    
    public int longitud() {
        int total = 0;
        JNodoDE<Tipo> aux = cabeza;
        while(aux.getSiguiente() != null) {
            aux = aux.getSiguiente();
            total++; 
        }
        return total;
    }
    
    public void adicionar(Tipo x) {
        JNodoDE<Tipo> aux = cabeza;
        while(aux.getSiguiente() != null) 
            aux = aux.getSiguiente();
        JNodoDE<Tipo> temp = new JNodoDE<Tipo>(x, null, aux);
        aux.setSiguiente(temp);
    }
   
    public boolean vacia() {
        return cabeza.getSiguiente() == null;
    }
}
