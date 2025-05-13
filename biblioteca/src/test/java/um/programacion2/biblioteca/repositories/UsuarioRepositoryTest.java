package um.programacion2.biblioteca.repositories;

import um.programacion2.biblioteca.enums.EstadoUsuario;
import um.programacion2.biblioteca.modelos.Usuario;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioRepositoryImplTest {

    private UsuarioRepositoryImpl usuarioRepository;

    @BeforeEach
    void setUp() {
        usuarioRepository = new UsuarioRepositoryImpl();
    }

    @Test
    void testSaveAndFindById() {
        Usuario usuario = new Usuario(null, "Juan Pérez", "juan@example.com", EstadoUsuario.ACTIVO);
        Usuario saved = usuarioRepository.save(usuario);

        assertNotNull(saved.getId());
        Optional<Usuario> result = usuarioRepository.findById(saved.getId());

        assertTrue(result.isPresent());
        assertEquals("Juan Pérez", result.get().getNombre());
    }

    @Test
    void testFindAll() {
        usuarioRepository.save(new Usuario(null, "Felipe", "felipe@example.com", EstadoUsuario.ACTIVO));
        usuarioRepository.save(new Usuario(null, "Ana", "ana@example.com", EstadoUsuario.ACTIVO));

        List<Usuario> usuarios = usuarioRepository.findAll();
        assertEquals(2, usuarios.size());
    }

    @Test
    void testDeleteById() {
        Usuario usuario = usuarioRepository.save(new Usuario(null, "Juan Pérez", "juan@example.com", EstadoUsuario.ACTIVO));
        Long id = usuario.getId();

        usuarioRepository.deleteById(id);
        assertFalse(usuarioRepository.findById(id).isPresent());
    }

    @Test
    void testExistsById() {
        Usuario usuario = usuarioRepository.save(new Usuario(null, "Felipe", "felipe@example.com", EstadoUsuario.ACTIVO));
        Long id = usuario.getId();

        assertTrue(usuarioRepository.existsById(id));
        usuarioRepository.deleteById(id);
        assertFalse(usuarioRepository.existsById(id));
    }
}
