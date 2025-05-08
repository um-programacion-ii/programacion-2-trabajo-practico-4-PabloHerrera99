package Repositories;

import Modelos.Prestamo;

import java.util.List;
import java.util.Optional;

public interface PrestamoRepository {
    void save(Prestamo prestamo);
    Optional<Prestamo> findById(Long id);
    List<Prestamo> findAll();
    void deleteById(Long id);
    List<Prestamo> findByUsuarioId(Long usuarioId);
    List<Prestamo> findByLibroId(Long libroId);
}
