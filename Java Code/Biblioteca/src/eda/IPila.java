package eda;

/* Interface para el TDA Pila */
    public interface IPila<T>
    {
        /* M�todo para adicionar un elemento x a la pila */
        void Apilar(T x);
        
        /* M�todo para extraer un elemento de la pila. */
        /* Se extrae siempre el elemento que est� en el tope. */
        /* Retorna la informaci�n del elemento extra�do. */
        T Extraer()throws Exception;
        
        /* M�todo que retorna la informaci�n del elemento tope */
        T Tope();

        /* M�todo que retorna true si la pila est� vac�a (sin elementos) */
        /* y false en caso contrario */
        boolean Vacia();
    }

