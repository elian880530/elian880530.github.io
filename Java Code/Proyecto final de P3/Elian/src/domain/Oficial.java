package domain;

public class Oficial {
private String nombre;
private String usuario;
private String grado;
private String cargo;
private String sello;
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public String getUsuario() {
	return usuario;
}
public void setUsuario(String usuario) {
	this.usuario = usuario;
}
public String getGrado() {
	return grado;
}
public void setGrado(String grado) {
	this.grado = grado;
}
public String getCargo() {
	return cargo;
}
public void setCargo(String cargo) {
	this.cargo = cargo;
}
public String getSello() {
	return sello;
}
public void setSello(String sello) {
	this.sello = sello;
}
public Oficial(String nombre, String usuario, String grado, String cargo,
		String sello) {
	super();
	this.nombre = nombre;
	this.usuario = usuario;
	this.grado = grado;
	this.cargo = cargo;
	this.sello = sello;
}
}
