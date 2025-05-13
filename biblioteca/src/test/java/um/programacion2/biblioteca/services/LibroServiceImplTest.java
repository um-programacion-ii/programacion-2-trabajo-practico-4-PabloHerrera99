package um.programacion2.biblioteca.services;

import um.programacion2.biblioteca.enums.EstadoLibro;
import um.programacion2.biblioteca.exceptions.LibroNoEncontradoException;
import um.programacion2.biblioteca.modelos.Libro;
import um.programacion2.biblioteca.repositories.LibroRepository;

import java.util.List;
import java.util.Optional;

import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import um.programacion2.biblioteca.services.LibroServiceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class LibroServiceImplTest {
    @Mock
    private LibroRepository libroRepository;

    @InjectMocks
    private LibroServiceImpl libroService;

    @Test
    void cuandoBuscarPorIsbnExiste_entoncesRetornaLibro() {
        // Arrange
        String isbn = "123-456-789";
        Libro libroEsperado = new Libro(1L, isbn, "Test Book", "Test Author", EstadoLibro.DISPONIBLE);
        when(libroRepository.findByIsbn(isbn)).thenReturn(Optional.of(libroEsperado));

        // Act
        Libro resultado = libroService.findByIsbn(isbn);

        // Assert
        assertNotNull(resultado);
        assertEquals(isbn, resultado.getIsbn());
        verify(libroRepository).findByIsbn(isbn);
    }

    @Test
    void cuandoBuscarPorIsbnNoExiste_entoncesLanzaExcepcion() {
        // Arrange
        String isbn = "123-456-789";
        when(libroRepository.findByIsbn(isbn)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(LibroNoEncontradoException.class, () ->
                libroService.findByIsbn(isbn)
        );
    }

    @Test
    void cuandoFindByIdExiste_entoncesRetornaLibro() {
        Long id = 1L;
        Libro libro = new Libro(id, "123", "Test", "Autor", EstadoLibro.DISPONIBLE);
        when(libroRepository.findById(id)).thenReturn(Optional.of(libro));

        Libro resultado = libroService.findById(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        verify(libroRepository).findById(id);
    }

    @Test
    void cuandoFindByIdNoExiste_entoncesLanzaExcepcion() {
        Long id = 1L;
        when(libroRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(LibroNoEncontradoException.class, () -> libroService.findById(id));
    }

    @Test
    void cuandoObtenerTodos_entoncesRetornaListaDeLibros() {
        List<Libro> libros = List.of(new Libro(), new Libro());
        when(libroRepository.findAll()).thenReturn(libros);

        List<Libro> resultado = libroService.findAll();

        assertEquals(2, resultado.size());
        verify(libroRepository).findAll();
    }

    @Test
    void cuandoGuardar_entoncesRetornaLibroGuardado() {
        Libro libro = new Libro(null, "123", "Nuevo", "Autor", EstadoLibro.DISPONIBLE);
        when(libroRepository.save(libro)).thenReturn(libro);

        Libro resultado = libroService.save(libro);

        assertEquals("123", resultado.getIsbn());
        verify(libroRepository).save(libro);
    }

    @Test
    void cuandoDelete_entoncesRepositoryEsInvocado() {
        Long id = 1L;

        libroService.delete(id);

        verify(libroRepository).deleteById(id);
    }

    @Test
    void cuandoUpdateConIdExistente_entoncesRetornaLibroActualizado() {
        Long id = 1L;
        Libro libro = new Libro(null, "123", "Actualizado", "Autor", EstadoLibro.DISPONIBLE);

        when(libroRepository.existsById(id)).thenReturn(true);
        when(libroRepository.save(any(Libro.class))).thenAnswer(inv -> inv.getArgument(0));

        Libro resultado = libroService.update(id, libro);

        assertEquals(id, resultado.getId());
        assertEquals("Actualizado", resultado.getTitulo());
        verify(libroRepository).save(libro);
    }

    @Test
    void cuandoUpdateConIdInexistente_entoncesLanzaExcepcion() {
        Long id = 1L;
        Libro libro = new Libro(null, "123", "Actualizado", "Autor", EstadoLibro.DISPONIBLE);
        when(libroRepository.existsById(id)).thenReturn(false);

        assertThrows(LibroNoEncontradoException.class, () -> libroService.update(id, libro));
    }
}