package Domain;

public class Venta {
	
	private int CI_cliente;
	private int Solapin_trabajador;
	private int Chapilla_carro;
	
	public Venta(int cICliente, int solapinTrabajador, int chapillaCarro) {
		super();
		CI_cliente = cICliente;
		Solapin_trabajador = solapinTrabajador;
		Chapilla_carro = chapillaCarro;
	}

	public int getCI_cliente() {
		return CI_cliente;
	}

	public void setCI_cliente(int cICliente) {
		CI_cliente = cICliente;
	}

	public int getSolapin_trabajador() {
		return Solapin_trabajador;
	}

	public void setSolapin_trabajador(int solapinTrabajador) {
		Solapin_trabajador = solapinTrabajador;
	}

	public int getChapilla_carro() {
		return Chapilla_carro;
	}

	public void setChapilla_carro(int chapillaCarro) {
		Chapilla_carro = chapillaCarro;
	}
	
	

}
