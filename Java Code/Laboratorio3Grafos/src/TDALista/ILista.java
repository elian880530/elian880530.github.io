/*
 * ILista.java
 *
 * Created on 26 de septiembre de 2006, 2:57
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package TDALista;

/**
 *
 * @author lester
 */
public interface ILista<Tipo> {

    public int longitud();
    void Insertar(Tipo x, int i);
    void Adicionar(Tipo x);
    Tipo Obtener(int i);
    void Eliminar(int i);
    int Longitud();
    boolean Vacia();
    int Buscar(Tipo x);
};
