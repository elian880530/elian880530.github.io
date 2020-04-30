package Domain;

public class Vendedor {
	
	private int solapin;
	private String nombre;
	private String primer_apellido;
	private String segundo_apellido;
	
	public Vendedor(int solapin, String nombre, String primerApellido,
			String segundoApellido) {
		super();
		this.solapin = solapin;
		this.nombre = nombre;
		primer_apellido = primerApellido;
		segundo_apellido = segundoApellido;
	}

	public int getSolapin() {
		return solapin;
	}

	public void setSolapin(int solapin) {
		this.solapin = solapin;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPrimer_apellido() {
		return primer_apellido;
	}

	public void setPrimer_apellido(String primerApellido) {
		primer_apellido = primerApellido;
	}

	public String getSegundo_apellido() {
		return segundo_apellido;
	}

	public void setSegundo_apellido(String segundoApellido) {
		segundo_apellido = segundoApellido;
	}
	
	

}
