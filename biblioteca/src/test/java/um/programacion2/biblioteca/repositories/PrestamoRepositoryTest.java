package um.programacion2.biblioteca.repositories;

import um.programacion2.biblioteca.enums.*;
import um.programacion2.biblioteca.modelos.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PrestamoRepositoryTest {

    private PrestamoRepositoryImpl prestamoRepository;

    private Usuario usuario;
    private Libro libro;

    @BeforeEach
    void setUp() {
        prestamoRepository = new PrestamoRepositoryImpl();

        usuario = new Usuario(1L, "Juan Pérez", "juan@example.com", EstadoUsuario.ACTIVO);
        libro = new Libro(1L, "123-456", "Mistborn", "Brandon Sanderson", EstadoLibro.DISPONIBLE);
    }

    @Test
    void testSaveAndFindById() {
        Prestamo prestamo = new Prestamo(null, libro, usuario, LocalDate.now(), LocalDate.now().plusDays(14));
        Prestamo saved = prestamoRepository.save(prestamo);

        assertNotNull(saved.getId());
        Optional<Prestamo> result = prestamoRepository.findById(saved.getId());

        assertTrue(result.isPresent());
        assertEquals(libro.getTitulo(), result.get().getLibro().getTitulo());
    }

    @Test
    void testFindAll() {
        prestamoRepository.save(new Prestamo(null, libro, usuario, LocalDate.now(), LocalDate.now().plusDays(7)));
        prestamoRepository.save(new Prestamo(null, libro, usuario, LocalDate.now(), LocalDate.now().plusDays(10)));

        List<Prestamo> all = prestamoRepository.findAll();

        assertEquals(2, all.size());
    }

    @Test
    void testDeleteById() {
        Prestamo prestamo = prestamoRepository.save(new Prestamo(null, libro, usuario, LocalDate.now(), LocalDate.now().plusDays(7)));
        Long id = prestamo.getId();

        prestamoRepository.deleteById(id);

        assertFalse(prestamoRepository.findById(id).isPresent());
    }

    @Test
    void testFindByUsuarioId() {
        prestamoRepository.save(new Prestamo(null, libro, usuario, LocalDate.now(), LocalDate.now().plusDays(7)));
        prestamoRepository.save(new Prestamo(null, libro, usuario, LocalDate.now(), LocalDate.now().plusDays(10)));

        List<Prestamo> prestamosUsuario = prestamoRepository.findByUsuarioId(usuario.getId());

        assertEquals(2, prestamosUsuario.size());
    }

    @Test
    void testFindByLibroId() {
        prestamoRepository.save(new Prestamo(null, libro, usuario, LocalDate.now(), LocalDate.now().plusDays(7)));

        List<Prestamo> prestamosLibro = prestamoRepository.findByLibroId(libro.getId());

        assertEquals(1, prestamosLibro.size());
        assertEquals(libro.getId(), prestamosLibro.get(0).getLibro().getId());
    }
}
