package clases;

public class Revista extends Material {

    private TFrecuencia frecuenciaPubl;

    public Revista(int id, int annoPublicacion, int annoLlegada,
            int cantidadExistencia, int cantidadPrestamo, String titulo,
            String autor, String editorial, TFrecuencia frecuenciaPubl) {
        super(id, annoPublicacion, annoLlegada, cantidadExistencia,
                cantidadPrestamo, titulo, autor, editorial);
        this.frecuenciaPubl = frecuenciaPubl;
    }

    @Override
    public String toString() {
        return "< " + super.toString() + ", " + frecuenciaPubl + " >";
    }
}
