
package TDALista;

public class Exception_ListaVacia extends Exception_list{
    
    /** Creates a new instance of ListaVacia */
    public Exception_ListaVacia() {
       this("La lista está vacía");
    }
    public Exception_ListaVacia(String mensaje) {
        super(mensaje);
    }
    
}
