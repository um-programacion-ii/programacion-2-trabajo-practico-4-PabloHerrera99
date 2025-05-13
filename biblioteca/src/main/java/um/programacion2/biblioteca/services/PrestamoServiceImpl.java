package um.programacion2.biblioteca.services;

import um.programacion2.biblioteca.exceptions.PrestamoNoValidoException;
import um.programacion2.biblioteca.modelos.Prestamo;
import um.programacion2.biblioteca.repositories.LibroRepository;
import um.programacion2.biblioteca.repositories.PrestamoRepository;
import um.programacion2.biblioteca.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrestamoServiceImpl implements PrestamoService{
    private final PrestamoRepository prestamoRepository;

    public PrestamoServiceImpl(PrestamoRepository prestamoRepository, LibroRepository libroRepository, UsuarioRepository usuarioRepository) {
        this.prestamoRepository = prestamoRepository;
    }

    @Override
    public Prestamo save(Prestamo prestamo) {
        return prestamoRepository.save(prestamo);
    }

    @Override
    public Prestamo findById(Long id) {
        return prestamoRepository.findById(id)
                .orElseThrow(() -> new PrestamoNoValidoException(id));
    }

    @Override
    public List<Prestamo> findAll() {
        return prestamoRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        prestamoRepository.deleteById(id);
    }

    @Override
    public List<Prestamo> findByUsuario(Long usuarioId) {
        return prestamoRepository.findByUsuarioId(usuarioId);
    }

    @Override
    public List<Prestamo> findByLibro(Long libroId) {
        return prestamoRepository.findByLibroId(libroId);
    }
}
