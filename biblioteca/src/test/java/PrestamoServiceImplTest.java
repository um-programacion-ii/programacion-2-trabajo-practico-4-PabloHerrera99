import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

import Exceptions.PrestamoNoValidoException;
import Modelos.Libro;
import Modelos.Prestamo;
import Modelos.Usuario;

import Repositories.PrestamoRepository;
import Services.PrestamoServiceImpl;

import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PrestamoServiceImplTest {

    @Mock
    private PrestamoRepository prestamoRepository;

    @InjectMocks
    private PrestamoServiceImpl prestamoService;

    @Test
    void cuandoFindByIdExiste_entoncesRetornaPrestamo() {
        Long id = 1L;
        Prestamo prestamo = new Prestamo(id, new Libro(), new Usuario(), LocalDate.now(), LocalDate.now().plusDays(7));
        when(prestamoRepository.findById(id)).thenReturn(Optional.of(prestamo));

        Prestamo resultado = prestamoService.findById(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        verify(prestamoRepository).findById(id);
    }

    @Test
    void cuandoFindByIdNoExiste_entoncesLanzaExcepcion() {
        Long id = 1L;
        when(prestamoRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(PrestamoNoValidoException.class, () -> prestamoService.findById(id));
    }

    @Test
    void cuandoFindAll_entoncesRetornaListaDePrestamos() {
        List<Prestamo> prestamos = List.of(
                new Prestamo(1L, new Libro(), new Usuario(), LocalDate.now(), LocalDate.now().plusDays(7)),
                new Prestamo(2L, new Libro(), new Usuario(), LocalDate.now(), LocalDate.now().plusDays(14))
        );
        when(prestamoRepository.findAll()).thenReturn(prestamos);

        List<Prestamo> resultado = prestamoService.findAll();

        assertEquals(2, resultado.size());
        verify(prestamoRepository).findAll();
    }

    @Test
    void cuandoSave_entoncesRetornaPrestamoGuardado() {
        Prestamo prestamo = new Prestamo(null, new Libro(), new Usuario(), LocalDate.now(), LocalDate.now().plusDays(7));
        when(prestamoRepository.save(prestamo)).thenReturn(prestamo);

        Prestamo resultado = prestamoService.save(prestamo);

        assertNotNull(resultado);
        verify(prestamoRepository).save(prestamo);
    }

    @Test
    void cuandoDelete_entoncesRepositoryEsInvocado() {
        Long id = 1L;

        prestamoService.delete(id);

        verify(prestamoRepository).deleteById(id);
    }

    @Test
    void cuandoFindByUsuario_entoncesRetornaPrestamosDelUsuario() {
        Long usuarioId = 1L;
        List<Prestamo> prestamos = List.of(new Prestamo(), new Prestamo());
        when(prestamoRepository.findByUsuarioId(usuarioId)).thenReturn(prestamos);

        List<Prestamo> resultado = prestamoService.findByUsuario(usuarioId);

        assertEquals(2, resultado.size());
        verify(prestamoRepository).findByUsuarioId(usuarioId);
    }

    @Test
    void cuandoFindByLibro_entoncesRetornaPrestamosDelLibro() {
        Long libroId = 1L;
        List<Prestamo> prestamos = List.of(new Prestamo(), new Prestamo());
        when(prestamoRepository.findByLibroId(libroId)).thenReturn(prestamos);

        List<Prestamo> resultado = prestamoService.findByLibro(libroId);

        assertEquals(2, resultado.size());
        verify(prestamoRepository).findByLibroId(libroId);
    }
}
