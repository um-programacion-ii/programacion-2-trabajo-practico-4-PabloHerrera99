package Controllers;

import Modelos.Prestamo;

import Services.PrestamoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {
    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @GetMapping
    public List<Prestamo> obtenerTodos() {
        return prestamoService.findAll();
    }

    @GetMapping("/{id}")
    public Prestamo obtenerPorId(@PathVariable Long id) {
        return prestamoService.findById(id);
    }

    @PostMapping
    public Prestamo registrar(@RequestBody Prestamo prestamo) {
        return prestamoService.save(prestamo);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        prestamoService.delete(id);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Prestamo> obtenerPorUsuario(@PathVariable Long usuarioId) {
        return prestamoService.findByUsuario(usuarioId);
    }

    @GetMapping("/libro/{libroId}")
    public List<Prestamo> obtenerPorLibro(@PathVariable Long libroId) {
        return prestamoService.findByLibro(libroId);
    }
}
