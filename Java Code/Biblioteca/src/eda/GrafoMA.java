package eda;

/* Clase GrafoMA
 * Implementa un grafo con la variante 
 * Lista de Vertices - Matriz de Adyacencia */
public class GrafoMA<T> implements IGrafo<T> {
	/* Lista de Vertices */
    protected ListaSE<T> vertices;
    /* Matriz de Adyacencia */
    protected boolean[][] matriz_ady;

    /* Constructor */
    public GrafoMA()
    {
        vertices = new ListaSE<T>();
        matriz_ady = new boolean[10][10];

        /* Inicializando en false todas las entradas de la matriz */
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                matriz_ady[i][j] = false;
    }

    /* Método para insertar un nuevo vértice al grafo */
    public void insertarVertice(T v)throws Exception
    {
        if (!existeVertice(v))
        {
            vertices.Adicionar(v);
            int matriz_dim = matriz_ady.length;

            if (matriz_dim < vertices.Longitud())
            {
                /* Caso en que hay que aumentar el tamaño de la matriz
                 * pues la longitud de la lista de vertices sobrepasó
                 * su actual dimensión */
                boolean[][] matriz_temp = new boolean[matriz_dim + 10][matriz_dim + 10];

                /* Se copia el contenido de la matriz_ady en la matriz_temp */
                for (int i = 0; i < matriz_dim; i++)
                    for (int j = 0; j < matriz_dim; j++)
                        matriz_temp[i][j] = matriz_ady[i][j];

                matriz_ady = matriz_temp;
            }
            /* La columna y la fila asociada al nuevo vertice
             * se llenan de valores false */
            for (int i = 0; i < vertices.Longitud(); i++)
            {
                matriz_ady[i][vertices.Longitud() - 1] = false;
                matriz_ady[vertices.Longitud() - 1][i] = false;
            }
        }
        else throw new Exception("No existe vertice");
    }

    /* Método para eliminar un vértice del grafo */
    public void eliminarVertice(T v)throws Exception
    { 
        if (!existeVertice(v))
            throw new Exception("No existe vertice");

        int posv = vertices.Buscar(v);

        //vertices.Eliminar(posv);

        /* Eliminar la columna y la fila asociada 
         * al vertice */
        /* Primero se corren las filas de la posicion 
         * posv-1 en adelante, una posicion hacia arriba */
        /* Luego se corren las columnas de la posicion posv-1
         * enadelante, una posicion hacia la izquierda */
        int longitud = vertices.Longitud();
        for (int i = posv - 1; i < longitud - 1; i++)
            for (int j = 0; j < longitud; j++)
                matriz_ady[i][j] = matriz_ady[i + 1][j];
        for (int i = posv - 1; i < longitud - 1; i++)
            for (int j = 0; j < longitud; j++)
                matriz_ady[j][i] = matriz_ady[j][i+1];

        /* Se elimina el vertice de la lista de vertices */
        vertices.Eliminar(posv);
    }

    /* Método para saber si existe un vértice en el grafo */
    public boolean existeVertice(T v)throws Exception
    {
        int posv = vertices.Buscar(v);
        return (posv != -1);
    }

    /* Método para saber el número de vértices del grafo */
    public int numeroDeVertices()
    {
        return vertices.Longitud();
    }

    /* Método para insertar un nuevo arco al grafo */
    public void insertarArco(Arco<T> A)throws Exception
    {
        T v1 = A.getVertice1();
        T v2 = A.getVertice2();

        if (!existeArco(v1, v2))
        {
            int posv1 = vertices.Buscar(v1);
            int posv2 = vertices.Buscar(v2);

            matriz_ady[posv1 - 1][posv2 - 1] = true;
            matriz_ady[posv2 - 1][posv1 - 1] = true;
        }
        else throw new Exception("Ya existe el arco");
    }

    /* Método para eliminar un arco del grafo.
     * Se pasan los vertices que componen el arco */
    public void eliminarArco(T v1, T v2)throws Exception
    {
        if (!existeArco(v1, v2))
        {
            int posv1 = vertices.Buscar(v1);
            int posv2 = vertices.Buscar(v2);

            matriz_ady[posv1 - 1][posv2 - 1] = false;
            matriz_ady[posv2 - 1][posv1 - 1] = false;
        }
        else throw new Exception("Ya existe el arco");
    }

    /* Método para saber si existe un arco en el grafo. 
     * Se pasan los vertices que componen el arco */
    public boolean existeArco(T v1, T v2) throws Exception 
    {
        if (!existeVertice(v1) || !existeVertice(v2))
            throw new Exception("No existe vertice");
        
        int posv1 = vertices.Buscar(v1);
        int posv2 = vertices.Buscar(v2);
        return (matriz_ady[posv1 - 1][posv2 - 1] == true);
    }

    /* Método para saber el número de arcos del grafo */
    public int numeroDeArcos()
    {
        int longitud = vertices.Longitud();
        int cont = 0;

        for (int i = 0; i < longitud; i++)
            for (int j = i + 1; j < longitud; j++)
                if (matriz_ady[i][j]) cont++;

        return cont;
    }

    /* Método que retorna una lista con los vértices adyacentes
     * a un vértice dado */
    public ListaSE<T> adyacentesA(T v)throws Exception
    {
        if (!existeVertice(v))
            throw new Exception("No existe vertice");

        ListaSE<T> adyacentes = new ListaSE<T>();
        for (int i = 1; i <= vertices.Longitud(); i++)
        {
            T w = vertices.Obtener(i);
            if (existeArco(v, w))
                adyacentes.Adicionar(w);
        }
        return adyacentes;
    }

    /* Método para saber si el grafo está vacío o no */
    public boolean esVacio()
    {
        return vertices.Vacia();
    }

}
