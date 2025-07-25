package modelado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class BibliotecaUI {
    private JFrame frame;
    private JTextArea areaUsuarios, areaLibros, areaPrestamos;
    private JTextField txtNombreUsuario, txtIdUsuario, txtEmail, txtTituloLibro, txtAutorLibro, txtIsbnLibro;
    private JPasswordField txtContraseña; // Agregado para la contraseña

    public BibliotecaUI() {
        frame = new JFrame("Biblioteca Académica");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Panel de usuarios
        JPanel panelUsuarios = new JPanel(new GridLayout(5, 2)); // Cambié a 5 filas para la contraseña
        panelUsuarios.add(new JLabel("Nombre Usuario:"));
        txtNombreUsuario = new JTextField();
        panelUsuarios.add(txtNombreUsuario);

        panelUsuarios.add(new JLabel("ID Usuario:"));
        txtIdUsuario = new JTextField();
        panelUsuarios.add(txtIdUsuario);

        panelUsuarios.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        panelUsuarios.add(txtEmail);

        panelUsuarios.add(new JLabel("Contraseña:"));
        txtContraseña = new JPasswordField(15); // Usamos JPasswordField para la contraseña
        panelUsuarios.add(txtContraseña);

        JButton btnRegistrarUsuario = new JButton("Registrar Usuario");
        panelUsuarios.add(btnRegistrarUsuario);

        // Panel de libros
        JPanel panelLibros = new JPanel(new GridLayout(4, 2));
        panelLibros.add(new JLabel("Título Libro:"));
        txtTituloLibro = new JTextField();
        panelLibros.add(txtTituloLibro);

        panelLibros.add(new JLabel("Autor Libro:"));
        txtAutorLibro = new JTextField();
        panelLibros.add(txtAutorLibro);

        panelLibros.add(new JLabel("Código Libro:"));
        txtIsbnLibro = new JTextField();
        panelLibros.add(txtIsbnLibro);

        JButton btnRegistrarLibro = new JButton("Registrar Libro");
        panelLibros.add(btnRegistrarLibro);

        // Panel de prestamos
        JPanel panelPrestamos = new JPanel(new GridLayout(3, 2));
        panelPrestamos.add(new JLabel("ID Usuario (Préstamo):"));
        JTextField txtIdUsuarioPrestamo = new JTextField();
        panelPrestamos.add(txtIdUsuarioPrestamo);

        panelPrestamos.add(new JLabel("Código Libro (Préstamo):"));
        JTextField txtIsbnLibroPrestamo = new JTextField();
        panelPrestamos.add(txtIsbnLibroPrestamo);

        JButton btnRealizarPrestamo = new JButton("Realizar Préstamo");
        panelPrestamos.add(btnRealizarPrestamo);

        // Áreas de texto para mostrar
        areaUsuarios = new JTextArea(5, 20);
        areaLibros = new JTextArea(5, 20);
        areaPrestamos = new JTextArea(5, 20);

        // Agregar componentes a la ventana
        JPanel centralPanel = new JPanel(new GridLayout(1, 3)); // Para colocar las áreas de texto
        centralPanel.add(new JScrollPane(areaUsuarios));
        centralPanel.add(new JScrollPane(areaLibros));
        centralPanel.add(new JScrollPane(areaPrestamos));

        frame.add(panelUsuarios, BorderLayout.NORTH);
        frame.add(panelLibros, BorderLayout.CENTER);
        frame.add(panelPrestamos, BorderLayout.SOUTH);
        frame.add(centralPanel, BorderLayout.CENTER);

        // Acción de botones
        btnRegistrarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = txtNombreUsuario.getText();
                String id = txtIdUsuario.getText();
                String email = txtEmail.getText();
                String contraseña = new String(txtContraseña.getPassword()); // Captura la contraseña

                // Verifica si los campos no están vacíos
                if (nombre.isEmpty() || id.isEmpty() || email.isEmpty() || contraseña.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Todos los campos deben ser llenados.");
                } else {
                    Usuario usuario = new Usuario(nombre, id, email, contraseña);
                    Biblioteca.getInstancia().agregarUsuario(usuario);
                    areaUsuarios.append(usuario.toString() + "\n");
                }
            }
        });

        btnRegistrarLibro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titulo = txtTituloLibro.getText();
                String autor = txtAutorLibro.getText();
                String isbn = txtIsbnLibro.getText();
                if (titulo.isEmpty() || autor.isEmpty() || isbn.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Todos los campos deben ser llenados.");
                } else {
                    Libro libro = new Libro(titulo, autor, isbn);
                    Biblioteca.getInstancia().agregarLibro(libro);
                    areaLibros.append(libro.toString() + "\n");
                }
            }
        });

        btnRealizarPrestamo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idUsuario = txtIdUsuarioPrestamo.getText();
                String isbnLibro = txtIsbnLibroPrestamo.getText();
                if (idUsuario.isEmpty() || isbnLibro.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Debe completar todos los campos.");
                } else {
                    realizarPrestamo(idUsuario, isbnLibro);
                }
            }
        });

        frame.setVisible(true);
    }

    private void realizarPrestamo(String idUsuario, String codigoLibro) {
        Usuario usuario = Biblioteca.getInstancia().buscarUsuario(idUsuario);
        Libro libro = Biblioteca.getInstancia().buscarLibroPorCodigo(codigoLibro);

        // Validación para mostrar errores específicos
        if (usuario == null) {
            JOptionPane.showMessageDialog(frame, "Usuario no encontrado. Verifique el ID.");
            return;
        }

        if (libro == null) {
            JOptionPane.showMessageDialog(frame, "Libro no encontrado. Verifique el código.");
            return;
        }

        // Verificar si el libro está disponible para préstamo
        if (!libro.isDisponible()) {
            JOptionPane.showMessageDialog(frame, "El libro no está disponible para préstamo.");
            return;
        }

        // Realizar el préstamo si todo ok bb
        Date fechaPrestamo = new Date();
        Date fechaDevolucion = new Date(fechaPrestamo.getTime() + (7L * 24 * 60 * 60 * 1000));
        Biblioteca.getInstancia().realizarPrestamo(usuario, libro, fechaPrestamo, fechaDevolucion);
        areaPrestamos.append("Préstamo realizado a " + usuario.getNombre() + " para el libro " + libro.getTitulo() + "\n");
    }


}
