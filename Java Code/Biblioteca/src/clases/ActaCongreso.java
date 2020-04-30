package clases;

public class ActaCongreso extends Material {

    private String nombreCongreso;

    public ActaCongreso(int id, int annoPublicacion, int annoLlegada,
            int cantidadExistencia, int cantidadPrestamo, String titulo,
            String autor, String editorial, String nombreCongreso) {
        super(id, annoPublicacion, annoLlegada, cantidadExistencia,
                cantidadPrestamo, titulo, autor, editorial);
        this.nombreCongreso = nombreCongreso;
    }

    @Override
    public String toString() {
        return "< " + super.toString() + ", " + nombreCongreso + " >";
    }
}
