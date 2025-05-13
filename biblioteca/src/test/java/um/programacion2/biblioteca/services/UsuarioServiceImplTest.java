package um.programacion2.biblioteca.services;

import um.programacion2.biblioteca.enums.EstadoUsuario;
import um.programacion2.biblioteca.exceptions.UsuarioNoEncontradoException;
import um.programacion2.biblioteca.modelos.Usuario;
import um.programacion2.biblioteca.repositories.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import um.programacion2.biblioteca.services.UsuarioServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UsuarioServiceImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @Test
    void cuandoFindByIdExiste_entoncesRetornaUsuario() {
        Long id = 1L;
        Usuario usuario = new Usuario(id, "Juan", "juan@mail.com", EstadoUsuario.ACTIVO);
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));

        Usuario resultado = usuarioService.findById(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        verify(usuarioRepository).findById(id);
    }

    @Test
    void cuandoFindByIdNoExiste_entoncesLanzaExcepcion() {
        Long id = 1L;
        when(usuarioRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(UsuarioNoEncontradoException.class, () -> usuarioService.findById(id));
    }

    @Test
    void cuandoFindAll_entoncesRetornaListaDeUsuarios() {
        List<Usuario> usuarios = List.of(
                new Usuario(1L, "Juan", "juan@mail.com", EstadoUsuario.ACTIVO),
                new Usuario(2L, "Ana", "ana@mail.com", EstadoUsuario.INACTIVO)
        );
        when(usuarioRepository.findAll()).thenReturn(usuarios);

        List<Usuario> resultado = usuarioService.findAll();

        assertEquals(2, resultado.size());
        verify(usuarioRepository).findAll();
    }

    @Test
    void cuandoSave_entoncesRetornaUsuarioGuardado() {
        Usuario usuario = new Usuario(null, "Nuevo", "nuevo@mail.com", EstadoUsuario.ACTIVO);
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario resultado = usuarioService.save(usuario);

        assertEquals("Nuevo", resultado.getNombre());
        verify(usuarioRepository).save(usuario);
    }

    @Test
    void cuandoDelete_entoncesRepositoryEsInvocado() {
        Long id = 1L;

        usuarioService.delete(id);

        verify(usuarioRepository).deleteById(id);
    }

    @Test
    void cuandoUpdateConIdExistente_entoncesRetornaUsuarioActualizado() {
        Long id = 1L;
        Usuario usuario = new Usuario(null, "Modificado", "mod@mail.com", EstadoUsuario.ACTIVO);

        when(usuarioRepository.existsById(id)).thenReturn(true);
        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(inv -> inv.getArgument(0));

        Usuario resultado = usuarioService.update(id, usuario);

        assertEquals(id, resultado.getId());
        assertEquals("Modificado", resultado.getNombre());
        verify(usuarioRepository).save(usuario);
    }

    @Test
    void cuandoUpdateConIdInexistente_entoncesLanzaExcepcion() {
        Long id = 1L;
        Usuario usuario = new Usuario(null, "Modificado", "mod@mail.com", EstadoUsuario.ACTIVO);
        when(usuarioRepository.existsById(id)).thenReturn(false);

        assertThrows(UsuarioNoEncontradoException.class, () -> usuarioService.update(id, usuario));
    }
}
