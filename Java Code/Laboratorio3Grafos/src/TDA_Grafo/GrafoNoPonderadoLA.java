/*
 * GrafoNoPonderadoLA.java
 *
 * Created on 6 de noviembre de 2007, 3:19
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package TDA_Grafo;

import TDALista.ILista;
import TDALista.ListaSE;


public class GrafoNoPonderadoLA<T> implements GrafoNoPonderado<T> {
    protected ILista<T> vertices;
    protected ILista<ILista<Integer>> listaA;
    /** Creates a new instance of GrafoNoPonderadoLA */
    public GrafoNoPonderadoLA() {
        vertices = new ListaSE();
        listaA = new ListaSE();
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
    
    //Implementar por los Estudiantes
    public int CantidadBucles()
    {
        //Retorna la Cantidad de Bucles que tiene el Grafo
        return 0; 
    }
    //Implementar por los Estudiantes
    public int CantidadNodosAislados()
    {
        //Retorna la Cantidad de Nodos Aislados que tiene el Grafo
        return 0;
    }
    
     //Implementar por los Estudiantes
    public int CantidadNodosNoAislados()
    {
        //Retorna la Cantidad de Nodos No Aislados que tiene el Grafo
        return 0;
    }
    
    
    
    //Implementar por los Estudiantes
    public ILista<T> AdyacentesA(T v)
    {
        //Devuelve una Lista con todos los Vertices Adyacentes a v
        return new ListaSE<T>();   
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
    
  
   
   //Implementar por los Estudiantes
   public boolean EsConexo()
   {
      //Devuelve true si el grafo es conexo, false en otro caso
        ILista<T> L = new ListaSE<T>(); 
       BPP(0, new boolean[vertices.Longitud()],L);
       return (L.Longitud() == vertices.Longitud());

   }
   
   
   
   public boolean EsNulo()
   {
      //Un grafo es nulo si el conjunto de aristas es vacio.
        return CantidadNodosAislados() == NumeroDeVertices();
   }
   
   
   public int CantidadAdyacentesA(T v)
    {
            //Retorna la cantidad de vertices adyacentes al Nodo V
            if (EstaElVertice(v)) {
                return listaA.Obtener(vertices.Buscar(v)).Longitud();
            }
            else
             throw new IllegalArgumentException("No se encuentra el vertice");
        
    }
    
   
   
   public void InsertarVertice(T v)
    {
        vertices.Adicionar(v);
        listaA.Adicionar(new ListaSE<Integer>());
    } 
   
    public void InsertarArco(Arco<T> a)
    {
        if(EstaElVertice((T)a.vInicial) && EstaElVertice((T)a.vFinal) && !EstaELArco(a))
        {
            int posV_Inicial = vertices.Buscar((T)a.vInicial);
            int posV_Final = vertices.Buscar((T)a.vFinal);
            if(listaA.Obtener(posV_Inicial).Buscar(posV_Final)==-1  && listaA.Obtener(posV_Final).Buscar(posV_Inicial)==-1){
                listaA.Obtener(posV_Inicial).Adicionar(posV_Final);
                listaA.Obtener(posV_Final).Adicionar(posV_Inicial);
            }
        }
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
       return vertices.Buscar(v)!=-1;
    }
    public boolean EstaELArco(Arco a)//Me quede por aqui
    {
       if(EstaElVertice((T)a.vFinal) && EstaElVertice((T)a.vFinal))
       {
            int posV_Inicial = vertices.Buscar((T)a.vInicial);
            int posV_Final = vertices.Buscar((T)a.vFinal);
            return (listaA.Obtener(posV_Inicial).Buscar(posV_Final)!=-1 && listaA.Obtener(posV_Final).Buscar(posV_Inicial)!=-1);
       }        
       return false;
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
        ILista<Integer> adys = listaA.Obtener(pos);
        for(int i=0; i<adys.Longitud(); i++)
        {
            Integer posAdy = adys.Obtener(i);
            ILista<Integer> listaAux =listaA.Obtener(posAdy);
            for(int j=0; j<listaAux.Longitud(); j++)
            {
                if(listaAux.Obtener(j) == pos) 
                {
                    listaAux.Eliminar(j);
                    break;
                }
            }
        }
        listaA.Eliminar(pos);
        vertices.Eliminar(pos);
        ILista<Integer> lista;
        for(int i=0; i<listaA.Longitud(); i++)
        {
            lista = listaA.Obtener(i);
            for(int j=0; j<lista.Longitud(); j++)
                if(lista.Obtener(j) > pos)
                {
                    Integer aux = lista.Obtener(j);
                    lista.Insertar(aux - 1, j);
                    lista.Eliminar(j+1);
                }
        }
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
        ILista<Integer> adyacentes = listaA.Obtener(pos);
        for(int i=0; i<adyacentes.Longitud(); i++)
            if(!visitados[adyacentes.Obtener(i)]) 
                BPP(adyacentes.Obtener(i), visitados, l);
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
            ILista<Integer> adyacentes = listaA.Obtener(posAux);
            for(int i=0; i<adyacentes.Longitud(); i++)
                if(!visitados[adyacentes.Obtener(i)]) 
                {
                    visitados[adyacentes.Obtener(i)] = true;
                    lista.Adicionar(vertices.Obtener(adyacentes.Obtener(i)));
                    cola.Adicionar(adyacentes.Obtener(i));
                }
        }
    }
    
    public ILista<ILista<Integer>> ListaAdyacencia()
    {
        return listaA;
    }

    public boolean estaELArco(T v, T v2) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    

    

   
}
