package um.programacion2.biblioteca.controllers;

import um.programacion2.biblioteca.services.PrestamoService;
import um.programacion2.biblioteca.modelos.Prestamo;
import um.programacion2.biblioteca.exceptions.PrestamoNoValidoException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {
    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    /**
     * Obtiene la lista completa de préstamos registrados.
     *
     * @return lista de préstamos
     */
    @GetMapping
    public List<Prestamo> obtenerTodos() {
        return prestamoService.findAll();
    }

    /**
     * Obtiene un préstamo específico por su ID.
     *
     * @param id ID del préstamo
     * @return el préstamo encontrado
     * @throws PrestamoNoValidoException si no se encuentra el préstamo
     */
    @GetMapping("/{id}")
    public Prestamo obtenerPorId(@PathVariable Long id) {
        return prestamoService.findById(id);
    }


    /**
     * Registra un nuevo préstamo.
     *
     * @param prestamo datos del préstamo a registrar
     * @return el préstamo creado con ID asignado
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Prestamo registrar(@RequestBody Prestamo prestamo) {
        return prestamoService.save(prestamo);
    }

    /**
     * Elimina un préstamo existente por su ID.
     *
     * @param id ID del préstamo a eliminar
     * @throws PrestamoNoEncontradoException si no se encuentra el préstamo
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        prestamoService.delete(id);
    }

    /**
     * Obtiene todos los préstamos asociados a un usuario específico.
     *
     * @param usuarioId ID del usuario
     * @return lista de préstamos del usuario
     */
    @GetMapping("/usuario/{usuarioId}")
    public List<Prestamo> obtenerPorUsuario(@PathVariable Long usuarioId) {
        return prestamoService.findByUsuario(usuarioId);
    }

    /**
     * Obtiene todos los préstamos asociados a un libro específico.
     *
     * @param libroId ID del libro
     * @return lista de préstamos del libro
     */
    @GetMapping("/libro/{libroId}")
    public List<Prestamo> obtenerPorLibro(@PathVariable Long libroId) {
        return prestamoService.findByLibro(libroId);
    }
}
