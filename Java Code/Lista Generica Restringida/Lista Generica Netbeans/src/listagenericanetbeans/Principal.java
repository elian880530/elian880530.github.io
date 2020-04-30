package listagenericanetbeans;

public class Principal {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		try {
			Lista<Persona> MisPersonas = new Lista<Persona>();
			MisPersonas.Adicionar(new Persona(14332,"Amary Jackson"));
			MisPersonas.Adicionar(new Persona(14444,"Yosveni"));
			MisPersonas.Adicionar(new Persona(1,"Yo"));
			
			MisPersonas.OrdenarAscendente();
			
			for (int i = 0; i < MisPersonas.CantidadElementos(); i++) {
				System.out.println(MisPersonas.Obtener(i).getNombre() +":" + MisPersonas.Obtener(i).getSolapin());
			}	
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	
		
		
		
		
		

	}

}
