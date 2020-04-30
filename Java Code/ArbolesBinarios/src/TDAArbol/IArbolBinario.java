/*
 * IArbolBinario.java
 *
 * Created on 19 de octubre de 2006, 2:17
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package TDAArbol;

import TDALista.ILista;

/**
 *
 * @author lester
 */
public interface IArbolBinario<Tipo> {
    Tipo getRaiz();
    boolean esHoja();
    boolean esVacio();
    int grado();
    int altura();
    IArbolBinario<Tipo> subArbolIzquierdo();
    IArbolBinario<Tipo> subArbolDerecho();
    void adicionarIzquierdo(IArbolBinario<Tipo> nsa);
    void adicionarDerecho(IArbolBinario<Tipo> nsa);
    void podarIzquierdo();
    void podarDerecho();
    ILista<Tipo> preOrden();
    ILista<Tipo> posOrden();
    ILista<Tipo> entreOrden();
    ILista<Tipo> aLoAncho();
    int ContarDerechos();
}
