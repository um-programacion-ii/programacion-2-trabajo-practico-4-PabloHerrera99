package um.programacion2.biblioteca.services;

import um.programacion2.biblioteca.modelos.Prestamo;

import java.util.List;

public interface PrestamoService {
    Prestamo save(Prestamo prestamo);
    Prestamo findById(Long id);
    List<Prestamo> findAll();
    void delete(Long id);
    List<Prestamo> findByUsuario(Long usuarioid);
    List<Prestamo> findByLibro(Long libroid);
}
