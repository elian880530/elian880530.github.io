package src.eda;

public abstract class ILista<T> {

    public void Adicionar(T x){}

    public void Insertar(T x, int pos) throws Exception {}

    public T Obtener(int pos) throws Exception {
        return null;
    }

    public void Eliminar(int pos) throws Exception {}

    public int Longitud() {
        return 0;
    }

    public boolean Vacia() {
        return true;
    }
    
    public int Buscar(T x) throws Exception {
        return 0;
    }
}
