package src.eda;

public class ListaSE<T> 
{
  private NodoSE<T> cabeza;
  private NodoSE<T> ultimo;
  
   
	public ListaSE() 
	{
	
	}
	
	 public void Adicionar(T pDato)
	 {
	        NodoSE<T> NodoSE = new NodoSE<T>(pDato,null);
	        if (cabeza == null) {
	        	cabeza = NodoSE;
	        }
	        else{
	            NodoSE<T> Cursor = cabeza;
	            while(Cursor.getSiguiente()!=null){
	                Cursor = Cursor.getSiguiente();
	            }
	            Cursor.setSiguiente(NodoSE);
	        }
	    }
	 
	 public void AdicionarSinRepetir(T pDato)throws Exception
	 {
		 NodoSE<T> NodoSE = new NodoSE<T>(pDato,null);
	        if (cabeza == null) {
	        	cabeza = NodoSE;
	        }
	        else{
	            NodoSE<T> Cursor = cabeza;
	            while(Cursor.getSiguiente()!=null && !Cursor.getInfo().equals(pDato))
	                Cursor = Cursor.getSiguiente();
	                if(Cursor.getInfo().equals(pDato))
	                	throw new Exception("Elemento repetido");
	                else
	                {
	                	  Cursor.setSiguiente(NodoSE);
	                }	            
	          
	        }
	    }
		 
	 

	
	 public void Insertar(T pDato, int pos) throws   Exception{
	        if(pos < 0 || pos > Longitud()-1)
	            throw new Exception("Posicion Incorrecta");
	        else{
	            NodoSE<T> NodoSE = new NodoSE<T>(pDato,null);
	            if(pos ==0){
	                NodoSE.setSiguiente(cabeza);
	                cabeza = NodoSE;
	            }else
	            {
	                NodoSE<T> Cursor = cabeza;
	                int Contador = 0;
	                while(Contador++ < pos-1){
	                    Cursor = Cursor.getSiguiente();
	                }
	                NodoSE.setSiguiente(Cursor.getSiguiente());
	                Cursor.setSiguiente(NodoSE);
	            }
	            
	        }
	    }       

	
	 public T Obtener(int pos) throws Exception
	 {
	        if(pos<0 || pos>Longitud()-1){
	            throw  new Exception("Posicion Invalida");
	        }
	        else{
	            NodoSE<T> Cursor = cabeza;
	            int Contador =0;
	            while (Contador++<pos) {
	                Cursor = Cursor.getSiguiente();
	            }
	            return Cursor.getInfo();
	        }
	    }

	
	
	 public void Eliminar(int pos) throws Exception{
	        if(pos<0 || pos>Longitud()-1){
	            throw  new Exception("Posicion Invalida");
	        }else{
	            if(pos == 0)       
	                cabeza = cabeza.getSiguiente();
	            else{
	                NodoSE<T> Cursor = cabeza;
	                int Contador = 0;
	                while(Contador++< pos-1)
	                    Cursor = Cursor.getSiguiente();
	                Cursor.setSiguiente(Cursor.getSiguiente().getSiguiente());
	            }    
	         }
	    }

	
	public boolean IsEmty()
	{
		return cabeza == null;
	}
	
	public int Longitud() {
		if(cabeza == null)
			return 0;
		else{
			NodoSE<T> cursor = cabeza;
			int cont = 1;
			while (cursor.getSiguiente() != null){
				cursor = cursor.getSiguiente();
				cont++;
			}	
			return cont;
		}
	}
	
	public boolean Exist(T dato)
	{
		NodoSE<T> cursor = cabeza;
		while (cursor.getSiguiente()!=null && !cursor.getInfo().equals(dato)) 
		{
			cursor = cursor.getSiguiente();
		}
		if(cursor.getInfo().equals(dato))
			return true;
		else
			return false;
		
	}
	
	public void AdicionarU(T pdato)
	{
		NodoSE<T> NodoSE = new NodoSE<T>(pdato,null);
		if(cabeza == null)
			cabeza = ultimo = NodoSE;
		else
		{
			ultimo.setSiguiente(NodoSE);
			ultimo = NodoSE;
		}
		
	}
	
	public void EliminarPosPares()
	{
		
		cabeza = cabeza.getSiguiente();
		NodoSE<T> cursor = cabeza;
		while(cursor.getSiguiente()!=null)
		{			
				NodoSE<T> aux = cursor.getSiguiente();
				cursor.setSiguiente(aux.getSiguiente());
				cursor = cursor.getSiguiente();
		}
 	}
	
	public void EliminarPosImpar()
	{
		NodoSE<T> cursor = cabeza;
		while(cursor.getSiguiente()!=null)
		{
			NodoSE<T> aux = cursor.getSiguiente();
			cursor.setSiguiente(aux.getSiguiente());
			cursor = cursor.getSiguiente();
		}
	}
	
	public void EliminarX() throws Exception
	{
		for (int i = 0; i <= (Longitud()/2)+1; i++) 
		{
		  Eliminar(i);	
		}
	}
	
	

}
