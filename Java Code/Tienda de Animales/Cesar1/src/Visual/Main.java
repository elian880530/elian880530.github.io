package Visual;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import Clases.Gato;
import Clases.Mascota;
import Clases.Perro;
import Clases.Tienda;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Tienda t = new Tienda();
		Mascota p = new Perro(10, "China", "Negro", "00AB", "Doberman");
		Mascota g = new Gato(12, "España", "Carmelita", "00B14", "Verdes", "M",
				true);
		try {
			t.ingresarMascotas(p);
			t.ingresarMascotas(g);
			t.Guardar();
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*Tienda miTienda = t.Cargar();
		System.out.println(((Perro) miTienda.obtenerMascota(0)).getPaisProced());
        System.out.print((Gato)miTienda.obtenerMascota(1));*/
	}

}
