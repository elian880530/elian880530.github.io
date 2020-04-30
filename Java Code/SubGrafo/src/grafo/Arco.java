/*
 * Arco.java
 *
 * Created on 8 de noviembre de 2006, 1:40
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package grafo;

/**
 *
 * @author rolandotr
 */
public class Arco<T> {
    
    public T v1;
    public T v2;
    
    /** Creates a new instance of Arco */
    public Arco(T v1, T v2) {
        assert(v1 != null && v2 != null) : "v1 != null && v2 != null";
        this.v1 = v1;
        this.v2 = v2;
    }
    
}
