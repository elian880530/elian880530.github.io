package Domain;

public class Cliente {
	
	private int CI;
	private String nombre;
	private String primer_apellido;
	private String segundo_apellido;
	private String direccion;
	private boolean sexo;
	private String estado_civil;
	
	public Cliente(int cI, String nombre, String primerApellido,
			String segundoApellido, String direccion, boolean sexo,
			String estadoCivil) {
		super();
		CI = cI;
		this.nombre = nombre;
		primer_apellido = primerApellido;
		segundo_apellido = segundoApellido;
		this.direccion = direccion;
		this.sexo = sexo;
		estado_civil = estadoCivil;
	}

	public int getCI() {
		return CI;
	}

	public void setCI(int cI) {
		CI = cI;
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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public boolean getSexo() {
		return sexo;
	}

	public void setSexo(boolean sexo) {
		this.sexo = sexo;
	}

	public String getEstado_civil() {
		return estado_civil;
	}

	public void setEstado_civil(String estadoCivil) {
		estado_civil = estadoCivil;
	}
	
	

}
