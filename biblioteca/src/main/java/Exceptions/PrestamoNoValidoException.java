package Exceptions;

public class PrestamoNoValidoException extends RuntimeException {
    public PrestamoNoValidoException(Long id) {
        super("Prestamo con ID " + id + " no fue encontrado.");
    }
}
