/*
 * CGrafo.java
 *
 *  Created on 15 de enero de 2009, 0:22
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package conexiones;

/**
 *
 * @author eghernandez
 */

public class CGrafo<T> 
{
    private CListaSE<CVertice<T>> V;
    private CListaSE<CArista> A;
    private int id_generador;
    
    public CGrafo() 
    {
        V = new CListaSE<CVertice<T>>();
        A = new CListaSE<CArista>();
        id_generador = 0;
    }
    
    /**
     * Crea una nueva instancia del Grafo a partir de los parametros V y A
     * Un grafo es un conjunto de Vertices y un multiconjunto de Aristas
     * V especifica el conjunto de vertices del Grafo
     * A especifica el conjunto de aristas del Grafo
     */
    public CGrafo(CListaSE<CVertice<T>> V, CListaSE<CArista> A) 
    {
        this.V = V;
        this.A = A;
        id_generador = 0;
    }
    
    /**
     * Devuelve el conjunto de Vertices del Grafo
     * V - Conjunto de vertices del Grafo 
     */
    public CListaSE<CVertice<T>> getVertices() 
    {
        return V;
    }
    
    /**
     * Devuelve el conjunto de Aristas del Grafo
     * A - Conjunto de Aristas del Grafo
     */
    public CListaSE<CArista> getAristas() 
    {
        return A;
    }
    
    /**
     * Adicionar un vertice al Grafo
     * dato - es el contenido del nuevo vertice que se anadira al grafo
     */
    public void AdicionarVertice(T dato) 
    {
        int id = ++id_generador;
        V.Adicionar(new CVertice(dato, id));
    }
    
    /**
     * Verifica la existencia de un vertice
     * n nombre del vertice 
     */
    public boolean EstaElVertice(T n)throws Exception
    {
        for(int i = 1; i <= V.Longitud(); i++)
        {
            if(V.Obtener(i).getDato().equals(n))
                return true;
        }
        return false;
    }
    
    /**
     * Verifica la existencia de una arista
     * n nombre del vertice 1
     * n1 nombre del vertice 2
     */
    public boolean EstaLaArista(T n, T n1)throws Exception
    {
        for (int i = 1; i <= A.Longitud(); i++) 
        {
            if(A.Obtener(i).getV1().getDato().equals(n) && A.Obtener(i).getV2().getDato().equals(n1) || A.Obtener(i).getV1().getDato().equals(n1) && A.Obtener(i).getV2().getDato().equals(n))
                return true;
        }
        return false;
    }
    
    /**
     * Adiciona una arista al Grafo, en orden no descendente con respecto al peso de las aristas
     * u uno de los vertices asociados a la nueva arista
     * v uno de los vertices asociados a la nueva arista
     * peso es el costo de la relacion entre u y v
     */
    public void AdicionarArista(CVertice u, CVertice v, int peso)throws Exception
    {
        int i = 1;
        while(i <= A.Longitud() && A.Obtener(i).getPeso() > peso)
        {
	    i++;
	}
        A.Insertar(new CArista(u, v, peso), i);
    }
    
    /**
     * Busca en que Conjunto se encuentra el vertice <strong>v</strong> dentro de la 
     * coleccion <strong>collection</strong> de conjuntos disjuntos. Dos conjuntos son disjuntos si no 
     * tienen elementos comunes.
     * Este metodo es empleado unicamente en el algoritmo Kruskal.
     * coleccion coleccion que contiene los conjuntos disjuntos
     * v vertice que se desea encontrar
     * return Devuelve en que conjunto se encuentra el vertice, -1 en caso que no se encuentre.
     */
    private int KruskalEncontrarVertice(CListaSE<CListaSE<CVertice<T>>> coleccion, CVertice v) throws Exception
    {
        for(int i = 1; i <= coleccion.Longitud(); i++) 
        {
            for(int j = 1; j <= coleccion.Obtener(i).Longitud(); j++) 
            {
                if (coleccion.Obtener(i).Obtener(j).getId() == v.getId()) 
                {
                    return i;
                }
            }
        }
        return -1;
    }
    
    /**
     * Une dos conjuntos dentro de una colecion de conjuntos disjuntos. 
     * Este metodo es empleado unicamente en el algoritmo Kruskal.
     * coleccion coleccion de conjuntos disjuntos.
     * u posicion de uno de los conjuntos a unir.
     * v posicion del otro de los conjuntos a unir.
     */
    private void KruskalUnirColeccion(CListaSE<CListaSE<CVertice<T>>> coleccion, int u, int v) throws Exception
    {
        coleccion.Obtener(v).AdicionarTodo(coleccion.Obtener(u));
        coleccion.Eliminar(u);
    }
    
    /**
     * Crea un arbol de recubrimiento minimo del Grafo, 
     * modificando el conjunto de aristas del mismo
     */
    public void Kruskal() throws Exception
    {
        CListaSE<CArista> lista = new CListaSE<CArista>();
        /**
         * Crea los conjuntos disjuntos vertices del Grafo, inicialmente 
         * cada conjunto contiene a cada uno de los vertices
         */
        CListaSE<CListaSE<CVertice<T>>> coleccion = new CListaSE<CListaSE<CVertice<T>>>();
        for(int i = 1; i <= V.Longitud(); i++) 
        {
            coleccion.Adicionar(new CListaSE<CVertice<T>>());
            coleccion.Obtener(i).Adicionar(V.Obtener(i));
        }
        /**
         * El siguiente paso es ordenar las aristas por peso, 
         * pero de ello en nuestro caso se encarga el metodo 
         * AdicionarArista(...)
         */
        for(int i = 1; i <= A.Longitud(); i++) 
        {
            /**
             * Se seleccionan las aristas y se determina si los vertices asociados
             * a ella pertenecen o no al mismo conjunto.
             */
            int u = KruskalEncontrarVertice(coleccion, A.Obtener(i).getV1());
            int v = KruskalEncontrarVertice(coleccion, A.Obtener(i).getV2());
            /**
             * Si los vertices no pertenecen al mismo conjunto,
             * se adiciona la arista al nuevo conjunto de Aristas
             * y se unen los conjuntos.
             */
            if (u != v) 
            {
                lista.Adicionar(A.Obtener(i));
                KruskalUnirColeccion(coleccion, u, v);
            }
        }
        /**
         * Se modifica el conjunto de aristas del Grafo
         */
        A = new CListaSE<CArista>();
        for(int i = 1; i <= lista.Longitud(); i++) 
        {
            this.AdicionarArista(lista.Obtener(i).getV1(), lista.Obtener(i).getV2(), lista.Obtener(i).getPeso());
        }
    }
}
