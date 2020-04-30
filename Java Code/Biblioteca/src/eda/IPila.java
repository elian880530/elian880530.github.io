package eda;

/* Interface para el TDA Pila */
    public interface IPila<T>
    {
        /* Método para adicionar un elemento x a la pila */
        void Apilar(T x);
        
        /* Método para extraer un elemento de la pila. */
        /* Se extrae siempre el elemento que está en el tope. */
        /* Retorna la información del elemento extraído. */
        T Extraer()throws Exception;
        
        /* Método que retorna la información del elemento tope */
        T Tope();

        /* Método que retorna true si la pila está vacía (sin elementos) */
        /* y false en caso contrario */
        boolean Vacia();
    }

