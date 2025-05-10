package Services;

import Modelos.Libro;
import Modelos.Prestamo;
import Modelos.Usuario;

import java.util.List;

public interface PrestamoService {
    Prestamo save(Prestamo prestamo);
    Prestamo findById(Long id);
    List<Prestamo> findAll();
    Void delete(Long id);
    List<Prestamo> findByUsuario(Usuario usuario);
    List<Prestamo> findByLibro(Libro libro);
}
