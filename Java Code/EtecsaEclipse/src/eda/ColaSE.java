package src.eda;

/**
 * Implementaci�n de la Cola SE utilizando nodos SE
 * @author Gabriel La O Ram�rez
 *
 * @param <T>
 */
public class ColaSE<T> {
	
	/**
	 * Nodo SE - clase est�tica y privada de la clase ColaSE
	 * @author Gabriel La O Ram�rez
	 *
	 * @param <T>
	 */
	private static class NodoSE<T>{
		private T dato;
		private NodoSE<T> siguiente;
		
		/**
		 * Construye un nuevo nodo a partir de un valor
		 * @param dato - valor del nodo
		 */
		public NodoSE(T dato) {
			this.dato = dato;
			siguiente = null;
		}
	}
	
	NodoSE<T> cola;
	NodoSE<T> cabeza;
	
	/**
	 * Construye una cola vac�a 
	 */
	public ColaSE()
	{
		cola = cabeza = null;
	}
	
	/**
	 * Extrae el primer objeto de la cola y lo devuelve
	 * @return T
	 * @throws Exception - Cola vac�a
	 */
	public T Extraer() throws Exception
	{
		if (cabeza == null)
			throw new Exception("Cola vacia");
		
		T objeto = cabeza.dato;
		cabeza = cabeza.siguiente;
		
		return objeto;
	}
	
	/**
	 * Adiciona un objeto al final de la cola
	 * @param objeto - objeto a adicionar
	 */
	public void Adicionar(T objeto)
	{
		NodoSE<T> nuevo = new NodoSE<T>(objeto);
		
		if (cabeza == null){
			cabeza = nuevo;
			cola = nuevo;
		}
		else{
		cola.siguiente = nuevo;
		cola = nuevo;
		}
	}
	
	/**
	 * Retorna el primer objeto de la cola
	 * @return T
	 * @throws Exception
	 */
	public T Frente() throws Exception
	{
		if (cabeza == null)
			throw new Exception("Cola vacia");
		
		return cabeza.dato;
	}
	
	/**
	 * Retorna el �ltimo objeto de la la cola
	 * @return T
	 * @throws Exception
	 */
	public T Fondo() throws Exception
	{
		if (cola == null)
			throw new Exception("Cola vacia");
		
		return cola.dato;
	}
	
	/**
	 * Retorna true si la cola est� vac�a
	 * @return boolean
	 */
	public boolean EsVacia()
	{
		return cabeza == null;
	}
}
