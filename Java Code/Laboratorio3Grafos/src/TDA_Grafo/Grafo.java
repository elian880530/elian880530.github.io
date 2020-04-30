/*
 * Grafo.java
 *
 * Created on 4 de noviembre de 2007, 23:47
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package TDA_Grafo;
import TDALista.ILista;

public interface Grafo<T> {
   //Implementar por los Estudiantes
    public int NumeroDeArcos();
    //Implementar por los estudiantes
    public void InsertarArco(Arco<T> a);
     //Implementar por los Estudiantes
    public void EliminarArco(Arco<T> a);
    //Implementar por los Estudiantes
    public int CantidadBucles();
    //Implementar por los Estudiantes
    public int CantidadNodosAislados();
     //Implementar por los Estudiantes
    public int CantidadNodosNoAislados();
    //Implementar por los Estudiantes
    public int CantidadAdyacentesA(T v);
    //Implementar por los Estudiantes
    public ILista<T> AdyacentesA(T v);
    //Implementar por los Estudiantes
    public boolean EsConexo();
   //Implementar por los Estudiantes
   public boolean EsRegular();
  //Implementar por los Estudiantes
   public boolean EsNulo();
   //Implementar por los Estudiantes
   public boolean EsCompleto();
   
 
  public void InsertarVertice(T v);
  boolean EsVacio();
  int NumeroDeVertices();
  boolean EstaElVertice(T v);
  boolean EstaELArco(Arco<T> a);
  void EliminarVertice(T v);
  void ReemplazarVertice(T viejo, T nuevo);
  ILista<T> RecorrerALoAncho();
  ILista<T> RecorrerEnProfundidad();
}
