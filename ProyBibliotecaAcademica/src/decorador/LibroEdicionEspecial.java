package decorador;

public class LibroEdicionEspecial extends LibroDecorator {
    public LibroEdicionEspecial(ILibro libro) {
        super(libro);
    }

    @Override
    public String getDescripcion() {
        return libro.getDescripcion() + " (Edición Especial)";
    }
}