package decorador;

public class LibroBasico implements ILibro {
    private String titulo;

    public LibroBasico(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String getDescripcion() {
        return "Libro: " + titulo;
    }
}