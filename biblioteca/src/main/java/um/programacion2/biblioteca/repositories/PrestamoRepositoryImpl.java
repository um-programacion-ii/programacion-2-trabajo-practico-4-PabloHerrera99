package um.programacion2.biblioteca.repositories;

import um.programacion2.biblioteca.modelos.Prestamo;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class PrestamoRepositoryImpl implements PrestamoRepository {

    private final Map<Long, Prestamo> prestamos = new HashMap<>();
    private Long nextId = 1L;

    @Override
    public Prestamo save(Prestamo prestamo) {
        if (prestamo.getId() == null) {
            prestamo.setId(nextId++);
        }
        prestamos.put(prestamo.getId(), prestamo);
        return prestamo;    }

    @Override
    public Optional<Prestamo> findById(Long id) {
        return prestamos.values().stream()
                .filter(prestamo -> prestamo.getId().equals(id))
                .findFirst();    }

    @Override
    public List<Prestamo> findAll() {
        return new ArrayList<>(prestamos.values());
    }

    @Override
    public void deleteById(Long id) {
        prestamos.remove(id);
    }

    @Override
    public List<Prestamo> findByUsuarioId(Long usuarioId) {
        return prestamos.values().stream()
                .filter(p -> p.getUsuario().getId().equals(usuarioId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Prestamo> findByLibroId(Long libroId) {
        return prestamos.values().stream()
                .filter(p -> p.getLibro().getId().equals(libroId))
                .collect(Collectors.toList());
    }
}
