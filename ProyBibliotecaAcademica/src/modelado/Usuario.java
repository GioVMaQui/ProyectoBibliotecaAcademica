package modelado;

import java.io.Serializable;
import java.util.List;

public class Usuario implements Serializable {
    private String nombre;
    private String idUsuario;
    private String email;
    private String contrasenia;
    private boolean esAdmin;

    public Usuario(String nombre, String idUsuario, String email, String contrasenia) {
        this.nombre = nombre;
        this.idUsuario = idUsuario;
        this.email = email;
        this.contrasenia = contrasenia;
    }

    public String getNombre() {
        return nombre;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void mostrarPrestamos(List<Prestamo> prestamos) {
        for (Prestamo prestamo : prestamos) {
            if (prestamo.getUsuario().getIdUsuario().equals(this.idUsuario)) {
                System.out.println(prestamo);
            }
        }
    }
    @Override
    public String toString() {
        return nombre + " (" + idUsuario + ") - " + email;
    }



    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}
