/*
 * ILista.java
 *
 * Created on 26 de septiembre de 2006, 2:57
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package auxiliar;

/**
 *
 * @author lester
 */
public interface ILista<Tipo> {
    void insertar(Tipo x, int i);
    void adicionar(Tipo x);
    Tipo obtener(int i);
    boolean eliminar(int i);
    int longitud();
    boolean vacia();
    JNodoSE<Tipo> getCabeza();
};
