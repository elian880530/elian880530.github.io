/*
 * CNodoSE.java
 *
 * Created on 26 de septiembre de 2006, 13:54
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package auxiliar;

/**
 *
 * @author lester
 */
public class JNodoSE<Tipo> {
    
    protected JNodoSE<Tipo> siguiente; 
    protected Tipo info;
    
    /** Creates a new instance of CNodoSE */
    public JNodoSE() {
        this.info = null;
        this.siguiente = null;
    }
    
    public JNodoSE(Tipo info) {
        this.info = info;
        this.siguiente = null;
    }
    
    public JNodoSE(Tipo info, JNodoSE<Tipo> siguiente) {
        this.info = info;
        this.siguiente = siguiente;
    }
    
    public Tipo getDato() {
        return info;
    }
    
    public void setDato(Tipo info) {
        this.info = info;
    }
    
    public JNodoSE<Tipo> getSiguiente() {
        return siguiente;
    }
    
    public void setSiguiente(JNodoSE<Tipo> siguiente) {
        this.siguiente = siguiente;
    }
}
