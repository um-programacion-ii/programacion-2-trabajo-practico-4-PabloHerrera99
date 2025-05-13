package um.programacion2.biblioteca.controllers;

import org.springframework.http.HttpStatus;
import um.programacion2.biblioteca.exceptions.LibroNoEncontradoException;
import um.programacion2.biblioteca.modelos.Libro;
import um.programacion2.biblioteca.services.LibroService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/libros")
public class LibroController {
    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    /**
     * Obtiene la lista completa de libros registrados en el sistema.
     *
     * @return lista de libros
     */
    @GetMapping
    public List<Libro> obtenerTodos() {
        return libroService.findAll();
    }

    /**
     * Obtiene un libro específico según su ID.
     *
     * @param id ID del libro a buscar
     * @return el libro encontrado
     * @throws LibroNoEncontradoException si no se encuentra el libro con el ID dado
     */
    @GetMapping("/{id}")
    public Libro obtenerPorId(@PathVariable Long id) {
        return libroService.findById(id);
    }


    /**
     * Crea un nuevo libro.
     *
     * @param libro objeto libro a registrar
     * @return el libro creado con ID asignado
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Libro crear(@RequestBody Libro libro) {
        return libroService.save(libro);
    }

    /**
     * Actualiza los datos de un libro existente.
     *
     * @param id ID del libro a actualizar
     * @param libro datos actualizados del libro
     * @return el libro actualizado
     * @throws LibroNoEncontradoException si no se encuentra el libro con el ID dado
     */
    @PutMapping("/{id}")
    public Libro actualizar(@PathVariable Long id, @RequestBody Libro libro) {
        return libroService.update(id, libro);
    }

    /**
     * Elimina un libro por su ID.
     *
     * @param id ID del libro a eliminar
     * @throws LibroNoEncontradoException si no se encuentra el libro con el ID dado
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) //
    public void eliminar(@PathVariable Long id) {
        libroService.delete(id);
    }
}
