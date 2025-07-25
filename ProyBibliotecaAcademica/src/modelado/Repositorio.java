package modelado;

import java.util.List;

public interface Repositorio<T> {
    void guardar(T item);
    List<T> listar();
    void modificar(T item);
}
