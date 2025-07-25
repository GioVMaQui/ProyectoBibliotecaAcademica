package decorador;

public class LibroConResumen extends LibroDecorator {
    public LibroConResumen(ILibro libro) {
        super(libro);
    }

    @Override
    public String getDescripcion() {
        return libro.getDescripcion() + " + incluye resumen";
    }
}