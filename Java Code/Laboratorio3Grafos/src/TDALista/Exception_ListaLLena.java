package TDALista;

public class Exception_ListaLLena extends Exception_list{
    
    public Exception_ListaLLena() {
        this("La lista est� llena");
    }
    public Exception_ListaLLena(String mensaje) {
        super(mensaje);
    }
    
}
