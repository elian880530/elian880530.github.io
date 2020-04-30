package src.eda;

public class NodoDE<T> {

    protected T info;
    protected NodoDE<T> anterior;
    protected NodoDE<T> siguiente;

    public NodoDE(T info, NodoDE<T> anterior, NodoDE<T> siguiente) {
        this.info = info;
        this.anterior = anterior;
        this.siguiente = siguiente;
    }

    public T getInfo() {
        return info;
    }

    public NodoDE<T> getAnterior() {
        return anterior;
    }
    
    public NodoDE<T> getSiguiente() {
        return siguiente;
    }

    public void setAnterior(NodoDE<T> anterior) {
        this.anterior = anterior;
    }
    
    public void setSiguiente(NodoDE<T> siguiente) {
        this.siguiente = siguiente;
    }
}
