/*
 * GrafoNoPonderadoMA.java
 *
 * Created on 5 de noviembre de 2007, 16:11
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package TDA_Grafo;

import TDALista.ILista;
import TDALista.ListaSE;

public class GrafoNoPonderadoMA<T> implements GrafoNoPonderado<T>{
    protected ILista<T> vertices;
    protected Integer[][] matrizA;
    /** Creates a new instance of GrafoNoPonderadoMA */
    public GrafoNoPonderadoMA() {
        vertices = new ListaSE();
        matrizA = new Integer[100][100];
        for (int f = 0; f < matrizA.length; f++) 
        {
            for (int c = 0; c < matrizA.length; c++) 
                matrizA[f][c]=0;
        }

        
    }
   
     //Los métodos que aparecen a continuación deben ser implementados por los estudiantes  
    
    //Implementar por los Estudiantes
    public int NumeroDeArcos()
    {
        //Devuelve el Número de Arcos presentes en el grafo 
        return 0;
    }
    
   
   
     //Implementar por los Estudiantes
    public void EliminarArco(Arco a)
    {
          //Elimina el arco 'a', existente entre 2 nodos
    }
    
    //Implementar por los Estudiantes(Este es mi metodo)
    public int CantidadBucles(){
       
          int cont=0;
       for(int i=0;i<vertices.Longitud();i++){
            if(matrizA[i][i] == 1 )
	cont++;				
           }
            return cont;				
          
    }
    //Implementar por los Estudiantes
    public int CantidadNodosAislados()
    {
        //Retorna la Cantidad de Nodos Aislados que tiene el Grafo
        return 0;
    }
    
     //Implementar por los Estudiantes
    public int CantidadNodosNoAislados(){
    int cont=0;
    for(int i=0;i<vertices.longitud();i++){  
    for(int j=i;j<vertices.longitud();j++){
    if( matrizA[i][j]==1){
    cont++;
    break;}}}
    return cont;
            }
    
    
    
    //Implementar por los Estudiantes(Este es mi metodo)
   public ILista<T> AdyacentesA(T vert){
      ListaSE<T>result=new ListaSE<T>();
      for(int i=0;i<vertices.Longitud();i++)
          if(estaELArco(vert, vertices.Obtener(i)))
              result.Adicionar(vertices.Obtener(i));
      return result;
         
    
    }

    
    public boolean estaELArco(T v,T v2){
    if((EstaElVertice(v))&&(EstaElVertice(v2)))
        return (matrizA[vertices.Buscar(v)][vertices.Buscar(v2)]==1);
    return false;
    }
   
   //Implementar por los Estudiantes
   public boolean EsRegular()
   {
       //Un grafo es regular si todos sus vértices tienen el mismo grado.
        return false;
   }
   

   
   
     //Implementar por los Estudiantes
   public boolean EsCompleto()
   {
       //Es completo si existen aristas uniendo todos los pares posibles de vértices.
        return false;
   }
   
   
    
