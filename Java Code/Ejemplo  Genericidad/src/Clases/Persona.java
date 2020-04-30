package Clases;

public class Persona {
	String CI, Nombre;

	public Persona(String ci, String nombre) {
		super();
		CI = ci;
		Nombre = nombre;
	}


	public String getNombre() {
		return Nombre;
	}


	public void setNombre(String nombre) {
		Nombre = nombre;
	}


	public String getCI() {
		return CI;
	}

	public void setCI(String ci) {
		CI = ci;
	}
}
