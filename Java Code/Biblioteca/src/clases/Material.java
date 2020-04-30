package clases;

public class Material {

    protected int ID,  annoPublicacion,  annoLlegada,  cantidadExistencia,  cantidadPrestamo;

    public int getID() {
        return ID;
    }
    protected String titulo,  autor,  editorial;

    public Material(int id, int annoPublicacion, int annoLlegada,
            int cantidadExistencia, int cantidadPrestamo, String titulo,
            String autor, String editorial) {
        ID = id;
        this.annoPublicacion = annoPublicacion;
        this.annoLlegada = annoLlegada;
        this.cantidadExistencia = cantidadExistencia;
        this.cantidadPrestamo = cantidadPrestamo;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
    }

    @Override
    public String toString() {
        return ID + ", " + titulo + ", " + cantidadExistencia + ", " + cantidadPrestamo;
    }
}
