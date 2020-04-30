/*
 * Arco.java
 *
 * Created on 5 de noviembre de 2007, 0:07
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package TDA_Grafo;



public class Arco<T> {
    protected T vInicial,
                vFinal;
    /** Creates a new instance of Arco */
    public Arco(T vInicial, T vFinal) {
        if(vInicial == null || vFinal == null) throw new IllegalArgumentException("Ninguno de los vértices puede ser null");
        this.vInicial = vInicial;
        this.vFinal = vFinal;
    }
    public T GetVerticeInicial()
    {
        return vInicial;
    }
     public T GetVerticeFinal()
    {
        return vFinal;
    }
     public void SetVerticeInicial(T v)
    {
        vInicial = v;
    }
     public void SetVerticeFinal(T v)
    {
        vFinal = v;
    } 
}
