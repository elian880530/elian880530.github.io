/*
 * CNodoDE.java
 *
 * Created on 26 de septiembre de 2006, 15:54
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package auxiliar;

/**
 *
 * @author lester
 */
public class JNodoDE<Tipo> {
    
    protected JNodoDE<Tipo> siguiente; 
    protected JNodoDE<Tipo> anterior; 
    protected Tipo info;
    
    /** Creates a new instance of CNodoDE */
    public JNodoDE() {
        this.info = null;
        this.siguiente = null;
        this.anterior = null;
    }
    
    public JNodoDE(Tipo info) {
        this.info = info;
        this.siguiente = null;
        this.anterior = null;
    }
    
    public JNodoDE(Tipo info, JNodoDE<Tipo> siguiente, JNodoDE<Tipo> anterior) {
        this.info = info;
        this.siguiente = siguiente;
        this.anterior = anterior;
    }
    
    public Tipo getDato() {
        return info;
    }
    
    public void setDato(Tipo info) {
        this.info = info;
    }
    
    public JNodoDE<Tipo> getSiguiente() {
        return siguiente;
    }
    
    public void setSiguiente(JNodoDE<Tipo> siguiente) {
        this.siguiente = siguiente;
    }
    
     public JNodoDE<Tipo> getAnterior() {
        return anterior;
    }
    
    public void setAnterior(JNodoDE<Tipo> anterior) {
        this.anterior = anterior;
    }
    
}
