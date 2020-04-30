
package TDALista;


public class Exception_PosFueraDeRango extends Exception_list{
    
    public Exception_PosFueraDeRango() 
    {
        this("La posicion esta fuera de los limites de la lista");
    }
    public Exception_PosFueraDeRango(int posicion) 
    {
        this("La posicion: " + posicion + " está fuera de los limites de la lista");
    }
    public Exception_PosFueraDeRango(String mensaje) {
        super(mensaje);
    }
    
}
