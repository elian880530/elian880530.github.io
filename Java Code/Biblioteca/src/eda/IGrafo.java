
package eda;

/* Interface para el TDA Grafo */
/* En esta variante se asume que el grafo es no ponderado */
interface IGrafo<T>
{
    /* M�todo para insertar un nuevo v�rtice al grafo */
    void insertarVertice(T v)throws Exception;

    /* M�todo para eliminar un v�rtice del grafo */
    void eliminarVertice(T v)throws Exception;

    /* M�todo para saber si existe un v�rtice en el grafo */
    boolean existeVertice(T v)throws Exception;

    /* M�todo para saber el n�mero de v�rtices del grafo */
    int numeroDeVertices();

    /* M�todo para insertar un nuevo arco al grafo */
    void insertarArco(Arco<T> A)throws Exception;

    /* M�todo para eliminar un arco del grafo.
     * Se pasan los vertices que componen el arco */
    void eliminarArco(T v1, T v2)throws Exception;

    /* M�todo para saber si existe un arco en el grafo. 
     * Se pasan los vertices que componen el arco */
    boolean existeArco(T v1, T v2) throws Exception;

    /* M�todo para saber el n�mero de arcos del grafo */
    int numeroDeArcos();

    /* M�todo que retorna una lista con los v�rtices adyacentes
     * a un v�rtice dado */
    ListaSE<T> adyacentesA(T v)throws Exception;

    /* M�todo para saber si el grafo est� vac�o o no */
    boolean esVacio();
}