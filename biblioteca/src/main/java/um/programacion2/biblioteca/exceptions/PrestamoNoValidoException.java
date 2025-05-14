package um.programacion2.biblioteca.exceptions;

public class PrestamoNoValidoException extends RuntimeException {
    public PrestamoNoValidoException(Long id) {
        super("Prestamo con ID " + id + " no fue encontrado.");
    }
}
