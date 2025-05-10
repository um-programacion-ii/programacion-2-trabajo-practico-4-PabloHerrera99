package Repositories;

import Modelos.Usuario;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepository {

    private Map<Long, Usuario> usuarios = new HashMap<>();
    private Long nextId = 1L;

    @Override
    public Usuario save(Usuario usuario) {
        if (usuario.getId() == null) {
            usuario.setId(nextId++);
        }
        usuarios.put(usuario.getId(), usuario);
        return usuario;
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return usuarios.values().stream()
                .filter(usuario -> usuario.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Usuario> findAll() {
        return new ArrayList<>(usuarios.values());
    }

    @Override
    public void deleteById(Long id) {
        usuarios.remove(id);
    }
}
