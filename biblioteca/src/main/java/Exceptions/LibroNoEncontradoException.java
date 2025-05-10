package Exceptions;

public class LibroNoEncontradoException extends RuntimeException {

    public LibroNoEncontradoException(Long id) {
        super("Libro con ID " + id + " no fue encontrado.");
    }

    public LibroNoEncontradoException(String isbn) {
        super("Libro con ISBN " + isbn + " no fue encontrado.");
    }
}