package Repositories;

import Modelos.Usuario;

import java.util.List;
import java.util.Optional;


public interface UsuarioRepository {
    Usuario save(Usuario usuario);
    Optional<Usuario> findById(Long id);
    List<Usuario> findAll();
    void deleteById(Long id);
}