// Fin de la Implementacion de los estudiantes 
    
   
   public boolean EsConexo()
   {
       ILista<T> BPP = new ListaSE<T>();
       BPP(0, new boolean[vertices.Longitud()], BPP);
       return  BPP.Longitud() == vertices.Longitud();

   }
   
   
   public int CantidadAdyacentesA(T v)
    {
         int CantAdyacentes=0;
        for (int c = 0; c < NumeroDeVertices(); c++) {
            if(matrizA[vertices.Buscar(v)][c] ==1)
                CantAdyacentes++;
        }
        return CantAdyacentes;

    }
   
   public boolean EsNulo()
   {
        //Un grafo es nulo si el conjunto de aristas es vacio.
       return (CantidadNodosAislados() == vertices.Longitud());

   }
   
   
    public void InsertarArco(Arco<T> a)
    {
        if(EstaElVertice(a.vInicial) && EstaElVertice(a.vFinal) && !EstaELArco(a) )
        {
            int posV1= vertices.Buscar(a.vInicial);
            int posV2= vertices.Buscar(a.vFinal);
            matrizA[posV1][posV2] = matrizA[posV2][posV1] = 1;
        }
    } 
   
   public void InsertarVertice(T v)
    {
        vertices.Adicionar(v);
    }
    public boolean EsVacio()
    {
        return vertices.Longitud() == 0;
    }
    public int NumeroDeVertices()
    {
        return vertices.Longitud();
    }
    
    
    public boolean EstaElVertice(T v)
    {
        if(v == null) throw new IllegalArgumentException("El parámetro no puede ser null");
        for(int i=0; i<vertices.Longitud(); i++)
            if(vertices.Obtener(i).equals(v)) return true;
        return false;
    }
    public boolean EstaELArco(Arco a)
    {
        if(a == null) throw new IllegalArgumentException("El parámetro no puede ser null");
        
        int posV1=-1, posV2=-1;
        if (EstaElVertice((T)a.vInicial)  && EstaElVertice((T)a.vFinal)) {
             posV1 = vertices.Buscar((T)a.vInicial);
             posV2 = vertices.Buscar((T)a.vFinal);
             return matrizA[posV1][posV2]==1 && matrizA[posV2][posV1]==1;
        }
        return false;
    }
    
   
    
    private void CreceMatriz()
    {
        Integer[][] aux = new Integer[matrizA.length + 10][matrizA.length + 10];
        int longitud = matrizA.length;
        for(int i=0; i<longitud; i++)
            for(int j=i; i<longitud; j++)
            {
                aux[i][j] = matrizA[i][j];
                aux[j][i] = matrizA[j][i];
            }
        matrizA = aux;
    }
    
    private int BuscaVertice(T v)
    {
        int pos = -1;
        for(int i=0; i<vertices.Longitud(); i++)
            if(vertices.Obtener(i).equals(v)) pos = i;
        return pos;
    }
   
    public void EliminarVertice(T v)
    {
        if(v == null) throw new IllegalArgumentException("El parámetro no puede ser null");
        int pos = BuscaVertice(v);
        if(pos == -1) throw new IllegalArgumentException("El vértice indicado no pertenece al grafo");
        int longitud = vertices.Longitud();
        for(int i=0; i<longitud; i++)
            for(int j=pos; j<longitud - 1; j++)
                matrizA[i][j] = matrizA[i][j+1];
        for(int i=pos; i<longitud -1; i++)
            for(int j=0; j<longitud - 1; j++)
                matrizA[i][j] = matrizA[i+1][j];
        vertices.Eliminar(pos);
    }
    
    
    public void ReemplazarVertice(T viejo, T nuevo)
    {
        int pos = -1;
        for(int i=0; i<vertices.Longitud(); i++)
        {
            T v = vertices.Obtener(i);
            if(v.equals(viejo)) 
                pos = i;
            if(v.equals(nuevo)) 
                throw new IllegalArgumentException("El vértice nuevo pertenece al grafo");
        }
        if(pos == -1) throw new IllegalArgumentException("El vértice viejo no pertenece al grafo");
        vertices.Insertar(nuevo, pos);
        vertices.Eliminar(pos + 1);
    }
    
    public ILista<T> RecorrerEnProfundidad()
    {
        int longitud = vertices.Longitud();
        boolean[] visitados = new boolean[longitud];
        for (int i = 0; i < longitud; i++) {
            visitados[i]=false;
        }
        ILista<T> lista = new ListaSE();
        for(int i=0; i<longitud; i++)
            if(!visitados[i])
                BPP(i, visitados, lista);
        return lista;
    }
    
    private void BPP(int pos, boolean[] visitados, ILista<T> l)
    {
        visitados[pos] = true;
        l.Adicionar(vertices.Obtener(pos));
        for(int i=0; i<vertices.Longitud(); i++)
            if(matrizA[pos][i]==1 && !visitados[i]) 
                BPP(i, visitados, l);
    }
    
    public ILista<T> RecorrerALoAncho()
    {
        int longitud = vertices.Longitud();
        boolean[] visitados = new boolean[longitud];
        for (int i = 0; i < longitud; i++) {
            visitados[i]=false;
        }

        ILista<T> lista = new ListaSE();
        for(int i=0; i<longitud; i++)
            if(!visitados[i])
                BPA(i, visitados, lista);
        return lista;
    }
    
    private void  BPA(int pos, boolean[] visitados,ILista lista)
    {
        ILista<Integer> cola = new ListaSE();
        visitados[pos] = true;
        lista.Adicionar(vertices.Obtener(pos));
        cola.Adicionar(pos);
        while(cola.Longitud() > 0)
        {
            int posAux = cola.Obtener(0).intValue();
            cola.Eliminar(0);
            for(int i=0; i<vertices.Longitud(); i++)
                if(matrizA[posAux][i]==1 && !visitados[i]) 
                {
                    visitados[i] = true;
                    lista.Adicionar(vertices.Obtener(i));
                    cola.Adicionar(i);
                }
        }
    }
    public Integer[][] MatrizAdyacencia(){return matrizA;}
}
