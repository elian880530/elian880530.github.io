/*
 * ArcoPonderado.java
 *
 * Created on 5 de noviembre de 2007, 0:14
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package TDA_Grafo;




public class ArcoPonderado<T> extends Arco<T> {
    protected float peso;
    /** Creates a new instance of ArcoPonderado */
    public ArcoPonderado(T vInicial, T vFinal, float peso) {
        super(vInicial, vFinal);
        this.peso = peso;
    }
    public float GetPeso()
    {
        return peso;
    }
    public void SetPeso(float peso)
    {
        this.peso = peso;
    }
}
