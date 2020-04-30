package domain;


public class Usuario {
	
	private String nombre;
	private String solapin;
	private String rol;
	private String facultad;
	
	
	public Usuario(String nombre,String solapin,String rol, String facultad) {
		this.nombre = nombre;
		this.solapin = solapin;
		this.rol= rol;
		this.facultad= facultad;
	}

	public String getSolapin() {
		return solapin;
	}

	public void setSolapin(String solapin) {
		this.solapin= solapin;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFacultad() {
		return facultad;
	}

	public void setFacultad(String facultad) {
		this.facultad= facultad;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	
}
