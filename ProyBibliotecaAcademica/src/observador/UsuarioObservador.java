package observador;

public class UsuarioObservador implements Observador {
    private String nombre;

    public UsuarioObservador(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void actualizar(String mensaje) {
        System.out.println("Notificación para " + nombre + ": " + mensaje);
    }
}