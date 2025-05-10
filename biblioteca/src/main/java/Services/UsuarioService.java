package Services;

import Modelos.Usuario;

import java.util.List;

public interface UsuarioService {
    Usuario findById(Long id);
    List<Usuario> findAll();
    Usuario save(Usuario usuario);
    void delete(Long id);
    Usuario update(Long id, Usuario usuario);
}
