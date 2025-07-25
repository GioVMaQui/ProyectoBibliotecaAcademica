package modelado;

import java.util.ArrayList;
import java.util.List;

public class RepositorioUsuarios implements Repositorio<Usuario> {
    private List<Usuario> usuarios = new ArrayList<>();

    @Override
    public void guardar(Usuario usuario) {
        usuarios.add(usuario);
    }

    @Override
    public List<Usuario> listar() {
        return usuarios;
    }

    @Override
    public void modificar(Usuario usuario) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getIdUsuario().equals(usuario.getIdUsuario())) {
                usuarios.set(i, usuario);
                break;
            }
        }
    }
}