/*
 * GrafoNoPonderado.java
 *
 * Created on 5 de noviembre de 2007, 0:26
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package TDA_Grafo;

import TDALista.ILista;


public interface GrafoNoPonderado<T> extends Grafo<T>{
  boolean EsVacio();
  int NumeroDeVertices();
  int NumeroDeArcos();
  boolean EstaElVertice(T v);
  boolean EstaELArco(Arco<T> a);
  boolean estaELArco(T v,T v2);
  void InsertarVertice(T v);
  void InsertarArco(Arco<T> a);
  void EliminarVertice(T v);
  void EliminarArco(Arco<T> a);
  ILista<T> AdyacentesA(T v);
  void ReemplazarVertice(T viejo, T nuevo);
  ILista<T> RecorrerALoAncho();
  ILista<T> RecorrerEnProfundidad();
}
