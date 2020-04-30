package listagenericanetbeans;


public class Lista<T extends Persona> {
	private T[] Elementos;
	private int CantRealElementos;
	
	@SuppressWarnings("unchecked")
	
	
	//Constructor que crea una lista para almacenar 1000000 de elementos
	public Lista() {
		super();
		CantRealElementos =0;
		Elementos = (T[])new Persona[1000000];
	}
	
	
	@SuppressWarnings("unchecked")
	
	/*
	 * Constructor que crea una lista para almacenar una Cantidad de 
	 * elementos definida(variable pLongitud)
	*/
	public  Lista(int pLongitud) {
		CantRealElementos=0;
		Elementos = (T[])new Object[pLongitud];
	}
	
	/*
	 * Adiciona un elemento (t) a la lista
	 * 
	 */
	public boolean Adicionar(T valor) throws Exception {
		try {
			if(CantRealElementos< Elementos.length){
				Elementos[CantRealElementos++] = valor;
				return true;
			}
			else {
				throw new Exception("No se Puede Adicionar. Lista LLena");
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}	
	}
	
	
	/*
	 *Elimina un Elemento dada su posicion
	 * 
	 */
	public boolean Eliminar(int pos) throws Exception{
		try {
			if (pos>=0 && pos<CantRealElementos) {
				for (int p = pos; p < CantRealElementos; p++) {
					Elementos[p] = Elementos[p+1];
				}
				CantRealElementos--;
				return true;
			}
			else {
				throw new Exception("Posicion Fuera de Rango");
			}
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	/*
	 *Elimina los elementos que se enuentran en el rango comprendido entre
	 *posInicial y posFinal (incluye los extremos)
	 * 
	 */
	public boolean EliminarRango(int posInicial, int posFinal) throws Exception{
		try {
			if(posInicial<0 || posInicial >= CantRealElementos)
				throw new Exception("Posicion Inicial Fuera de Rango");
			if(posFinal<0 || posFinal >= CantRealElementos)
				throw new Exception("Posicion Final Fuera de Rango");
			if(posFinal<posInicial)
				throw new Exception("Posicion Final Menor que Posicion Inicial");
			
			//Elimino los elementos comprendidos en el rango (incluye los extremos)
			for (int p = posInicial; p <=posFinal; p++) {
				Eliminar(posInicial);
			}
			return true;
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	/*
	 * Solo mantiene en la lista los elementos que se encuentran comprendidos en el
	 * rango (incluye los extremos). El resto de los elementos son eliminados
	 * 
	 */
	public boolean MantenerRango(int posInicial, int posFinal) throws Exception {
		try {
			
			//Elimina los Elementos a la derecha de la posFinal sin incluir posFinal
				if(posFinal<CantRealElementos-1){
					EliminarRango(posFinal+1,CantRealElementos-1);
				}
			//Elimina los Elementos a la Izquierda de posInicial sin incluir pos Inicial
				if(posInicial>0){
					EliminarRango(0, posInicial-1);
				}
			return true;
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	/*
	 * Devuelve el elemento de la lista que se encuentra en la posicion (pos)
	 * 
	 */
	public T Obtener(int pos) throws Exception {
		try {
			if (pos>=0 && pos<CantRealElementos) {
				return Elementos[pos];
			}
			else {
				throw new Exception("Posicion Fuera de Rango");
			}
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	/*
	 * Inserta un elemento en una posicion comprendida entre 0 y la Cantidad Real
	 * de Elementos
	 *
	 */
	public boolean Insertar(int pos, T valor) throws Exception{
		try {
			if(pos< 0 || pos>CantRealElementos-1){
				throw new Exception("Posicion Fuera de Rango");
			}
			if (CantRealElementos==Elementos.length) {
				throw new Exception("Lista LLena. No se puede Insertar");
			}
			//Corro los Elementos un lugar hacia la derecha
			for (int p = CantRealElementos-1; p >= pos; p--) {
				Elementos[p+1]=Elementos[p];
			}
			//Inserto
			Elementos[pos] = valor;
			CantRealElementos++;
			return true;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	/*
	 *  Devuelve la posicion donde se encuentra un valor determinado en caso
	 *  de que exista. Develve -1 en caso de no existir
	 * 
	 * */
	public int BuscarP(T valor){
		for (int p = 0; p < CantRealElementos; p++) {
			if (Elementos[p].equals(valor)) {
				return p;
			}
		}
		return -1;
	}
	/*
	 * Indica si existe o no un elemento dado.
	 * */
	public boolean BuscarB(T valor){
		return BuscarP(valor)!=-1;
	}
	
	/*
	 * Indica la Cantidad Real de Elementos que se encuentran en la Lista 
	 *
	 */
	public int CantidadElementos() {
		return CantRealElementos;
	}
	
	/*
	 * Devuelve la longitud de la Lista, es decir la capacidad 
	 */
	public int Longitud() {
		return Elementos.length;
	}
        
        public void OrdenarDescendente()throws Exception {
		try {
			for (int i = 0; i < CantRealElementos-1; i++) {
				for (int j = i+1; j < CantRealElementos; j++) {
					if(Elementos[i].Solapin<Elementos[j].Solapin){
						T temp = Elementos[i];
						Elementos[i]=Elementos[j];
						Elementos[j] = temp;
					}
				}
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public void OrdenarAscendente()throws Exception {
		try {
			for (int i = 0; i < CantRealElementos-1; i++) {
				for (int j = i+1; j < CantRealElementos; j++) {
					if(Elementos[i].Solapin>Elementos[j].Solapin){
						T temp = Elementos[i];
						Elementos[i]=Elementos[j];
						Elementos[j] = temp;
					}
				}
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
}
