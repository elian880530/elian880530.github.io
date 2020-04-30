package clases;

public class Solicitud {
	
	private String nombre, CI, direccion;
	private boolean asociado;

	private Material material;

	public Solicitud(String nombre, String ci, String direccion,
			boolean asociado, Material material) {
		this.nombre = nombre;
		CI = ci;
		this.direccion = direccion;
		this.asociado = asociado;
		this.material = material;
	}
	
    @Override
	public String toString(){
		return "< " + nombre + ", " +  CI + ", " + (asociado ? "Asociado" : "No asociado") + ", Material: " + material.getID() +  " >";
	}

	
}
