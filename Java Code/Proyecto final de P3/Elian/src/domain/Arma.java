package domain;

public class Arma {
private String nombre;
private String tipo;
private String nroserie;
public String getNombre() {
	return nombre;
}
public String getNroserie() {
	return nroserie;
}
public void setNroserie(String nroserie) {
	this.nroserie = nroserie;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public String getTipo() {
	return tipo;
}
public void setTipo(String tipo) {
	this.tipo = tipo;
}
public Arma(String nombre, String tipo, String nroserie) {
	super();
	this.nombre = nombre;
	this.tipo = tipo;
	this.nroserie = nroserie;
}




}
