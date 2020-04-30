
package eda;

/* Interface para el TDA Grafo */
/* En esta variante se asume que el grafo es no ponderado */
interface IGrafo<T>
{
    /* Método para insertar un nuevo vértice al grafo */
    void insertarVertice(T v)throws Exception;

    /* Método para eliminar un vértice del grafo */
    void eliminarVertice(T v)throws Exception;

    /* Método para saber si existe un vértice en el grafo */
    boolean existeVertice(T v)throws Exception;

    /* Método para saber el número de vértices del grafo */
    int numeroDeVertices();

    /* Método para insertar un nuevo arco al grafo */
    void insertarArco(Arco<T> A)throws Exception;

    /* Método para eliminar un arco del grafo.
     * Se pasan los vertices que componen el arco */
    void eliminarArco(T v1, T v2)throws Exception;

    /* Método para saber si existe un arco en el grafo. 
     * Se pasan los vertices que componen el arco */
    boolean existeArco(T v1, T v2) throws Exception;

    /* Método para saber el número de arcos del grafo */
    int numeroDeArcos();

    /* Método que retorna una lista con los vértices adyacentes
     * a un vértice dado */
    ListaSE<T> adyacentesA(T v)throws Exception;

    /* Método para saber si el grafo está vacío o no */
    boolean esVacio();
}