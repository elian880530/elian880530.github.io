package domain;

public class ArmaOficial {
	private String nombre;
	private String usuario;
	private String grado;
	private String cargo;
	private String sello;
	private String nombreArma;
	private String tipo;
	public String getNombre() {
		return nombre;
	}
	public String getUsuario() {
		return usuario;
	}
	public String getGrado() {
		return grado;
	}
	public String getCargo() {
		return cargo;
	}
	public String getSello() {
		return sello;
	}
	public String getNombreArma() {
		return nombreArma;
	}
	public String getTipo() {
		return tipo;
	}
	public ArmaOficial(String nombre, String usuario, String grado,
			String cargo, String sello, String nombreArma, String tipo) {
		super();
		this.nombre = nombre;
		this.usuario = usuario;
		this.grado = grado;
		this.cargo = cargo;
		this.sello = sello;
		this.nombreArma = nombreArma;
		this.tipo = tipo;
	}




}
