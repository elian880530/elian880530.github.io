package clases;

import biblioteca.GeneradorDatos;
import eda.*;

public class Biblioteca {

	public Biblioteca() {

		biblioteca.GeneradorDatos gen = new GeneradorDatos();
		materiales = gen.genListaMateriales();
		tarjetasAsociado = gen.genListaTarjetas();

		try {
			solicitudes = gen.genListaSolicitudes(materiales, tarjetasAsociado);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private ILista<Material> materiales;

	public ILista<Material> getMateriales() {
		return materiales;
	}

	public ILista<TarjetaAsociado> getTarjetasAsociado() {
		return tarjetasAsociado;
	}

	public ILista<Solicitud> getSolicitudes() {
		return solicitudes;
	}

	private ILista<TarjetaAsociado> tarjetasAsociado;
	private ILista<Solicitud> solicitudes;

}
