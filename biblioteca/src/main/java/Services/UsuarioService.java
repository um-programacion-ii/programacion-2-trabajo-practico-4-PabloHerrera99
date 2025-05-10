package Services;

import Modelos.Usuario;

import java.util.List;

public interface UsuarioService {
    Usuario save(Usuario usuario);
    Usuario findById(Long id);
    List<Usuario> findAll();
    void delete(Long id);
    Usuario update(Long id, Usuario usuario);
}
