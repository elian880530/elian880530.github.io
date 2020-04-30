package eda;

/* Clase Arco que ser� usada en la definici�n de la 
 * interface IGrafo */
public class Arco <T>{
    /* V�rtices que componen el arco */
    protected T vertice1;
    protected T vertice2;

    /* Constructor */
    public Arco(T vertice1, T vertice2)
    {
        this.vertice1 = vertice1;
        this.vertice2 = vertice2;
    }

    /* M�todos de acceso a los v�rtices */
    public T getVertice1()
    {
        return vertice1;
    }
    public T getVertice2()
    {
        return vertice2;
    }
}
