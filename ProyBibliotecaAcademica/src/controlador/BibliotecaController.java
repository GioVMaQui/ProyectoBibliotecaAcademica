package controlador;

import modelado.*;
import persistencia.ArchBiblioteca;
import observador.Notificador;
import observador.Observador;

import java.util.Date;
import java.util.List;

public class BibliotecaController {
    private Biblioteca biblioteca;
    private Notificador notificador;

    private final String RUTA_USUARIOS = "usuarios.ser";
    private final String RUTA_LIBROS = "libros.ser";
    private final String RUTA_PRESTAMOS = "prestamos.ser";

    public BibliotecaController() {
        biblioteca = Biblioteca.getInstancia();
        notificador = new Notificador();
        cargarDatos();
    }

    // Registrar nuevo usuario
    public void registrarUsuario(String nombre, String id, String email, String contrasenia) {
        Usuario nuevo = new Usuario(nombre, id, email,contrasenia);
        biblioteca.agregarUsuario(nuevo);
        notificador.agregarObservador((Observador) mensaje ->
                System.out.println("Notificación para " + nombre + ": " + mensaje));
    }

    // Registrar nuevo libro
    public void registrarLibro(String titulo, String autor, String codigo) {
        Libro nuevo = new Libro(titulo, autor, codigo);
        biblioteca.agregarLibro(nuevo);
    }

    // Realizar un préstamo
    public String realizarPrestamo(String idUsuario, String isbn) {
        Usuario usuario = buscarUsuario(idUsuario);
        Libro libro = buscarLibro(isbn);

        if (usuario == null) return "Usuario no encontrado";
        if (libro == null) return "Libro no encontrado.";
        if (!libro.isDisponible()) return "El libro no está disponible.";

        Date hoy = new Date();
        Date devolucion = new Date(hoy.getTime() + 7L * 24 * 60 * 60 * 1000); // 7 días

        biblioteca.realizarPrestamo(usuario, libro, hoy, devolucion);
        return "Préstamo realizado con éxito";
    }

    public String devolverPrestamo(String idUsuario, String codigo) {
        Prestamo prestamoADevolver = null;

        for (Prestamo p : biblioteca.getPrestamos()) {
            if (p.getUsuario().getIdUsuario().equals(idUsuario)
                    && p.getLibro().getCodigo().equals(codigo)) {
                prestamoADevolver = p;
                break;
            }
        }

        if (prestamoADevolver == null) return "No se encontro el prestamo";

        biblioteca.devolverLibro(prestamoADevolver);
        notificador.notificar("El libro '" + codigo + "' ha sido devuelto.");
        return "Libro devuelto correctamente.";
    }

    // Buscar usuario
    private Usuario buscarUsuario(String id) {
        for (Usuario u : biblioteca.getUsuarios()) {
            if (u.getIdUsuario().equals(id)) return u;
        }
        return null;
    }

    // Buscar libro
    private Libro buscarLibro(String codigo) {
        for (Libro l : biblioteca.getLibros()) {
            if (l.getCodigo().equals(codigo)) return l;
        }
        return null;
    }
    public boolean eliminarUsuario(String id) {
        return biblioteca.getUsuarios().removeIf(u -> u.getIdUsuario().equals(id));
    }

    public boolean eliminarLibro(String codigo) {
        return biblioteca.getLibros().removeIf(l -> l.getCodigo().equals(codigo));
    }


    // listado
    public List<Usuario> getUsuarios() {
        return biblioteca.getUsuarios();
    }

    public List<Libro> getLibros() {
        return biblioteca.getLibros();
    }

    public List<Prestamo> getPrestamos() {
        return biblioteca.getPrestamos();
    }
    public boolean verificarDisponibilidad(String codigo) {
        Libro libro = biblioteca.buscarLibroPorCodigo(codigo);
        return libro != null && libro.isDisponible();
    }
    public Usuario login(String id, String contraseña) {
        for (Usuario u : biblioteca.getUsuarios()) {
            if (u.getIdUsuario().equals(id) && u.getContrasenia().equals(contraseña)) {
                return u;
            }
        }
        return null;
    }

    // Guardar datos
    public void guardarDatos() {
        ArchBiblioteca.guardarLista(getUsuarios(), RUTA_USUARIOS);
        ArchBiblioteca.guardarLista(getLibros(), RUTA_LIBROS);
        ArchBiblioteca.guardarLista(getPrestamos(), RUTA_PRESTAMOS);
    }

    // Cargar datos
    @SuppressWarnings("unchecked")
    public void cargarDatos() {
        List<Usuario> usuarios = ArchBiblioteca.cargarLista(RUTA_USUARIOS);
        List<Libro> libros = ArchBiblioteca.cargarLista(RUTA_LIBROS);
        List<Prestamo> prestamos = ArchBiblioteca.cargarLista(RUTA_PRESTAMOS);

        if (usuarios != null) biblioteca.getUsuarios().addAll(usuarios);
        if (libros != null) biblioteca.getLibros().addAll(libros);
        if (prestamos != null) biblioteca.getPrestamos().addAll(prestamos);
    }
}