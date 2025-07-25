package modelado;

import java.util.ArrayList;
import java.util.List;

public class RepositorioLibros implements Repositorio<Libro> {
    private List<Libro> libros = new ArrayList<>();

    @Override
    public void guardar(Libro libro) {
        libros.add(libro);
    }

    @Override
    public List<Libro> listar() {
        return libros;
    }

    @Override
    public void modificar(Libro libro) {
        for (int i = 0; i < libros.size(); i++) {
            if (libros.get(i).getCodigo().equals(libro.getCodigo())) {
                libros.set(i, libro);
                break;
            }
        }
    }
}