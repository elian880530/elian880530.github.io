package Clases;

public class principal {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Perro p = new Perro(4,"europa","blanco","32", "dalmata");
		Gato g = new Gato(4,"cuba","negro","34", "negro", "f", true);
		Perro pe = new Perro(3,"eeuu","verde","32", "dalmata");
		Gato ge = new Gato(2,"canada","azul","31", "negro", "f", false);
		
		Tienda t = new Tienda();
		try{
			t.ingresarMascotas(p);
			t.ingresarMascotas(g);
			t.ingresarMascotas(pe);
			t.ingresarMascotas(ge);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("dado un pais");
		System.out.println(t.cantUnPais("cuba"));
		System.out.println("precio dado");
		//System.out.println(t.Precio("31"));
		
		System.out.println("listado pais");
		t.OrdAlf();
		for(int i = 0; i < t.getCantReal(); i++)
			System.out.println(t.getMisMascotas()[i].paisProced + " -- " + t.getMisMascotas()[i].Code);
		
		System.out.println("mayor edad");
		
		Mascota[] m = t.Mayores();
		for(int i = 0; i < m.length; i++)
			System.out.println(m[i].cantMeses + " -- " + m[i].Code);
		
		
		
		System.out.println("Menos predominante");
		String[] a = t.colorMenosPredominante();
		
		for(int i = 0; i < a.length; i++)
			if(a[i] != null)
				System.out.println(a[i]);
		
		
		
	}

}
