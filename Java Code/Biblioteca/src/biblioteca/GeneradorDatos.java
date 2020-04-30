package biblioteca;

import eda.*;
import java.util.*;

import clases.*;

public class GeneradorDatos {

	private Random randObj;

	public GeneradorDatos() {
		randObj = new Random();
	}

	// / <summary>
	// / Metodo para generar aleatoriamente un numero entero dentro de un intervalo
	// / </summary>
	// / <param name="rinic"> Valor maximo del intervalo </param>
	// / <param name="rfin"> Valor maximo del intervalo </param>
	// / <returns> Valor entero aleatorio </returns>
	private int genEnteroAleatorio(int rinic, int rfin) {
		return randObj.nextInt(rfin - rinic + 1) + rinic;
	}

	// / <summary>
	// / Metodo para generar aleatoriamente un numero real dentro de un
	// intervalo
	// / </summary>
	// / <param name="rinic">Valor manimo del intervalo</param>
	// / <param name="rfin">Valor maximo del intervalo</param>
	// / <returns>Valor real aleatorio</returns>
	@SuppressWarnings("unused")
	private double genRealAleatorio(double rinic, double rfin) {
		double rnum = randObj.nextDouble();
		return rinic + (rfin - rinic) * rnum;
	}

	String[] autores = { "Pedro Calderon de la Barca", "Calin, P. G M",
			"Charlotte Bronte", "Choderlos de Laclos ", "Jose Lezama Lima" };

	String[] titulos = { "El Alcalde de Zalamea", "A la luz de las tres lunas",
			"La Familia de Pascual", "Caperucita Roja", "Catulli Carmina",
			"Jane Eyre", "Cinco en una vaina", "Meccano",
			"Crimen y costumbre en la sociedad salvaje", "Mundo tenebroso" };

	String[] editoriales = { "Gente Nueva", "Jose Maerti", "Spring", "Oceano",
			"Letras Cubanas" };

	private String genCarnetIdentidad() {
		String res = "";
		for (int i = 0; i < 11; i++) {
			res += genEnteroAleatorio(0, 9);
		}
		return res;
	}

	static int idCont = 0;

	// / <summary>
	// / Metodo para generar aleatoriamente un libro
	// / </summary>
	// / <returns>Libro aleatorio</returns>
	public Libro genLibro() {

		int id, aPubli, aLleg, cantExist;
		String titulo, autor, editorial;

		id = ++idCont;
		aPubli = genEnteroAleatorio(1990, 2009);
		aLleg = genEnteroAleatorio(aPubli, 2009);
		cantExist = genEnteroAleatorio(1, 100);

		titulo = titulos[genEnteroAleatorio(0, titulos.length - 1)];
		autor = autores[genEnteroAleatorio(0, autores.length - 1)];
		editorial = editoriales[genEnteroAleatorio(0, editoriales.length - 1)];
		TGenero genero = TGenero.values()[genEnteroAleatorio(0, TGenero
				.values().length - 1)];
		return new Libro(id, aPubli, aLleg, cantExist, 0, titulo, autor,
				editorial, genero);
	}

	// / <summary>
	// / Metodo para generar aleatoriamente una revista
	// / </summary>
	// / <returns>Revista aleatorio</returns>
	public Revista genRevista() {

		int id, aPubli, aLleg, cantExist;
		String titulo, autor, editorial;

		id = ++idCont;
		aPubli = genEnteroAleatorio(1990, 2009);
		aLleg = genEnteroAleatorio(aPubli, 2009);
		cantExist = genEnteroAleatorio(1, 100);

		titulo = titulos[genEnteroAleatorio(0, titulos.length - 1)];
		autor = autores[genEnteroAleatorio(0, autores.length - 1)];
		editorial = editoriales[genEnteroAleatorio(0, editoriales.length - 1)];
		TFrecuencia frecuencia = TFrecuencia.values()[genEnteroAleatorio(0,
				TFrecuencia.values().length - 1)];

		return new Revista(id, aPubli, aLleg, cantExist, 0, titulo, autor,
				editorial, frecuencia);

	}

