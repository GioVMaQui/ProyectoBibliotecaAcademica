package InterfazGrafica;

//intento de crear un login

import controlador.BibliotecaController;
import modelado.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginUI {
    private JFrame frame;
    private JTextField txtIdUsuario;
    private JPasswordField txtContraseña;
    private BibliotecaController controlador;

    public LoginUI(BibliotecaController controlador) {
        this.controlador = controlador;

        frame = new JFrame("Login");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 2));

        frame.add(new JLabel("ID Usuario:"));
        txtIdUsuario = new JTextField();
        frame.add(txtIdUsuario);

        frame.add(new JLabel("Contraseña:"));
        txtContraseña = new JPasswordField();
        frame.add(txtContraseña);

        JButton btnLogin = new JButton("Login");
        frame.add(btnLogin);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = txtIdUsuario.getText();
                String contraseña = new String(txtContraseña.getPassword());

                Usuario usuario = controlador.login(id, contraseña);

                if (usuario != null) {
                    JOptionPane.showMessageDialog(frame, "Bienvenido " + usuario.getNombre() + "!");
                    frame.dispose();  // Cerrar la ventana de login
                    abrirVentanaBiblioteca();
                } else {
                    JOptionPane.showMessageDialog(frame, "ID o Contraseña incorrectos.");
                }
            }
        });

        frame.setVisible(true);
    }

    private void abrirVentanaBiblioteca() {
        new BibliotecaUI(controlador).setVisible(true);
    }
}
