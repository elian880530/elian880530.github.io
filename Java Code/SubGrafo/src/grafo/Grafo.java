package grafo;

public abstract class Grafo<T>{

	public Grafo(){
	}
	
	public abstract boolean esVacio();
	public abstract boolean estaElVertice(T v);
	public abstract boolean estaElArco(Arco<T> a);
	public abstract void insertaVertice(T v);
	public abstract void insertaArco(Arco<T> a);
        public abstract int cantVertices();

	//tener en cuenta que realmente la clase abstracta tiene muchos otros metodos.

}