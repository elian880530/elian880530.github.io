package clases;

public class Libro extends Material {

    private TGenero genero;

    public Libro(int id, int annoPublicacion, int annoLlegada,
            int cantidadExistencia, int cantidadPrestamo, String titulo,
            String autor, String editorial, TGenero genero) {
        super(id, annoPublicacion, annoLlegada, cantidadExistencia,
                cantidadPrestamo, titulo, autor, editorial);
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "< " + super.toString() + ", " + genero + " >";
    }
}
