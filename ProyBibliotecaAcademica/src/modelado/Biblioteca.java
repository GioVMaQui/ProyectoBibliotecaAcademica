package modelado;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Biblioteca {
    private static Biblioteca instancia;
    private List<Usuario> usuarios;
    private List<Libro> libros;
    private List<Prestamo> prestamos;

    private Biblioteca() {
        usuarios = new ArrayList<>();
        libros = new ArrayList<>();
        prestamos = new ArrayList<>();
    }

    public static Biblioteca getInstancia() {
        if (instancia == null) {
            instancia = new Biblioteca();
        }
        return instancia;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void agregarLibro(Libro libro) {
        libros.add(libro);
    }

    public void realizarPrestamo(Usuario usuario, Libro libro, Date fechaPrestamo, Date fechaDevolucion) {
        if (libro.isDisponible()) {
            libro.setDisponible(false);
            prestamos.add(new Prestamo(usuario, libro, fechaPrestamo, fechaDevolucion));
        } else {
            System.out.println("El libro no está disponible para préstamo.");
        }
    }

    public void devolverLibro(Prestamo prestamo) {
        prestamo.getLibro().setDisponible(true);
        prestamos.remove(prestamo);
    }
    public Libro buscarLibroPorCodigo(String codigo) {
        for (Libro libro : libros) {
            if (libro.getCodigo().equals(codigo)) {
                return libro;
            }
        }
        return null;
    }
    public Usuario buscarUsuario(String idUsuario) {
        for (Usuario usuario : usuarios) {
            if (usuario.getIdUsuario().equals(idUsuario)) {
                return usuario;
            }
        }
        return null;
    }
}
