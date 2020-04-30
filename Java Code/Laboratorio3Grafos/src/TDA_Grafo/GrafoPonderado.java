/*
 * GrafoPonderado.java
 *
 * Created on 5 de noviembre de 2007, 0:29
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package TDA_Grafo;


public interface GrafoPonderado<T> extends Grafo<T>{
    void ModificarPeso(Arco a, float nuevopeso);
}
