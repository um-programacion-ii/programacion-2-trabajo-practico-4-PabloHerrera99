package um.programacion2.biblioteca.services;

import um.programacion2.biblioteca.modelos.Libro;

import java.util.List;

public interface LibroService {
    Libro save(Libro libro);
    Libro findByIsbn(String isbn);
    Libro findById(Long id);
    List<Libro> findAll();
    void delete(Long id);
    Libro update(Long id, Libro libro);
}
