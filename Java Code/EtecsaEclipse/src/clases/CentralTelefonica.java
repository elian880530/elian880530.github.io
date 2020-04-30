package src.clases;

import src.eda.*;
/*import eda.ListaDE;
import eda.ListaSE;*/
//import clases.CentralProvincial;
public class CentralTelefonica {

	public ListaDE<Arbol<Central>> Provincias;

	public CentralTelefonica(ListaDE<Arbol<Central>> provincias) {
		super();
		Provincias = provincias;
	}

	public CentralTelefonica() {
		super();
		Provincias = new ListaDE<Arbol<Central>>();
	}
	
	public void AdicionarProv(CentralProvincial cp)throws Exception{
		 for(int i=0;i<Provincias.Longitud();i++){
		if(Provincias.Obtener(i).getRaiz().nombre.equals(cp.nombre))
			throw new Exception("Nombre incorrecto");
			 }
		 Arbol<Central> prov = new Arbol<Central>(cp);
		 Provincias.Adicionar(prov);
	}

	public boolean Buscar(String cp1,String cp2,String cm1,String cm2)throws Exception{
		ListaSE<Central> lista = new ListaSE<Central>();
		ListaSE<Central> lista2 = new ListaSE<Central>();
		int aux=-1;
		int aux1=-1;

		  for(int i=0;i<Provincias.Longitud();i++){
		        if(Provincias.Obtener(i).getRaiz().nombre.equals(cp1)){
		        int pos=i;
		        Provincias.Obtener(pos).PreOrden(lista);
		              for (int j = 0; j < lista.Longitud(); j++){
		                        if (lista.Obtener(j).nombre.equals(cm1)){
		                        aux=1;
		                        break;
		                        }
		                        
		               }
		              break;
		          }
		          
		    }

		  for(int i=0;i<Provincias.Longitud();i++){
		        if(Provincias.Obtener(i).getRaiz().nombre.equals(cp2)){
		        int pos=i;
		        Provincias.Obtener(pos).PreOrden(lista2);
		              for (int j = 0; j < lista2.Longitud(); j++){
		                        if (lista2.Obtener(j).nombre.equals(cm2)){
		                        aux1=1;
		                        break;
		                        }
		                        
		               }
		              break;
		          }
		          
		    }
		 if(aux1==1&&aux==1)
		 return true;
		 return false;
		}


		public double Precio(String cp1,String cp2,String cm1,String cm2,double min)throws Exception{
		          
		           ListaSE<Central> lista = new ListaSE<Central>();
		           double cont1=0;
		           double cont2=0;
		           double result=0;
		           double aux=0;
		           boolean var=Buscar(cp1,cp2,cm1,cm2);
		           
		 if(var==true){
		           
		      if(cp1.equals(cp2))
		              result=5*min;
		       else{ 
		            for(int i=0;i<Provincias.Longitud();i++){
		                if (Provincias.Obtener(i).getRaiz().nombre.equals(cp1)){
		                cont1=i;
		                break;
		                }
		              }
		            for(int i=0;i<Provincias.Longitud();i++){
		                if (Provincias.Obtener(i).getRaiz().nombre.equals(cp2)){
		                cont2=i;
		                break;
		                }
		              }
		            if (cont1<cont2)
		            aux=cont2-cont1;
		            if (cont2<cont1)
		            aux=cont1-cont2;
		            result=5*aux*min;
		            }     
		   }
		 
		return result;
		}
           

	
	public void AdicionarMcpio(CentralMunicipal cm, CentralProvincial cp, String con) throws Exception{
		
		Arbol<Central> mcpio = new Arbol<Central>(cm);
		int pos = -1;
		
		for (int i = 0; i < Provincias.Longitud(); i++)
			if(Provincias.Obtener(i).getRaiz().nombre.equals(cp.nombre))
				pos = i;
		if (pos != -1)
		{
			if(Provincias.Obtener(pos).EsHoja() && con.equals(""))
				Provincias.Obtener(pos).AddSubArbol(mcpio);
			else
				if(Provincias.Obtener(pos).EsHoja() && !con.equals(""))
					throw new Exception("La Central no Existe");
				else
				{
						ListaSE<Central> lista = new ListaSE<Central>();
						
						Provincias.Obtener(pos).PreOrden(lista);
						
						for (int i = 0; i < lista.Longitud(); i++)
							if (lista.Obtener(i).nombre.equals(cm.nombre))
								throw new Exception("La Central ya Existe");
						
						int cant = Provincias.Obtener(pos).Grado();
						for (int i = 0;i < cant; i++)
						{
							AddMcpio(Provincias.Obtener(pos).subArbol(i), cm, con);
					}
				}	
		}
	}
	
	private boolean AddMcpio(Arbol<Central> mcpio, CentralMunicipal cm, String con) throws Exception{
		
		Arbol<Central> c = new Arbol<Central>(cm);		
		
		if(mcpio.getRaiz().nombre.equals(con))
		{
			mcpio.AddSubArbol(c);
		}
		else
		{
			if(!mcpio.EsHoja())
			{
				int cant = mcpio.Grado();
				for (int i = 0;i < cant; i++)
				{
					if (mcpio.subArbol(i).getRaiz().nombre.equals(con));
					mcpio.AddSubArbol(c);
					return true;
				}
				for (int i = 0; i < cant; i++)
					return AddMcpio(mcpio.subArbol(i), cm, con);
			}
		}
		return false;
	}

	public ListaSE<String> GetProv(){
		
		ListaSE<String> lista = new ListaSE<String>();
		
		if (!Provincias.Vacia())
			for (int i = 0; i < Provincias.Longitud(); i++)
				lista.Adicionar(Provincias.Obtener(i).getRaiz().nombre);
		
		return lista;
	}
	
	public ListaSE<String> GetMcpios(String cp) throws Exception{
		
		ListaSE<Central> lista = new ListaSE<Central>();
		ListaSE<String> list = new ListaSE<String>();
		
		if (!Provincias.Vacia())
			
			
			for (int i = 0; i < Provincias.Longitud(); i++){
				if (Provincias.Obtener(i).getRaiz().nombre.equals(cp)){
					Provincias.Obtener(i).PreOrden(lista);
					}
				}
		for (int i = 0; i < lista.Longitud(); i++)
			list.Adicionar(lista.Obtener(i).nombre);
		return list;
	}
public ListaSE<Central> GetMcpios2() throws Exception{
		
		ListaSE<Central> lista = new ListaSE<Central>();
		ListaSE<Central> list = new ListaSE<Central>();
		Provincias.Obtener(0).PreOrden(list);
		
		//if (!Provincias.Vacia())
			
		
		
		return list;
	}
public ListaSE<String> GetMcpios3(String a) throws Exception{
	
	ListaSE<String> lista = new ListaSE<String>();
	ListaSE<Central> list = new ListaSE<Central>();
	
	for(int i=0;i<Provincias.Longitud();i++){
		if(Provincias.Obtener(i).getRaiz().equals(a)){
			Provincias.Obtener(i).PreOrden(list);
			
			break;
		}
		
		
		
	}
	for(int i=0;i<list.Longitud();i++){
		lista.Adicionar(list.Obtener(i).toString());
		
		
		
	}
	//if (!Provincias.Vacia())
		
	
	
	return lista;
}
}
