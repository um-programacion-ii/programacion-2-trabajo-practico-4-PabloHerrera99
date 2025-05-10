package Services;

import Modelos.Libro;

import java.util.List;

public interface LibroService {
    Libro save(Libro libro);
    Libro findByIsbn(String isbn);
    List<Libro> findAll();
    void delete(Long id);
    Libro update(Long id, Libro libro);
}
