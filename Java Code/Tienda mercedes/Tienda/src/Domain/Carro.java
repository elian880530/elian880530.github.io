package Domain;

public class Carro {
	
	private int id;
	private int costo;
	private String nombre;
	private String color;
	
	public Carro(int id, int costo, String nombre, String color) {
		super();
		this.id = id;
		this.costo = costo;
		this.nombre = nombre;
		this.color = color;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCosto() {
		return costo;
	}

	public void setCosto(int costo) {
		this.costo = costo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	
	

}
