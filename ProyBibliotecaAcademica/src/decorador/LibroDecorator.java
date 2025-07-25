package decorador;

public abstract class LibroDecorator implements ILibro {
    protected ILibro libro;

    public LibroDecorator(ILibro libro) {
        this.libro = libro;
    }
}