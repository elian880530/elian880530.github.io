package Clases;

import org.omg.CORBA.BooleanSeqHolder;

public class UCI {
	Lista<Persona> MisPersonas = new Lista<Persona>();
	
	
	public boolean Adicionar(Persona P) throws Exception{
		try {
			return MisPersonas.Adicionar(P);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public Persona Obtener(int pos) throws Exception {
		try {
			return MisPersonas.Obtener(pos);
		} catch (Exception e) {
			throw new Exception(e.getLocalizedMessage());
		}
	}

	public boolean Eliminar(int pos) throws Exception {
		try {
			MisPersonas.Eliminar(pos);
			return true;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	public Lista<Persona> DevolverLista() {
		return MisPersonas;
	}
	
	public int CantidadReal() {
		return MisPersonas.CantidadReal();
	}
}
