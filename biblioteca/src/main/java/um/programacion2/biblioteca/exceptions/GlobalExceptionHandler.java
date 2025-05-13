package um.programacion2.biblioteca.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LibroNoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String manejarLibroNoEncontrado(LibroNoEncontradoException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(PrestamoNoValidoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String manejarPrestamoNoEncontrado(PrestamoNoValidoException ex) {
      return ex.getMessage();
    }

    @ExceptionHandler(UsuarioNoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String manejarUsuarioNoEncontrado(UsuarioNoEncontradoException ex) {
      return ex.getMessage();
    }
}