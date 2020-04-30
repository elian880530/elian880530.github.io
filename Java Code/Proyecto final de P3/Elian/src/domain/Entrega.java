package domain;

public class Entrega {
	private String usuario;
	private String nroserie;

	
	public Entrega(String usuario,String nroserie) {
		super();
		this.usuario = usuario;
		this.nroserie = nroserie;
	}

	
	public String getNroserie() {
		return nroserie;
	}


	public String getUsuario() {
		return usuario;
	}

	}