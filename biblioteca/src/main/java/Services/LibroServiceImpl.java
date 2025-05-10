package Services;

import Exceptions.LibroNoEncontradoException;
import Modelos.Libro;
import Repositories.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroServiceImpl implements LibroService {
    private final LibroRepository libroRepository;

    public LibroServiceImpl(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @Override
    public Libro save(Libro libro) {
        return libroRepository.save(libro);
    }

    @Override
    public Libro findByIsbn(String isbn) {
        return libroRepository.findByIsbn(isbn)
                .orElseThrow(() -> new LibroNoEncontradoException(isbn));
    }

    @Override
    public List<Libro> findAll() {
        return libroRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        libroRepository.deleteById(id);
    }

    @Override
    public Libro update(Long id, Libro libro) {
        if (!libroRepository.existsById(id)) {
            throw new LibroNoEncontradoException(id);
        }
        libro.setId(id);
        return libroRepository.save(libro);
    }

}
