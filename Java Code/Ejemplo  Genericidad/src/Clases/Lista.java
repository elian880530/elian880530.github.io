package Clases;

import java.util.ArrayList;


public class Lista<T> {
	T[] MisElementos;
	int CantRealElementos;
	
	@SuppressWarnings("unchecked")
	public Lista() {
		super();
		CantRealElementos =0;
		MisElementos = (T[])new Object[1000000];
	}
	
	public boolean Adicionar(T t) throws Exception {
		try {
			MisElementos[CantRealElementos++] = t;
			return true;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}	
	}
	
	public boolean Eliminar(int pos) throws Exception{
		try {
			for (int p = pos; p < CantRealElementos; p++) {
				MisElementos[p] = MisElementos[p+1];
			}
			CantRealElementos--;
			return true;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public T Obtener(int pos) throws Exception {
		try {
			return MisElementos[pos];
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public int CantidadReal() {
		return CantRealElementos;
	}
	
	public int Longitud() {
		return MisElementos.length;
	}
	
}
