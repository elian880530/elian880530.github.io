/*
 * CNodoSE.java
 *
 * Created on 26 de septiembre de 2006, 13:54
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package TDALista;

/**
 *
 * @author lester
 */
public class NodoSE<Tipo> {
    
    protected NodoSE<Tipo> siguiente; 
    protected Tipo info;
    
    /** Creates a new instance of CNodoSE */
    public NodoSE() {
        this.info = null;
        this.siguiente = null;
    }
    
    public NodoSE(Tipo info) {
        this.info = info;
        this.siguiente = null;
    }
    
    public NodoSE(Tipo info, NodoSE<Tipo> siguiente) {
        this.info = info;
        this.siguiente = siguiente;
    }
    
    public Tipo getDato() {
        return info;
    }
    
    public void setDato(Tipo info) {
        this.info = info;
    }
    
    public NodoSE<Tipo> getSiguiente() {
        return siguiente;
    }
    
    public void setSiguiente(NodoSE<Tipo> siguiente) {
        this.siguiente = siguiente;
    }
}
