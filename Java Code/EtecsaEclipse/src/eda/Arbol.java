package src.eda;

public class Arbol<T> 
{
	private T raiz;
	private ListaSE<Arbol<T>> hijos;

	public Arbol(T r) 
	{
	 this.raiz = r;
	 hijos = new ListaSE<Arbol<T>>();
	}

	//devuelve el valor del nodo raiz
	public T getRaiz() 
	{
		return raiz;
	}
	
	//Retorna verdadero si el grado del nodo es cero
	public boolean EsHoja()
	{
		return hijos.IsEmty();
	}
	
	/*Devuelve el Grado del
	 * Arbol(Cantidad de hijos o nodos asociados a el) */
	public int Grado()
	 {
		return hijos.Longitud(); 
	 }
	 
	 //Devuelve la altura del arbol
	 public int Altura()
	 {
		if(EsHoja())
			return 0;
		int max = 0;
		for (int i = 0; i < hijos.Longitud(); i++) 
		{
			try 
			{
				int h = hijos.Obtener(i).Altura();
				if(max < h)
					max = h;
				
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			
		}
		return max+1;
		
	 }
	 
	 
	 
	 //Devuelve el i-nesimo subarbol asociado
	 public Arbol<T> subArbol(int pos) throws Exception
	 {
		 return hijos.Obtener(pos);
	 }
	 
	 //adiciona un subarbol
	 public void AddSubArbol(Arbol<T> a)
	 {
		 hijos.Adicionar(a);
	 }
	 
	//Elimina el nodo de la posicion dada
	 public void PodarArbol(int pos) throws Exception
	 {
		 hijos.Eliminar(pos);
	 }
	 
	 public int GradoArbol() throws Exception
	 {
		 int max = 0;
		 for (int i = 0; i < hijos.Longitud(); i++) 
		 {
			 int grado = subArbol(i).Grado();
			 if(max < grado)
				 max = grado;
			
		 }
		 
		 return max;
	 }
	 
	/*
	 * Realiza un recorrido en PreOrden visitando 
	 primero la raiz y despus los hijos
	 */
	 public ListaSE<T> PreOrden(ListaSE<T> lista) throws Exception
	 {
		if(Grado() == 0)
			lista.Adicionar(raiz);
		else
		{
			lista.Adicionar(raiz);
			for (int i = 0; i < Grado(); i++) 
			{
				subArbol(i).PreOrden(lista);			
				
			}
		}
		return lista;
	 }
	
	 /**/
	 public ListaSE<T> EntreOrden(ListaSE<T> lista) throws Exception
	 {
		 if(Grado()==0)
			lista.Adicionar(raiz);
		 else
		 {
			 subArbol(0).EntreOrden(lista);
			 lista.Adicionar(raiz);
			 for (int i = 1; i < Grado(); i++) 
			 {
				 subArbol(i).EntreOrden(lista); 				
			 }
		 }
		 return lista;
	 }
	 
	 public ListaSE<T> PostOrden(ListaSE<T> lista) throws Exception
	 {
		 if(Grado()== 0)
			 lista.Adicionar(raiz);
		 else
		 {
			 for (int i = 0; i < Grado(); i++) 
			 {
				 subArbol(i).PostOrden(lista);
			 }
			 lista.Adicionar(raiz);
		 }
		return lista;	 
	 }
	 
	 public ListaSE<T> Alo_Ancho(ListaSE<T> lista) throws Exception
	 {
		 ColaSE<Arbol<T>> aux= new ColaSE<Arbol<T>>();
		 aux.Adicionar(this);
		 while (!aux.EsVacia())
		 {
			 Arbol<T> A = aux.Extraer();
			 lista.Adicionar(A.getRaiz());
			 for (int i = 0; i < A.Grado(); i++) 
			 {
				 aux.Adicionar(A.subArbol(i));
			 }
			
		 }
		 return lista;		 
	 }
	 
	 

	

}
