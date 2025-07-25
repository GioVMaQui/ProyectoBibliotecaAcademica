package consola;

import controlador.BibliotecaController;
import modelado.Libro;
import modelado.Prestamo;
import modelado.Usuario;

import java.util.List;
import java.util.Scanner;

public class AppBiblioteca {
    private final BibliotecaController controlador;
    private final Scanner scanner;

    public AppBiblioteca() {
        controlador = new BibliotecaController();
        scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");
            switch (opcion) {
                case 1 -> registrarUsuario();
                case 2 -> registrarLibro();
                case 3 -> realizarPrestamo();
                case 4 -> devolverLibro();
                case 5 -> mostrarUsuarios();
                case 6 -> mostrarLibros();
                case 7 -> mostrarPrestamos();
                case 8 -> eliminarUsuario();
                case 9 -> eliminarLibro();
                case 10 -> verificarDisponibilidad();
                case 0 -> salir();
                default -> System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 0);
    }

    private void mostrarMenu() {
        System.out.println("""
                \n=== BIBLIOTECA ACADÉMICA ===
                1. Registrar Usuario
                2. Registrar Libro
                3. Realizar Préstamo
                4. Devolver Libro
                5. Mostrar Usuarios
                6. Mostrar Libros
                7. Mostrar Préstamos
                8. Eliminar Usuario
                9. Eliminar Libro
                10. Verificar Disponibilidad
                0. Salir
                """);
    }

    private void registrarUsuario() {
        // Captura de los datos
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("ID: ");
        String id = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contrasenia = scanner.nextLine();


        controlador.registrarUsuario(nombre, id, email, contrasenia);
        System.out.println("Usuario registrado.");
    }


    private void registrarLibro() {
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("Codigo: ");
        String isbn = scanner.nextLine();

        controlador.registrarLibro(titulo, autor, isbn);
        System.out.println(" Libro registrado.");
    }

    private void realizarPrestamo() {
        System.out.print("ID Usuario: ");
        String idUsuario = scanner.nextLine();
        System.out.print("Codigo del Libro: ");
        String isbn = scanner.nextLine();

        String mensaje = controlador.realizarPrestamo(idUsuario, isbn);
        System.out.println(" " + mensaje);
    }

    private void devolverLibro() {
        System.out.print("ID Usuario: ");
        String idUsuario = scanner.nextLine();
        System.out.print("Codigo del Libro: ");
        String isbn = scanner.nextLine();

        String mensaje = controlador.devolverPrestamo(idUsuario, isbn);
        System.out.println(" " + mensaje);
    }

    private void mostrarUsuarios() {
        List<Usuario> usuarios = controlador.getUsuarios();
        if (usuarios.isEmpty()) {
            System.out.println(" No hay usuarios registrados");
        } else {
            System.out.println(" Usuarios:");
            usuarios.forEach(System.out::println);
        }
    }

    private void mostrarLibros() {
        List<Libro> libros = controlador.getLibros();
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
        } else {
            System.out.println(" Libros:");
            libros.forEach(System.out::println);
        }
    }

    private void mostrarPrestamos() {
        List<Prestamo> prestamos = controlador.getPrestamos();
        if (prestamos.isEmpty()) {
            System.out.println("No hay préstamos activos.");
        } else {
            System.out.println(" Préstamos:");
            prestamos.forEach(System.out::println);
        }
    }

    private void salir() {
        controlador.guardarDatos();
        System.out.println("Se guardo los datos");
    }

    private int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(" Ingrese un número válido.");
            }
        }
    }
    private void eliminarUsuario() {
        System.out.print("ID del usuario a eliminar: ");
        String id = scanner.nextLine();
        boolean eliminado = controlador.eliminarUsuario(id);
        System.out.println(eliminado ? " Usuario eliminado." : " Usuario no encontrado.");
    }

    private void eliminarLibro() {
        System.out.print("Codigo del libro a eliminar: ");
        String codigo = scanner.nextLine();
        boolean eliminado = controlador.eliminarLibro(codigo);
        System.out.println(eliminado ? " Libro eliminado." : " Libro no encontrado.");
    }
    private void verificarDisponibilidad() {
        System.out.print("Codigo del libro: ");
        String codigo = scanner.nextLine();
        boolean disponible = controlador.verificarDisponibilidad(codigo);
        System.out.println(disponible ? " El libro está disponible" : " El libro no está disponible o no existe");
    }

    public static void main(String[] args) {
        new AppBiblioteca().iniciar();
    }
}