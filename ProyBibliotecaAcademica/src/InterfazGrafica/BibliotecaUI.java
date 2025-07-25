package InterfazGrafica;
import java.util.Scanner;
import controlador.BibliotecaController;
import javax.swing.*;
import java.awt.*;

public class BibliotecaUI extends JFrame {
Scanner sc = new Scanner(System.in);
    private final BibliotecaController controlador;

    public BibliotecaUI() {
        controlador = new BibliotecaController();
        configurarVentana();
        crearInterfaz();
    }
    public BibliotecaUI(BibliotecaController controlador) {
        this.controlador = controlador;
        initComponents(); // Aquí llamas al método que crea la interfaz
    }
    private void initComponents() {
        setTitle("Sistema de Biblioteca");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));


        JButton btnMostrarLibros = new JButton("Mostrar Libros");
        add(btnMostrarLibros);

    }

    private void configurarVentana() {
        setTitle(" Biblioteca Académica");
        setSize(750, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void crearInterfaz() {
        JTabbedPane pestañas = new JTabbedPane();

        pestañas.addTab("Usuarios", crearPanelUsuarios());
        pestañas.addTab("Libros", crearPanelLibros());
        pestañas.addTab("Préstamos", crearPanelPrestamos());
        pestañas.addTab("Sistema", crearPanelSistema());

        add(pestañas, BorderLayout.CENTER);
    }

    private JPanel crearPanelUsuarios() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        panel.add(crearBoton(" Registrar Usuario", e -> registrarUsuario()));
        panel.add(crearBoton(" Mostrar Usuarios", e -> mostrarUsuarios()));
        panel.add(crearBoton(" Eliminar Usuario", e -> eliminarUsuario()));

        return panel;
    }

    private JPanel crearPanelLibros() {
        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        panel.add(crearBoton(" Registrar Libro", e -> registrarLibro()));
        panel.add(crearBoton(" Mostrar Libros", e -> mostrarLibros()));
        panel.add(crearBoton(" Eliminar Libro", e -> eliminarLibro()));
        panel.add(crearBoton(" Verificar Disponibilidad", e -> verificarDisponibilidad()));

        return panel;
    }

    private JPanel crearPanelPrestamos() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        panel.add(crearBoton(" Realizar Préstamo", e -> realizarPrestamo()));
        panel.add(crearBoton(" Devolver Libro", e -> devolverLibro()));
        panel.add(crearBoton(" Mostrar Préstamos", e -> mostrarPrestamos()));

        return panel;
    }

    private JPanel crearPanelSistema() {
        JPanel panel = new JPanel(new GridLayout(1, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        panel.add(crearBoton(" Guardar y Salir", e -> salir()));
        return panel;
    }

    private JButton crearBoton(String texto, java.awt.event.ActionListener accion) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        boton.setFocusPainted(false);
        boton.setBackground(new Color(60, 120, 200));
        boton.setForeground(Color.WHITE);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.addActionListener(accion);
        return boton;
    }


    private void registrarUsuario() {
        String nombre = JOptionPane.showInputDialog(this, "Nombre:");
        String id = JOptionPane.showInputDialog(this, "ID:");
        String email = JOptionPane.showInputDialog(this, "Email:");

        if (nombre != null && id != null && email != null) {
            String contraseña = JOptionPane.showInputDialog(this, "Contraseña:");

            controlador.registrarUsuario(nombre, id, email, contraseña);
            JOptionPane.showMessageDialog(this, "Usuario registrado.");
        } else {
            JOptionPane.showMessageDialog(this, "Los datos de usuario no son válidos.");
        }
    }


    private void registrarLibro() {
        String titulo = JOptionPane.showInputDialog(this, "Título:");
        String autor = JOptionPane.showInputDialog(this, "Autor:");
        String isbn = JOptionPane.showInputDialog(this, "Código del libro:");
        if (titulo != null && autor != null && isbn != null) {
            controlador.registrarLibro(titulo, autor, isbn);
            JOptionPane.showMessageDialog(this, " Libro registrado.");
        }
    }

    private void realizarPrestamo() {
        String id = JOptionPane.showInputDialog(this, "ID Usuario:");
        String isbn = JOptionPane.showInputDialog(this, "Código del libro:");
        String resultado = controlador.realizarPrestamo(id, isbn);
        JOptionPane.showMessageDialog(this, resultado);
    }

    private void devolverLibro() {
        String id = JOptionPane.showInputDialog(this, "ID Usuario:");
        String isbn = JOptionPane.showInputDialog(this, "Código del libro:");
        String resultado = controlador.devolverPrestamo(id, isbn);
        JOptionPane.showMessageDialog(this, resultado);
    }

    private void mostrarUsuarios() {
        StringBuilder sb = new StringBuilder();
        controlador.getUsuarios().forEach(u -> sb.append(u).append("\n"));
        JOptionPane.showMessageDialog(this, sb.length() == 0 ? "No hay usuarios registrados." : sb.toString());
    }

    private void mostrarLibros() {
        StringBuilder sb = new StringBuilder();
        controlador.getLibros().forEach(l -> sb.append(l).append("\n"));
        JOptionPane.showMessageDialog(this, sb.length() == 0 ? "No hay libros registrados." : sb.toString());
    }

    private void mostrarPrestamos() {
        StringBuilder sb = new StringBuilder();
        controlador.getPrestamos().forEach(p -> sb.append(p).append("\n"));
        JOptionPane.showMessageDialog(this, sb.length() == 0 ? "No hay préstamos activos." : sb.toString());
    }

    private void eliminarUsuario() {
        String id = JOptionPane.showInputDialog(this, "ID del usuario:");
        boolean eliminado = controlador.eliminarUsuario(id);
        JOptionPane.showMessageDialog(this, eliminado ? " Usuario eliminado." : " No encontrado.");
    }

    private void eliminarLibro() {
        String isbn = JOptionPane.showInputDialog(this, "Código del libro:");
        boolean eliminado = controlador.eliminarLibro(isbn);
        JOptionPane.showMessageDialog(this, eliminado ? " Libro eliminado." : " No encontrado.");
    }

    private void verificarDisponibilidad() {
        String isbn = JOptionPane.showInputDialog(this, "Código del libro:");
        boolean disponible = controlador.verificarDisponibilidad(isbn);
        JOptionPane.showMessageDialog(this, disponible ? " Disponible" : " No disponible");
    }

    private void salir() {
        controlador.guardarDatos();
        JOptionPane.showMessageDialog(this, "Datos guardados. Saliendo...");
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BibliotecaUI().setVisible(true));
    }
}
