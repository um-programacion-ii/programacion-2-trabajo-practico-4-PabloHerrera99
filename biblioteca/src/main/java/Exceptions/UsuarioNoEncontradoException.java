package Exceptions;

public class UsuarioNoEncontradoException extends RuntimeException {
    public UsuarioNoEncontradoException(Long id) {
        super("Usuario con ID " + id + " no fue encontrado.");
    }
}
