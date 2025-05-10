package Services;

import Modelos.Libro;
import Modelos.Prestamo;
import Modelos.Usuario;

import java.util.List;

public interface PrestamoService {
    Prestamo save(Prestamo prestamo);
    Prestamo findById(Long id);
    List<Prestamo> findAll();
    void delete(Long id);
    List<Prestamo> findByUsuario(Long usuarioid);
    List<Prestamo> findByLibro(Long libroid);
}
