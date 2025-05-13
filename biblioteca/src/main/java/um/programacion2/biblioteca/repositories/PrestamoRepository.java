package um.programacion2.biblioteca.repositories;

import um.programacion2.biblioteca.modelos.Prestamo;

import java.util.List;
import java.util.Optional;

public interface PrestamoRepository {
    Prestamo save(Prestamo prestamo);
    Optional<Prestamo> findById(Long id);
    List<Prestamo> findAll();
    void deleteById(Long id);
    List<Prestamo> findByUsuarioId(Long usuarioId);
    List<Prestamo> findByLibroId(Long libroId);
}
