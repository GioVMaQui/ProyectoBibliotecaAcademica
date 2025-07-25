package modelado;


import java.io.Serializable;
import java.util.Date;

public class Prestamo implements Serializable {
    private Usuario usuario;
    private Libro libro;
    private Date fechaPrestamo;
    private Date fechaDevolucion;

    public Prestamo(Usuario usuario, Libro libro, Date fechaPrestamo, Date fechaDevolucion) {
        this.usuario = usuario;
        this.libro = libro;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Libro getLibro() {
        return libro;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    @Override
    public String toString() {
        return "Préstamo de '" + libro.getTitulo() + "' a " + usuario.getNombre() +
                " desde " + fechaPrestamo + " hasta " + fechaDevolucion;
    }
}
