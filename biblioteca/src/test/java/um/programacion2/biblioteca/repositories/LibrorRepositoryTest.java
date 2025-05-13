package um.programacion2.biblioteca.repositories;

import um.programacion2.biblioteca.modelos.Libro;
import um.programacion2.biblioteca.enums.EstadoLibro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class LibroRepositoryTest {

    private LibroRepositoryImpl libroRepository;

    @BeforeEach
    void setUp() {
        libroRepository = new LibroRepositoryImpl();
    }

    @Test
    void testSaveAndFindById() {
        Libro libro = new Libro(null, "123456789", "El Camino de los reyes", "Brandon Sanderson", EstadoLibro.DISPONIBLE);
        Libro saved = libroRepository.save(libro);

        assertNotNull(saved.getId());
        Optional<Libro> result = libroRepository.findById(saved.getId());

        assertTrue(result.isPresent());
        assertEquals("El Camino de los reyes", result.get().getTitulo());
    }

    @Test
    void testFindByIsbn() {
        Libro libro = new Libro(null, "ABC-123", "Mistborn", "Brandon Sanderson", EstadoLibro.DISPONIBLE);
        libroRepository.save(libro);

        Optional<Libro> result = libroRepository.findByIsbn("ABC-123");

        assertTrue(result.isPresent());
        assertEquals("Mistborn", result.get().getTitulo());
    }

    @Test
    void testFindAll() {
        libroRepository.save(new Libro(null, "ISBN-1", "Libro 1", "Autor 1", EstadoLibro.DISPONIBLE));
        libroRepository.save(new Libro(null, "ISBN-2", "Libro 2",  "Autor 2", EstadoLibro.DISPONIBLE));

        List<Libro> all = libroRepository.findAll();

        assertEquals(2, all.size());
    }

    @Test
    void testDeleteById() {
        Libro libro = libroRepository.save(new Libro(null, "ISBN-DEL", "Para Eliminar",  "Autor 1", EstadoLibro.DISPONIBLE));
        Long id = libro.getId();

        libroRepository.deleteById(id);

        assertFalse(libroRepository.findById(id).isPresent());
    }

    @Test
    void testExistsById() {
        Libro libro = libroRepository.save(new Libro(null, "ISBN-EXIST", "Existente",  "Autor 1", EstadoLibro.DISPONIBLE));
        Long id = libro.getId();

        assertTrue(libroRepository.existsById(id));
        libroRepository.deleteById(id);
        assertFalse(libroRepository.existsById(id));
    }
}
