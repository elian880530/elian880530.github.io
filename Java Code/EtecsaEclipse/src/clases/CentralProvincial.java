package src.clases;

import src.eda.*;
//import eda.ListaSE;

public class CentralProvincial extends Central{

	public Arbol<Central> Municipios;

	public CentralProvincial(String nombre, Arbol<Central> municipios) {
		super(nombre);
		Municipios = municipios;
	}

	public CentralProvincial(String nombre) {
		super(nombre);
		Municipios = new Arbol<Central>(null);
	}
	
	public boolean Adicionar(CentralMunicipal cm, String con) throws Exception{
		ListaSE<Central> lista = new ListaSE<Central>();
		
		Municipios.PreOrden(lista);
		for (int i = 0; i < lista.Longitud(); i++)
			if (lista.Obtener(i).nombre.equals(cm.nombre))
				throw new Exception("La Central ya Existe");
		
		return AdicionarMcpio(Municipios, cm, con);
	}
	
	public boolean AdicionarMcpio(Arbol<Central> mcpio, CentralMunicipal cm, String con) throws Exception{
		
		Arbol<Central> c = new Arbol<Central>(cm);		
		
		if (mcpio == null)
		{
			mcpio.AddSubArbol(mcpio);
			return true;
		}
		else
		{
			if(mcpio.getRaiz().nombre == con)
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
						if (mcpio.subArbol(i).getRaiz().nombre == con);
						mcpio.AddSubArbol(c);
						return true;
					}
					for (int i = 0; i < cant; i++)
						return AdicionarMcpio(mcpio.subArbol(i), cm, con);
				}
			}
		}
		return false;
	}
public String toStringP2(){
		
		return super.nombre;
		
		
	}
	
	
}
