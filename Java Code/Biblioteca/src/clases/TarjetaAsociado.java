package clases;

public class TarjetaAsociado {

    public TarjetaAsociado(String nombre, String ci, String direccion) {
        this.nombre = nombre;
        CI = ci;
        this.direccion = direccion;
    }
    private String nombre,  CI,  direccion;

    public String getNombre() {
        return nombre;
    }

    public String getCI() {
        return CI;
    }

    public String getDireccion() {
        return direccion;
    }

    @Override
    public String toString() {
        return "< " + nombre + ", " + CI + " >";
    }
}