	// / <summary>
	// / Metodo para generar aleatoriamente un acta
	// / </summary>
	// / <returns>Acta aleatorio</returns>
	public ActaCongreso genActa() {

		int id, aPubli, aLleg, cantExist;
		String titulo, autor, editorial;

		id = ++idCont;
		aPubli = genEnteroAleatorio(1990, 2009);
		aLleg = genEnteroAleatorio(aPubli, 2009);
		cantExist = genEnteroAleatorio(1, 100);

		titulo = titulos[genEnteroAleatorio(0, titulos.length - 1)];
		autor = autores[genEnteroAleatorio(0, autores.length - 1)];
		editorial = editoriales[genEnteroAleatorio(0, editoriales.length - 1)];
		String[] congresos = { "A", "B", "C", "D", "E" };
		String congreso = congresos[genEnteroAleatorio(0, congresos.length - 1)];

		return new ActaCongreso(id, aPubli, aLleg, cantExist, 0, titulo, autor,
				editorial, congreso);

	}

	String[] nombres = { "Juan", "Ana", "Pedro", "Eduardo", "Carmen",
			"Edilberto" };

	String[] direcciones = { "Calle A", "Calle B", "Calle C", "Calle D",
			"Calle E", "Calle F", };

	/**
	 * Metodo para generar aleatoriamente una solicitud
	 * 
	 * @param materiles
	 *            Materiales de donde escoger
	 * @param asociados
	 *            Asociados a la bilioteca
	 * @return
	 * @throws Exception
	 */
	public Solicitud genSolicitud(ILista<Material> materiales,
			ILista<TarjetaAsociado> asociados) throws Exception {

		String nombre, CI, direccion;
		boolean asociado;
		Material material = materiales.Obtener(genEnteroAleatorio(0, materiales
				.Longitud() - 1));

		if (genEnteroAleatorio(1, 120) % 2 == 0) {
			nombre = nombres[genEnteroAleatorio(0, nombres.length - 1)];
			CI = genCarnetIdentidad();
			direccion = direcciones[genEnteroAleatorio(0,
					direcciones.length - 1)];
			asociado = false;
		} else {
			TarjetaAsociado tarjeta = asociados.Obtener(genEnteroAleatorio(0,
					asociados.Longitud() - 1));
			nombre = tarjeta.getNombre();
			CI = tarjeta.getCI();
			direccion = tarjeta.getDireccion();
			asociado = true;
		}

		return new Solicitud(nombre, CI, direccion, asociado, material);

	}

	private Material genMaterial() {
		Material material;

		int tipo = genEnteroAleatorio(0, 2);

		switch (tipo) {
		case 0:
			material = genLibro();
			break;

		case 1:
			material = genActa();
			break;

		default:
			material = genRevista();
			break;
		}
		return material;
	}

	public TarjetaAsociado genTarjetaAsociado() {

		String nombre, CI, direccion;
		nombre = nombres[genEnteroAleatorio(0, nombres.length - 1)];
		CI = genCarnetIdentidad();
		direccion = direcciones[genEnteroAleatorio(0, direcciones.length - 1)];

		return new TarjetaAsociado(nombre, CI, direccion);

	}

	public ILista<TarjetaAsociado> genListaTarjetas() {
		ILista<TarjetaAsociado> result = new ListaSE<TarjetaAsociado>();
		for (int i = 0; i < 8; i++) {
			result.Adicionar(genTarjetaAsociado());
		}
		return result;
	}

	public ILista<Solicitud> genListaSolicitudes(ILista<Material> materiales,
			ILista<TarjetaAsociado> asociados) throws Exception {
		ILista<Solicitud> result = new ListaSE<Solicitud>();
		for (int i = 0; i < 5; i++) {
			result.Adicionar(genSolicitud(materiales, asociados));
		}
		return result;
	}

	public ILista<Material> genListaMateriales() {
		ILista<Material> result = new ListaSE<Material>();
		for (int i = 0; i < 30; i++) {
			result.Adicionar(genMaterial());
		}
		return result;
	}

}
