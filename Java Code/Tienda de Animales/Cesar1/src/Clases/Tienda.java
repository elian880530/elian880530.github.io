package Clases;

import java.awt.Frame;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.crypto.spec.PSource;

public class Tienda implements Serializable {
	private int cantReal;
	private Mascota MisMascotas[];

	public Tienda() {
		cantReal = 0;
		MisMascotas = new Mascota[20];
	}
	
	public Mascota[] getMisMascotas() {
		return MisMascotas;
	}
	
	public int getCantReal() {
		return cantReal;
	}

	public void setMisMascotas(Mascota[] misMascotas) {
		MisMascotas = misMascotas;
	}

	public Mascota getObjPos(int pos){
		return MisMascotas[pos];
	}
	///--------------------------------------------------------------------------------
	
	//Ingresar Mascotas a la Tienda
	public void ingresarMascotas(Mascota mascota) throws Exception {
		if (buscarM(mascota.getCode()) == -1) {
			MisMascotas[cantReal] = mascota;
			cantReal++;			
		} else {
			throw new Exception("La mascota ya existe");
		}
	}
	//Buscar una mascota dada
	public int buscarM(String code) {
		int valor = -1;
		for (int i = 0; i < cantReal; i++) {
			if (MisMascotas[i].getCode().equals(code))
				valor = i;
		}
		return valor;
	}
	//Vender Mascota

	public void eliminarMascota(int posEliminar) throws Exception {
		for (int i = posEliminar; i < cantReal; i++) {
			MisMascotas[i]=MisMascotas[i+1];
		}
		cantReal--;
	}
	//Conocer cuantas proceden de un país dado
	public int cantUnPais(String pais) {
		int cont = 0;
		for (int i = 0; i < cantReal; i++) {
			if (MisMascotas[i].getPaisProced().equals(pais))
				cont++;
		}
		return cont;
	}
	
	//Conocer el precio de cualquier mascota
	public double Precio(String cod){
						
		return MisMascotas[buscarM(cod)].precios();
	}
	
	//Conocer el/las mascotas de mayor edad
	public Mascota[] Mayores() {
		Mascota[] result;
		
		int mayor = mayorMascota();
		int cont = 0;
		for (int i = 0; i < cantReal; i++) {
			if (mayor == MisMascotas[i].getCantMeses())
				cont++;
		}
		result = new Mascota[cont];
		cont = 0;
		for (int i = 0; i < cantReal; i++) {
			
			 if(MisMascotas[i].getCantMeses()==mayor){			
				 result[cont]=MisMascotas[i];
				 cont++; 
			 }
		
		}
		return result;
	}
	
	public int mayorMascota() {
		int mayor = 0;
		for (int i = 0; i < cantReal; i++) {
			if (mayor < MisMascotas[i].getCantMeses())
				mayor = MisMascotas[i].getCantMeses();
		}
		return mayor;
	}
	//El color que menos predomina entre las mascotas de la tienda.
	public String[] colorMenosPredominante(){
		
		String[] color = new String[cantReal];
		int[] cant = new int[cantReal];
		String col = "";
		int cont = 0, pos = 0;
		for (int i = 0; i < cantReal; i++){
			col = MisMascotas[i].getColorPredom();
			for (int j = 0; j < cantReal; j++) 
				if(col.equals(MisMascotas[j].getColorPredom())){
					cont++;
			}
			color[pos] = col;
			cant[pos] = cont;
			pos++;
			cont = 0;
		}
		
		cont = cant[0];
		for (int i = 1; i < cant.length; i++){ 
			if(cont < cant[i])
				cont = cant[i];
		}
		
		String[] aux = new String[cant.length + 1];
		pos = 0;
		
		for(int i = 0; i < cant.length; i++)
			if(cant[i] == cont){
				aux[pos] = color[i];
				pos++;
			}
		
		return aux;
	}
		
	//Obtener un listado ordenado alfabéticamente por países de procedencia
	public void OrdAlf() {
	
		Mascota aux;	
		for (int i = 0; i < cantReal - 1; i++) {
			for (int j = i + 1; j < cantReal; j++) {
				if (MisMascotas[i].getPaisProced().charAt(0) > MisMascotas[j].getPaisProced().charAt(0)) {
					aux = MisMascotas[i];
					MisMascotas[i] = MisMascotas[j];
					MisMascotas[j] = aux;
				}
			}
		}
	}
	
	//Obtener un listado ordenado alfabéticamente por 
	//países de procedencia sin modificar el listado principal
	public Mascota[] OrdAlfSinModificar() {
		Mascota[] masc = new Mascota[cantReal];
		int pos = 0;
		Mascota aux;
		
		for (int i = 0; i < cantReal; i++) {
			masc[pos] = MisMascotas[i];
		}
		
		for (int i = 0; i < cantReal - 1; i++) {
			for (int j = i + 1; j < cantReal; j++) {
				if (masc[i].getPaisProced().charAt(0) > masc[j].getPaisProced().charAt(0)) {
					aux = masc[i];
					masc[i] = masc[j];
					masc[j] = aux;
				}
			}
		}
		return masc;
	}
	
	public int Pos(String[] masc, String tu) {
		for (int i = 0; i < cantReal; i++) {
			if (tu.equals(MisMascotas[i]))
				return i;
		}
		return -1;
	}

	public Mascota obtenerMascota(int pos) {
		Mascota m = null;
		if (pos < MisMascotas.length && pos >= 0) {
			m = MisMascotas[pos];
		}
		return m;
	}
	
	public boolean menor(String a, String b){
		char [] an = a.toCharArray();
		char [] bn = b.toCharArray();
		for (int i = 0; i < an.length; i++){						
			if (i>bn.length)
				return false;
			if(an[i]<bn[i])
				return true;
			else
				if(an[i]>bn[i])
					return false;
		}
		if (bn.length>an.length)
			return true;
		return false;
	}
	
	//Salvar en el fichero
	public void Guardar() {
		FileOutputStream fos;
		try {
			fos = new FileOutputStream("tienda.dat");
			try {
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(this);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}

	//Cargar del Fichero
	public void Cargar() {
		FileInputStream fos;
		Tienda t = null;
		try {
			fos = new FileInputStream("tienda.dat");
			try {
				ObjectInputStream oos = new ObjectInputStream(fos);
				try {
					t = (Tienda) oos.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		this.MisMascotas=t.getMisMascotas();
		this.cantReal=t.cantReal;
		
	}
	
}
